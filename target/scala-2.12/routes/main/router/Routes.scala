
// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/Earl/Desktop/projects/PhoneBuzz/conf/routes
// @DATE:Sat Jul 22 14:29:54 PDT 2017

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._

import play.api.mvc._

import _root_.controllers.Assets.Asset
import _root_.play.libs.F

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:3
  HomeController_1: controllers.HomeController,
  // @LINE:4
  TwilioController_0: controllers.TwilioController,
  // @LINE:11
  Assets_2: controllers.Assets,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:3
    HomeController_1: controllers.HomeController,
    // @LINE:4
    TwilioController_0: controllers.TwilioController,
    // @LINE:11
    Assets_2: controllers.Assets
  ) = this(errorHandler, HomeController_1, TwilioController_0, Assets_2, "/")

  def withPrefix(prefix: String): Routes = {
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, HomeController_1, TwilioController_0, Assets_2, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""GET""", this.prefix, """controllers.HomeController.index"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """play""", """controllers.TwilioController.play"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """fizzbuzz""", """controllers.TwilioController.fizzBuzz"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """call""", """controllers.TwilioController.call"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """assets/""" + "$" + """file<.+>""", """controllers.Assets.versioned(path:String = "/public", file:Asset)"""),
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:3
  private[this] lazy val controllers_HomeController_index0_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix)))
  )
  private[this] lazy val controllers_HomeController_index0_invoker = createInvoker(
    HomeController_1.index,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "index",
      Nil,
      "GET",
      this.prefix + """""",
      """""",
      Seq()
    )
  )

  // @LINE:4
  private[this] lazy val controllers_TwilioController_play1_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("play")))
  )
  private[this] lazy val controllers_TwilioController_play1_invoker = createInvoker(
    TwilioController_0.play,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.TwilioController",
      "play",
      Nil,
      "POST",
      this.prefix + """play""",
      """""",
      Seq()
    )
  )

  // @LINE:5
  private[this] lazy val controllers_TwilioController_fizzBuzz2_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("fizzbuzz")))
  )
  private[this] lazy val controllers_TwilioController_fizzBuzz2_invoker = createInvoker(
    TwilioController_0.fizzBuzz,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.TwilioController",
      "fizzBuzz",
      Nil,
      "POST",
      this.prefix + """fizzbuzz""",
      """""",
      Seq()
    )
  )

  // @LINE:8
  private[this] lazy val controllers_TwilioController_call3_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("call")))
  )
  private[this] lazy val controllers_TwilioController_call3_invoker = createInvoker(
    TwilioController_0.call,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.TwilioController",
      "call",
      Nil,
      "POST",
      this.prefix + """call""",
      """""",
      Seq("""nocsrf""")
    )
  )

  // @LINE:11
  private[this] lazy val controllers_Assets_versioned4_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("assets/"), DynamicPart("file", """.+""",false)))
  )
  private[this] lazy val controllers_Assets_versioned4_invoker = createInvoker(
    Assets_2.versioned(fakeValue[String], fakeValue[Asset]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Assets",
      "versioned",
      Seq(classOf[String], classOf[Asset]),
      "GET",
      this.prefix + """assets/""" + "$" + """file<.+>""",
      """ Map static resources from the /public folder to the /assets URL path""",
      Seq()
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:3
    case controllers_HomeController_index0_route(params) =>
      call { 
        controllers_HomeController_index0_invoker.call(HomeController_1.index)
      }
  
    // @LINE:4
    case controllers_TwilioController_play1_route(params) =>
      call { 
        controllers_TwilioController_play1_invoker.call(TwilioController_0.play)
      }
  
    // @LINE:5
    case controllers_TwilioController_fizzBuzz2_route(params) =>
      call { 
        controllers_TwilioController_fizzBuzz2_invoker.call(TwilioController_0.fizzBuzz)
      }
  
    // @LINE:8
    case controllers_TwilioController_call3_route(params) =>
      call { 
        controllers_TwilioController_call3_invoker.call(TwilioController_0.call)
      }
  
    // @LINE:11
    case controllers_Assets_versioned4_route(params) =>
      call(Param[String]("path", Right("/public")), params.fromPath[Asset]("file", None)) { (path, file) =>
        controllers_Assets_versioned4_invoker.call(Assets_2.versioned(path, file))
      }
  }
}
