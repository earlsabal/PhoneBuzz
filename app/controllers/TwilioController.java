package controllers;
import play.mvc.*;

import com.twilio.twiml.TwiMLException;

public class TwilioController extends Controller {

	public Result play() throws TwiMLException{
		return ok();
	}

	public Result fizzBuzz() {
		return ok();
	}

}