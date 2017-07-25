package controllers;

import play.mvc.*;
import models.PhoneBuzzRound;
import services.PhoneBuzzRoundService;
import javax.inject.Inject;

import java.util.ArrayList;
import java.util.List;


public class HomeController extends Controller {

	@Inject
  private PhoneBuzzRoundService phoneBuzzRoundService;

	public Result index() {
		
    List<PhoneBuzzRound> rounds = phoneBuzzRoundService.findAll();
    String message = "Here are all the rounds played";
    if (rounds.size() == 0) { 
      message = "No rounds have been played";
    }

    return ok(views.html.index.render(rounds, message));
    
	}

}
