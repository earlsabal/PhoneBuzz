package controllers;
import play.mvc.*;
import java.util.Map;

import com.twilio.twiml.Gather;
import com.twilio.twiml.VoiceResponse;
import com.twilio.twiml.Say;
import com.twilio.twiml.TwiMLException;

public class TwilioController extends Controller {

	public Result play() throws TwiMLException{
		Say message = new Say.Builder("Hello player, input a number then press pound to play PhoneBuzz")
			.voice(Say.Voice.WOMAN).build();

		Gather playRound = new Gather.Builder().action("/fizzbuzz").say(message).build();
		VoiceResponse response = new VoiceResponse.Builder().gather(playRound).build();
		
		return ok(response.toXml()).as("text/xml");
	}

	public Result fizzBuzz() {
		final Map<String, String[]> values = request().body().asFormUrlEncoded();
		String number = values.get("Digits")[0];
		int num = Integer.parseInt(number);
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

}