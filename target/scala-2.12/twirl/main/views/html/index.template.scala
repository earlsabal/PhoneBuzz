
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

object index extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template2[List[PhoneBuzzRound],String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(rounds: List[PhoneBuzzRound])(message: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.49*/("""

"""),_display_(/*3.2*/main("Welcome to Play")/*3.25*/ {_display_(Seq[Any](format.raw/*3.27*/("""
  """),format.raw/*4.3*/("""<h1>Welcome to Play!</h1>
  <form action="/call" method="POST">
  	Phone number to call formatted in 10-digits:<br>
  	<input type="tel" name="phone" pattern="^\d"""),format.raw/*7.47*/("""{"""),format.raw/*7.48*/("""10"""),format.raw/*7.50*/("""}"""),format.raw/*7.51*/("""$" required><br>
  	Number of seconds delay:<br>
  	<input type="number" name="seconds"><br>
	  <input type="submit" value="Make call">
	</form>

	<h2>"""),_display_(/*13.7*/message),format.raw/*13.14*/("""</h2>
""")))}),format.raw/*14.2*/("""
"""))
      }
    }
  }

  def render(rounds:List[PhoneBuzzRound],message:String): play.twirl.api.HtmlFormat.Appendable = apply(rounds)(message)

  def f:((List[PhoneBuzzRound]) => (String) => play.twirl.api.HtmlFormat.Appendable) = (rounds) => (message) => apply(rounds)(message)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Tue Jul 25 05:28:47 PDT 2017
                  SOURCE: C:/Users/Earl/Desktop/projects/PhoneBuzz/app/views/index.scala.html
                  HASH: 78454a82d522553691e1dc3d662726c2b21507d9
                  MATRIX: 969->1|1111->48|1139->51|1170->74|1209->76|1238->79|1427->241|1455->242|1484->244|1512->245|1690->397|1718->404|1755->411
                  LINES: 28->1|33->1|35->3|35->3|35->3|36->4|39->7|39->7|39->7|39->7|45->13|45->13|46->14
                  -- GENERATED --
              */
          