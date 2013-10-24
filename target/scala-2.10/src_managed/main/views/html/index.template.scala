
package views.html

import play.templates._
import play.templates.TemplateMagic._

import play.api.templates._
import play.api.templates.PlayMagic._
import models._
import controllers._
import java.lang._
import java.util._
import scala.collection.JavaConversions._
import scala.collection.JavaConverters._
import play.api.i18n._
import play.core.j.PlayMagicForJava._
import play.mvc._
import play.data._
import play.api.data.Field
import play.mvc.Http.Context.Implicit._
import views.html._
/**/
object index extends BaseScalaTemplate[play.api.templates.HtmlFormat.Appendable,Format[play.api.templates.HtmlFormat.Appendable]](play.api.templates.HtmlFormat) with play.api.templates.Template1[String,play.api.templates.HtmlFormat.Appendable] {

    /**/
    def apply/*1.2*/(message: String):play.api.templates.HtmlFormat.Appendable = {
        _display_ {

Seq[Any](format.raw/*1.19*/("""

"""),_display_(Seq[Any](/*3.2*/main("Welcome to Play")/*3.25*/ {_display_(Seq[Any](format.raw/*3.27*/("""

<html>
  <FORM action = '/show' method = 'Post'>
	  name <Input type = 'text' name = 'name'>
	  level <Input type = 'number' name = 'level' >
	  <Input type = 'submit' value = 'OK'>
  </FORM>
</html>

""")))})),format.raw/*13.2*/("""
"""))}
    }
    
    def render(message:String): play.api.templates.HtmlFormat.Appendable = apply(message)
    
    def f:((String) => play.api.templates.HtmlFormat.Appendable) = (message) => apply(message)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Thu Oct 24 14:51:03 PDT 2013
                    SOURCE: /Users/Venkatesh/Documents/workspace/NASAPracticum-2013/app/views/index.scala.html
                    HASH: 6d00b3f0238ed06592e7d1f3e3f97a54b2ab248d
                    MATRIX: 774->1|885->18|922->21|953->44|992->46|1227->250
                    LINES: 26->1|29->1|31->3|31->3|31->3|41->13
                    -- GENERATED --
                */
            