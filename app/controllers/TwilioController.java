package controllers;
import play.mvc.*;
import java.util.Map;

import com.twilio.twiml.Gather;
import com.twilio.twiml.VoiceResponse;
import com.twilio.twiml.Say;
import com.twilio.twiml.TwiMLException;

public class TwilioController extends Controller {

	public Result play() {
		Say message = new Say.Builder("Hello player, input a number then press pound to play PhoneBuzz")
			.voice(Say.Voice.WOMAN).build();

		Gather playRound = new Gather.Builder().action("/fizzbuzz").say(message).build();
		
		try { 
			VoiceResponse response = new VoiceResponse.Builder().gather(playRound).build();
			return ok(response.toXml()).as("text/xml");
		}
		catch (TwiMLException exception) { 
			exception.printStackTrace();
			return ok("Something went wrong");
		}	
	}

	public Result fizzBuzz() {
		final Map<String, String[]> values = request().body().asFormUrlEncoded();
		String number = values.get("Digits")[0];
		int num = 0;

		//Checks a valid number
		try { num = Integer.parseInt(number); }
		catch (Exception e) { return ok("Sorry, please input a valid number"); }

		//Checks for a reasonable number
		if (num > 1000 || num < 1) { return ok("Please enter a number between 1 and 1000"); }

		String response = "";
		String phrase = "";
		for (int i = 1; i <= num; i++) {
			phrase = "";
			if (i % 3 == 0) { phrase += " Fizz"; }
			if (i % 5 == 0) { phrase += " Buzz"; }
			if (phrase.equals("")) { phrase = " " + String.valueOf(i); }
			response += phrase;
			if (i != num) { response += ","; }
		}
		return ok(response);
	}

	public Result call() {
		TwilioRestClient client = new TwilioRestClient.Builder(ACCOUNT_SID, AUTH_TOKEN).build();

    PhoneNumber to = new PhoneNumber(TO_NUMBER); // Replace with your phone number
    PhoneNumber from = new PhoneNumber(FROM_NUMBER); // Replace with a Twilio number
    URI uri = URI.create("https://earl-phone-buzz.herokuapp.com/play");

    Call call = Call.creator(to, from, uri).create(client);
    return ok("Success!");

	}

}