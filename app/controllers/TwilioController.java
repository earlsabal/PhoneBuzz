package controllers;
import play.mvc.*;
import java.util.Map;
import play.Configuration;
import javax.inject.Inject;
import java.lang.Exception;
import java.net.URI;

import java.util.concurrent.TimeUnit;
import java.lang.InterruptedException;

import com.twilio.http.TwilioRestClient;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.type.PhoneNumber;
import com.twilio.twiml.Gather;
import com.twilio.twiml.VoiceResponse;
import com.twilio.twiml.Say;
import com.twilio.twiml.TwiMLException;

public class TwilioController extends Controller {

	private final String ACCOUNT_SID;
	private final String AUTH_TOKEN;
	private final String FROM_NUMBER;	
	private final String APP_URL;	

	@Inject
	public TwilioController(Configuration configuration) {
		this.ACCOUNT_SID = configuration.getString("accountSID");
		this.AUTH_TOKEN = configuration.getString("authToken");
		this.FROM_NUMBER = configuration.getString("fromNumber");
		this.APP_URL = configuration.getString("url");
	}

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
		String toNumber = request().body().asFormUrlEncoded().get("phone")[0];
		String seconds = request().body().asFormUrlEncoded().get("seconds")[0];
		int secondsDelayed;

		// Checks if input is only 10-digits
		if (toNumber.length() != 10) { return ok("Not a 10-digit number"); }

		// Checks if input is only numbers
		try {
			int number = Integer.parseInt(toNumber);
		} catch (Exception numberException) {
			return ok("Invalid phone number input");
		}

		// Checks if the delayed seconds is a valid input
		try {
			secondsDelayed = Integer.parseInt(seconds);
		} catch (Exception secondsException) {
			return ok("Invalid seconds input");
		}

		// Checks if phone delay gets interrupted
    try {
    	TimeUnit.SECONDS.sleep(secondsDelayed);
    } catch (InterruptedException e) {
    	return ok("Phone call delay got interrupted");
    }

    PhoneNumber to = new PhoneNumber(toNumber);
    PhoneNumber from = new PhoneNumber(FROM_NUMBER);
    URI uri = URI.create(APP_URL);

    try {
	  	Call call = Call.creator(to, from, uri).create(client);
    } catch (Exception callException) {
    	callException.printStackTrace();
    	return ok("Invalid URI or Invalid caller ID, add phone number in your Twilio Verified Caller IDs");
    }
	  return ok("Success!");
	}

}