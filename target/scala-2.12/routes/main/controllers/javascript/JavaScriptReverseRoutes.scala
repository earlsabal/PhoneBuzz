
// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/Earl/Desktop/projects/PhoneBuzz/conf/routes
// @DATE:Sat Jul 22 14:29:54 PDT 2017

import play.api.routing.JavaScriptReverseRoute


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:3
package controllers.javascript {

  // @LINE:3
  class ReverseHomeController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:3
    def index: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.index",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + """"})
        }
      """
    )
  
  }

  // @LINE:11
  class ReverseAssets(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:11
    def versioned: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Assets.versioned",
      """
        function(file1) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[play.api.mvc.PathBindable[Asset]].javascriptUnbind + """)("file", file1)})
        }
      """
    )
  
  }

  // @LINE:4
  class ReverseTwilioController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:4
    def play: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.TwilioController.play",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "play"})
        }
      """
    )
  
    // @LINE:5
    def fizzBuzz: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.TwilioController.fizzBuzz",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "fizzbuzz"})
        }
      """
    )
  
    // @LINE:8
    def call: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.TwilioController.call",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "call"})
        }
      """
    )
  
  }


}
