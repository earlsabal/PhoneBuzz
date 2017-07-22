
// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/Earl/Desktop/projects/PhoneBuzz/conf/routes
// @DATE:Fri Jul 21 23:28:17 PDT 2017

import play.api.mvc.Call


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:3
package controllers {

  // @LINE:3
  class ReverseHomeController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:3
    def index(): Call = {
      
      Call("GET", _prefix)
    }
  
  }

  // @LINE:8
  class ReverseAssets(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:8
    def versioned(file:Asset): Call = {
      implicit val _rrc = new play.core.routing.ReverseRouteContext(Map(("path", "/public")))
      Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[play.api.mvc.PathBindable[Asset]].unbind("file", file))
    }
  
  }

  // @LINE:4
  class ReverseTwilioController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:4
    def play(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "play")
    }
  
    // @LINE:5
    def fizzBuzz(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "fizzbuzz")
    }
  
  }


}
