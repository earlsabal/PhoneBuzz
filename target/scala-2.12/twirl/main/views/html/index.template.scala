
package views.html

import _root_.play.twirl.api.TwirlFeatureImports._
import _root_.play.twirl.api.TwirlHelperImports._
import _root_.play.twirl.api.Html
import _root_.play.twirl.api.JavaScript
import _root_.play.twirl.api.Txt
import _root_.play.twirl.api.Xml
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import java.lang._
import java.util._
import scala.collection.JavaConverters._
import play.core.j.PlayMagicForJava._
import play.mvc._
import play.api.data.Field
import play.mvc.Http.Context.Implicit._
import play.data._
import play.core.j.PlayFormsMagicForJava._

object index extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/():play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.4*/("""

"""),_display_(/*3.2*/main("Welcome to Play")/*3.25*/ {_display_(Seq[Any](format.raw/*3.27*/("""
  """),format.raw/*4.3*/("""<h1>Welcome to Play!</h1>
  <form action="/call" method="POST">
  	Please enter a 10-digit phone number:<br>
  	<input type="tel" name="phone" pattern="^\d"""),format.raw/*7.47*/("""{"""),format.raw/*7.48*/("""10"""),format.raw/*7.50*/("""}"""),format.raw/*7.51*/("""$" required><br>
  	Number of seconds delay:<br>
  	<input type="number" name="seconds"><br>
	  <input type="submit" value="Make call">
	</form>
""")))}),format.raw/*12.2*/("""
"""))
      }
    }
  }

  def render(): play.twirl.api.HtmlFormat.Appendable = apply()

  def f:(() => play.twirl.api.HtmlFormat.Appendable) = () => apply()

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Sat Jul 22 20:28:17 PDT 2017
                  SOURCE: C:/Users/Earl/Desktop/projects/PhoneBuzz/app/views/index.scala.html
                  HASH: 3cb983773b42ed4fa39f7ddd43a69738bb3cd753
                  MATRIX: 941->1|1037->3|1065->6|1096->29|1135->31|1164->34|1346->189|1374->190|1403->192|1431->193|1607->339
                  LINES: 28->1|33->1|35->3|35->3|35->3|36->4|39->7|39->7|39->7|39->7|44->12
                  -- GENERATED --
              */
          