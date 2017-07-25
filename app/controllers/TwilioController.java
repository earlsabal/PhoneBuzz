package controllers;

import play.mvc.*;
import play.Configuration;
import java.util.Map;

import java.util.ArrayList;
import java.util.List;

import models.PhoneBuzzRound;
import services.PhoneBuzzRoundService;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import java.net.URI;

import java.io.IOException;
import java.lang.Exception;
import java.lang.InterruptedException;

import com.twilio.exception.ApiException;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.type.PhoneNumber;
import com.twilio.twiml.Gather;
import com.twilio.twiml.VoiceResponse;
import com.twilio.twiml.Say;
import com.twilio.twiml.TwiMLException;

public class TwilioController extends Controller {

	// Constants, you can change the max as desired (change in greeting as well)
	public static final int MAX_NUMBER = 1000;
	public static final int MIN_NUMBER = 1;
	public static final int CONTENT = 0;
	public static final int NO_ERROR = 0;
	public static final int ERROR = -1;
	public static final int TEN_DIGIT_NUMBER = 10;
	public static final int ZERO_TIME = 0;

	// Config Variables
	private final String ACCOUNT_SID;
	private final String AUTH_TOKEN;
	private final String FROM_NUMBER;	
	private final String APP_URL;	

	@Inject
  private PhoneBuzzRoundService phoneBuzzRoundService;

	@Inject
	public TwilioController(Configuration configuration) {

		this.ACCOUNT_SID = configuration.getString("accountSID");
		this.AUTH_TOKEN = configuration.getString("authToken");
		this.FROM_NUMBER = configuration.getString("fromNumber");
		this.APP_URL = configuration.getString("url");

	}

	public Result play() {

		String url = "/fizzbuzz";
		// System.out.print(request().body().asFormUrlEncoded());

		// If you changed the MAX_NUMBER, change the greeting as well
		String greeting = "Hello player, input a number between 1 and 1000 then press pound to play PhoneBuzz";
		Say message = new Say.Builder(greeting)
													.voice(Say.Voice.WOMAN).build();

		Gather playRound = new Gather.Builder()
													.action(url)
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
		// String seconds = request().getParameter("seconds");
		String phone = params.get("Caller")[CONTENT];

		String numberEntered = params.get("Digits")[CONTENT];
		long numberToPlay = stringToLongConverter(numberEntered);

		if (numberToPlay == ERROR) { return ok("Sorry, please input a valid number"); }
		if (numberToPlay < MIN_NUMBER || numberToPlay > MAX_NUMBER) { return ok("Please enter a number between 1 and 1000"); }

		String response = phoneBuzzResponse(numberToPlay);

		saveRound(phone, "4", numberToPlay);
		return ok(response);

	}

	public Result callPlayer() {
		ScheduledExecutorService delayer = Executors.newSingleThreadScheduledExecutor();
		String phoneNumber = request().body().asFormUrlEncoded().get("phone")[CONTENT];
		String secondsDelayed = request().body().asFormUrlEncoded().get("seconds")[CONTENT];
		String status = "now calling";
		// Was trying to check is save was working
			saveRound(phoneNumber, secondsDelayed, 7);
		status = validateCallRequest(phoneNumber, secondsDelayed);
		Callable<Result> delayedCall = new Callable<Result>() {
			@Override
			public Result call() {
				final String response = makePhoneCall(phoneNumber, secondsDelayed);
				return ok(response);
			}
		};

		if (status.equals("Valid Call")) { 
			long delayedSeconds = stringToLongConverter(secondsDelayed); 
			delayer.schedule(delayedCall, delayedSeconds, TimeUnit.SECONDS);
			return ok("Will call " + phoneNumber + " in " + secondsDelayed + " seconds!!");
		}

	  return ok(status);
	}

// HELPER METHODS -------------------------------------------------------------

	// Generates fizz buzz response from 1 to entered number
	public String phoneBuzzResponse(long max) {

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

	public String validateCallRequest(String phone, String seconds) {

		long secondsDelayed = stringToLongConverter(seconds);

		if (phone.length() != TEN_DIGIT_NUMBER) { return "Not a 10-digit number"; }
		if (stringToLongConverter(phone) == ERROR) { return "Invalid phone number input"; }
		if (secondsDelayed == ERROR) { return "Invalid seconds input"; }
		if (secondsDelayed < ZERO_TIME) { return "Please input a positive number"; }
    return "Valid Call";

	}

	// Converts String to Integer, returns -1 if String cannot be converted
	public int stringToIntConverter(String enteredNumber) {

		try { 
			int convertedNumber = Integer.parseInt(enteredNumber); 
			return convertedNumber;
		} catch (Exception exception) { 
			return ERROR; 
		}

	}

	public long stringToLongConverter(String enteredNumber) {

		try {
			long number = Long.parseLong(enteredNumber);
			return number;
		} catch (Exception numberException) {
			return ERROR;
		}

	}

	public String makePhoneCall(String phoneNumber, String seconds) {

		TwilioRestClient client = new TwilioRestClient.Builder(ACCOUNT_SID, AUTH_TOKEN).build();
		PhoneNumber to = new PhoneNumber(phoneNumber);
    PhoneNumber from = new PhoneNumber(FROM_NUMBER);
    URI uri = URI.create(APP_URL);

    try {
    	System.out.println(APP_URL);
	  	Call call = Call.creator(to, from, uri).setSendDigits(seconds+"#").create(client);
    } catch (Exception callException) {
    	return "Invalid URI or Invalid caller ID, add phone number in your Twilio Verified Caller IDs";
    }
    return "Success";

	}

	public void saveRound(String phone, String delayedSeconds, long input) {

		long seconds = stringToLongConverter(delayedSeconds);

		System.out.println(phone);
		System.out.println(seconds);
		System.out.println(input);

		PhoneBuzzRound round = new PhoneBuzzRound(phone, 
																							seconds,
																							input);
		phoneBuzzRoundService.save(round);
		// round.save();
	}

}
