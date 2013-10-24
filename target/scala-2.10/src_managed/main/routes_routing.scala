// @SOURCE:/Users/Shuai/test/conf/routes
// @HASH:32f0936c7069dda458518429bc370f43013f2ce9
// @DATE:Thu Oct 24 13:59:30 PDT 2013


import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._
import play.libs.F

import Router.queryString

object Routes extends Router.Routes {

private var _prefix = "/"

def setPrefix(prefix: String) {
  _prefix = prefix
  List[(String,Routes)]().foreach {
    case (p, router) => router.setPrefix(prefix + (if(prefix.endsWith("/")) "" else "/") + p)
  }
}

def prefix = _prefix

lazy val defaultPrefix = { if(Routes.prefix.endsWith("/")) "" else "/" }


// @LINE:6
private[this] lazy val controllers_Application_index0 = Route("GET", PathPattern(List(StaticPart(Routes.prefix))))
        

// @LINE:9
private[this] lazy val controllers_Assets_at1 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("assets/"),DynamicPart("file", """.+""",false))))
        

// @LINE:13
private[this] lazy val controllers_Application_formSubmit2 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("show/"),DynamicPart("name", """[^/]+""",true),StaticPart("/"),DynamicPart("level", """[^/]+""",true))))
        

// @LINE:16
private[this] lazy val controllers_Application_myShow3 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("show/"),DynamicPart("name", """[^/]+""",true),StaticPart("/"),DynamicPart("level", """[^/]+""",true))))
        
def documentation = List(("""GET""", prefix,"""controllers.Application.index()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """assets/$file<.+>""","""controllers.Assets.at(path:String = "/public", file:String)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """show/$name<[^/]+>/$level<[^/]+>""","""controllers.Application.formSubmit(name:String, level:Integer)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """show/$name<[^/]+>/$level<[^/]+>""","""controllers.Application.myShow(name:String, level:Integer)""")).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
  case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
  case l => s ++ l.asInstanceOf[List[(String,String,String)]] 
}}
      

def routes:PartialFunction[RequestHeader,Handler] = {

// @LINE:6
case controllers_Application_index0(params) => {
   call { 
        invokeHandler(controllers.Application.index(), HandlerDef(this, "controllers.Application", "index", Nil,"GET", """ Home page""", Routes.prefix + """"""))
   }
}
        

// @LINE:9
case controllers_Assets_at1(params) => {
   call(Param[String]("path", Right("/public")), params.fromPath[String]("file", None)) { (path, file) =>
        invokeHandler(controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String]),"GET", """ Map static resources from the /public folder to the /assets URL path""", Routes.prefix + """assets/$file<.+>"""))
   }
}
        

// @LINE:13
case controllers_Application_formSubmit2(params) => {
   call(params.fromPath[String]("name", None), params.fromPath[Integer]("level", None)) { (name, level) =>
        invokeHandler(controllers.Application.formSubmit(name, level), HandlerDef(this, "controllers.Application", "formSubmit", Seq(classOf[String], classOf[Integer]),"POST", """""", Routes.prefix + """show/$name<[^/]+>/$level<[^/]+>"""))
   }
}
        

// @LINE:16
case controllers_Application_myShow3(params) => {
   call(params.fromPath[String]("name", None), params.fromPath[Integer]("level", None)) { (name, level) =>
        invokeHandler(controllers.Application.myShow(name, level), HandlerDef(this, "controllers.Application", "myShow", Seq(classOf[String], classOf[Integer]),"GET", """""", Routes.prefix + """show/$name<[^/]+>/$level<[^/]+>"""))
   }
}
        
}

}
     