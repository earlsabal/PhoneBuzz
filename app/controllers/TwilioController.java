package controllers;
import play.mvc.*;

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
		return ok();
	}

}