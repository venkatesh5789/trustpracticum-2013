// @SOURCE:/Users/Venkatesh/Documents/workspace/NASAPracticum-2013/conf/routes
// @HASH:8f9f9fd2bb620b4660d35c98bcef54fd380c6468
// @DATE:Mon Oct 28 00:05:23 PDT 2013


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
        

// @LINE:12
private[this] lazy val controllers_Application_getGraphWithoutRender2 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("getGraphWithoutRender/"),DynamicPart("name", """[^/]+""",true),StaticPart("/"),DynamicPart("level", """[^/]+""",true))))
        

// @LINE:15
private[this] lazy val controllers_Application_getGraphWithRender3 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("getGraphWithRender/"),DynamicPart("name", """[^/]+""",true),StaticPart("/"),DynamicPart("level", """[^/]+""",true))))
        

// @LINE:18
private[this] lazy val controllers_Application_formSubmit4 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("show"))))
        
def documentation = List(("""GET""", prefix,"""controllers.Application.index()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """assets/$file<.+>""","""controllers.Assets.at(path:String = "/public", file:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getGraphWithoutRender/$name<[^/]+>/$level<[^/]+>""","""controllers.Application.getGraphWithoutRender(name:String, level:Integer)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getGraphWithRender/$name<[^/]+>/$level<[^/]+>""","""controllers.Application.getGraphWithRender(name:String, level:Integer)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """show""","""controllers.Application.formSubmit()""")).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
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
        

// @LINE:12
case controllers_Application_getGraphWithoutRender2(params) => {
   call(params.fromPath[String]("name", None), params.fromPath[Integer]("level", None)) { (name, level) =>
        invokeHandler(controllers.Application.getGraphWithoutRender(name, level), HandlerDef(this, "controllers.Application", "getGraphWithoutRender", Seq(classOf[String], classOf[Integer]),"GET", """""", Routes.prefix + """getGraphWithoutRender/$name<[^/]+>/$level<[^/]+>"""))
   }
}
        

// @LINE:15
case controllers_Application_getGraphWithRender3(params) => {
   call(params.fromPath[String]("name", None), params.fromPath[Integer]("level", None)) { (name, level) =>
        invokeHandler(controllers.Application.getGraphWithRender(name, level), HandlerDef(this, "controllers.Application", "getGraphWithRender", Seq(classOf[String], classOf[Integer]),"GET", """GET     /getGraphWithoutRender/:name/:level  			controllers.Application.generateDummyJson(name : String, level : Integer)
""", Routes.prefix + """getGraphWithRender/$name<[^/]+>/$level<[^/]+>"""))
   }
}
        

// @LINE:18
case controllers_Application_formSubmit4(params) => {
   call { 
        invokeHandler(controllers.Application.formSubmit(), HandlerDef(this, "controllers.Application", "formSubmit", Nil,"POST", """""", Routes.prefix + """show"""))
   }
}
        
}

}
     