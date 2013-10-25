
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
	 """),_display_(Seq[Any](/*4.4*/result)),format.raw/*4.10*/(""" 
""")))})))}
    }
    
    def render(result:String): play.api.templates.HtmlFormat.Appendable = apply(result)
    
    def f:((String) => play.api.templates.HtmlFormat.Appendable) = (result) => apply(result)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Thu Oct 24 23:35:07 PDT 2013
                    SOURCE: /Users/Venkatesh/Documents/workspace/NASAPracticum-2013/app/views/show.scala.html
                    HASH: 3e515866d3c3ca58dbadb3954b4edf5af7571f1c
                    MATRIX: 773->1|883->17|920->20|955->47|994->49|1032->53|1059->59
                    LINES: 26->1|29->1|31->3|31->3|31->3|32->4|32->4
                    -- GENERATED --
                */
            