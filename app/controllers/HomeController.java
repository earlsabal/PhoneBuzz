package controllers;

import play.mvc.*;
import models.PhoneBuzzRound;
import services.PhoneBuzzRoundService;
import javax.inject.Inject;

import java.util.ArrayList;
import java.util.List;


public class HomeController extends Controller {

	public Result index() {
		return ok(views.html.index.render());
	}

}
