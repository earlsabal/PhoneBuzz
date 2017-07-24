package controllers;

import play.mvc.*;
import play.Configuration;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import java.net.URI;

import java.lang.Exception;
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

		String greeting = "Hello player, input a number between 1 and 1000 then press pound to play PhoneBuzz";
		Say message = new Say.Builder(greeting)
													.voice(Say.Voice.WOMAN).build();

		Gather playRound = new Gather.Builder()
													.action("/fizzbuzz")
													.say(message).build();
		
		try { 
			VoiceResponse response = new VoiceResponse.Builder()
													.gather(playRound).build();
			return ok(response.toXml()).as("text/xml");
		} catch (TwiMLException exception) { 
			exception.printStackTrace();
			return ok("Something went wrong");
		}	

	}

	public Result fizzBuzz() {

		final Map<String, String[]> params = request().body().asFormUrlEncoded();
		String numberEntered = params.get("Digits")[0];
		
		int numberToPlay = validPhoneBuzzEntry(numberEntered);
		if (numberToPlay < 1) { return ok(errorResponse(numberToPlay)); }

		String response = phoneBuzzResponse(numberToPlay);
		return ok(response);

	}

	public Result call() {

		String phoneNumber = request().body().asFormUrlEncoded().get("phone")[0];
		String secondsDelayed = request().body().asFormUrlEncoded().get("seconds")[0];

		int status = validCallRequestChecker(phoneNumber, secondsDelayed);
		if (status == 0) { status = makePhoneCall(phoneNumber); }

	  return ok(errorResponse(status));

	}

// HELPER METHODS -------------------------------------------------------------

	// RETURNS -1 for invalid entry, -2 if number is outside range(1-1000)
	// ELSE converted number
	public int validPhoneBuzzEntry(String numberEntered){
		int numberStatus = stringToIntConverter(numberEntered);
		if (numberStatus == 0 || numberStatus > 1000) {
			numberStatus = -2;
		}
		return numberStatus;
	}

	public int validCallRequestChecker(String phone, String seconds) {

		long secondsDelayed = stringToLongConverter(seconds);

		// Checks if input is only 10-digits
		if (phone.length() != 10) { return -3; }
		// Checks if input is only numbers
		if (stringToLongConverter(phone) == -1) { return -4; }
		// Checks if the delayed seconds is a valid input
		if (secondsDelayed == -1) { return -5; }
		// Checks if phone delay gets interrupted
    if (delayCall(secondsDelayed) == -1) { return -6; }
    return 0;

	}

	// Generates fizz buzz response from 1 to entered number
	public String phoneBuzzResponse(int max) {

		String response = "";
		String phrase = "";
		for (int i = 1; i <= max; i++) {
			phrase = "";
			if (i % 3 == 0) { phrase += " Fizz"; }
			if (i % 5 == 0) { phrase += " Buzz"; }
			if (phrase.equals("")) { phrase = " " + String.valueOf(i); }
			response += phrase + ",";
		}
		response += "thank you for playing PhoneBuzz";
		return response;

	}

	// Converts String to Integer, returns -1 if String cannot be converted
	public int stringToIntConverter(String enteredNumber) {

		try { 
			int convertedNumber = Integer.parseInt(enteredNumber); 
			return convertedNumber;
		} catch (Exception exception) { 
			return -1; 
		}

	}

	public long stringToLongConverter(String enteredNumber) {

		long number = 0;
		try {
			number = Long.parseLong(enteredNumber);
		} catch (Exception numberException) {
			return -1;
		}
		return number;

	}

	public int makePhoneCall(String phoneNumber) {

		TwilioRestClient client = new TwilioRestClient.Builder(ACCOUNT_SID, AUTH_TOKEN).build();
		PhoneNumber to = new PhoneNumber(phoneNumber);
    PhoneNumber from = new PhoneNumber(FROM_NUMBER);
    URI uri = URI.create(APP_URL);

    try {
	  	Call call = Call.creator(to, from, uri).create(client);
    } catch (Exception callException) {
    	callException.printStackTrace();
    	return -1;
    }
    return 0;

	}

	public int delayCall(long secondsDelayed) {

		try {
    	TimeUnit.SECONDS.sleep(secondsDelayed);
    } catch (InterruptedException e) {
    	return -1;
    }
    return 0;

	}

	// Error messages
	public String errorResponse(int number) {

		switch (number) {  
			case  0: return "Success";
	    case -1: return "Sorry, please input a valid number";  
	    case -2: return "Please enter a number between 1 and 1000";  
	    case -3: return "Not a 10-digit number";
			case -4: return "Invalid phone number input";
			case -5: return "Invalid seconds input";
			case -6: return "Phone call delay got interrupted";
			case -7: return "Invalid URI or Invalid caller ID, add phone number in your Twilio Verified Caller IDs"; 
    default: return "Something went wrong";  
    }  

	}

}