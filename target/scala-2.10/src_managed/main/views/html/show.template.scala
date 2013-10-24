
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
object show extends BaseScalaTemplate[play.api.templates.HtmlFormat.Appendable,Format[play.api.templates.HtmlFormat.Appendable]](play.api.templates.HtmlFormat) with play.api.templates.Template1[String,play.api.templates.HtmlFormat.Appendable] {

    /**/
    def apply/*1.2*/(result: String):play.api.templates.HtmlFormat.Appendable = {
        _display_ {

Seq[Any](format.raw/*1.18*/("""

"""),_display_(Seq[Any](/*3.2*/main("Here is the result:")/*3.29*/ {_display_(Seq[Any](format.raw/*3.31*/("""
	<ul>
  		<li> """),_display_(Seq[Any](/*5.11*/result)),format.raw/*5.17*/(""" </li>
	</ul>

""")))})))}
    }
    
    def render(result:String): play.api.templates.HtmlFormat.Appendable = apply(result)
    
    def f:((String) => play.api.templates.HtmlFormat.Appendable) = (result) => apply(result)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Thu Oct 24 13:12:58 PDT 2013
                    SOURCE: /Users/Shuai/test/app/views/show.scala.html
                    HASH: adf29a4dbdb2f7c0c57df931fdde0c64e96dc15e
                    MATRIX: 773->1|883->17|920->20|955->47|994->49|1046->66|1073->72
                    LINES: 26->1|29->1|31->3|31->3|31->3|33->5|33->5
                    -- GENERATED --
                */
            