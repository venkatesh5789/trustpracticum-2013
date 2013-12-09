// @SOURCE:/Users/Venkatesh/Documents/workspace/NASAPracticum-2013/conf/routes
// @HASH:6da5def763434de5f92fe949612c3ad88a7c9661
// @DATE:Wed Nov 13 23:37:37 PST 2013


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
        

// @LINE:16
private[this] lazy val controllers_Application_getGraphWithRender3 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("getGraphWithRender/"),DynamicPart("name", """[^/]+""",true),StaticPart("/"),DynamicPart("level", """[^/]+""",true))))
        

// @LINE:19
private[this] lazy val controllers_Application_getCoAuthorInformation4 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("getCoAuthorInformation/"),DynamicPart("name", """[^/]+""",true))))
        

// @LINE:22
private[this] lazy val controllers_Application_getReputationForAuthor5 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("getReputation/"),DynamicPart("name", """[^/]+""",true))))
        

// @LINE:28
private[this] lazy val controllers_Application_getSocialNetwork6 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("getSocialNetwork/"),DynamicPart("name", """[^/]+""",true))))
        

// @LINE:31
private[this] lazy val controllers_Application_getCoAuthorsByTopic7 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("getCoAuthorsByTopic/"),DynamicPart("name", """[^/]+""",true),StaticPart("/"),DynamicPart("topics", """[^/]+""",true))))
        

// @LINE:34
private[this] lazy val controllers_Application_getCoAuthorsByTopicAndTime8 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("getCoAuthorsByTopicAndTime/"),DynamicPart("name", """[^/]+""",true),StaticPart("/"),DynamicPart("topics", """[^/]+""",true),StaticPart("/"),DynamicPart("year", """[^/]+""",true))))
        

// @LINE:37
private[this] lazy val controllers_Application_formSubmit9 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("show"))))
        
def documentation = List(("""GET""", prefix,"""controllers.Application.index()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """assets/$file<.+>""","""controllers.Assets.at(path:String = "/public", file:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getGraphWithoutRender/$name<[^/]+>/$level<[^/]+>""","""controllers.Application.getGraphWithoutRender(name:String, level:Integer)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getGraphWithRender/$name<[^/]+>/$level<[^/]+>""","""controllers.Application.getGraphWithRender(name:String, level:Integer)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getCoAuthorInformation/$name<[^/]+>""","""controllers.Application.getCoAuthorInformation(name:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getReputation/$name<[^/]+>""","""controllers.Application.getReputationForAuthor(name:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getSocialNetwork/$name<[^/]+>""","""controllers.Application.getSocialNetwork(name:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getCoAuthorsByTopic/$name<[^/]+>/$topics<[^/]+>""","""controllers.Application.getCoAuthorsByTopic(name:String, topics:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """getCoAuthorsByTopicAndTime/$name<[^/]+>/$topics<[^/]+>/$year<[^/]+>""","""controllers.Application.getCoAuthorsByTopicAndTime(name:String, topics:String, year:Long)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """show""","""controllers.Application.formSubmit()""")).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
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
        invokeHandler(controllers.Application.getGraphWithoutRender(name, level), HandlerDef(this, "controllers.Application", "getGraphWithoutRender", Seq(classOf[String], classOf[Integer]),"GET", """ Get a graph of the co-authors of an author given a level and a name""", Routes.prefix + """getGraphWithoutRender/$name<[^/]+>/$level<[^/]+>"""))
   }
}
        

// @LINE:16
case controllers_Application_getGraphWithRender3(params) => {
   call(params.fromPath[String]("name", None), params.fromPath[Integer]("level", None)) { (name, level) =>
        invokeHandler(controllers.Application.getGraphWithRender(name, level), HandlerDef(this, "controllers.Application", "getGraphWithRender", Seq(classOf[String], classOf[Integer]),"GET", """ """, Routes.prefix + """getGraphWithRender/$name<[^/]+>/$level<[^/]+>"""))
   }
}
        

// @LINE:19
case controllers_Application_getCoAuthorInformation4(params) => {
   call(params.fromPath[String]("name", None)) { (name) =>
        invokeHandler(controllers.Application.getCoAuthorInformation(name), HandlerDef(this, "controllers.Application", "getCoAuthorInformation", Seq(classOf[String]),"GET", """ Given an author's name, get all the co-authors""", Routes.prefix + """getCoAuthorInformation/$name<[^/]+>"""))
   }
}
        

// @LINE:22
case controllers_Application_getReputationForAuthor5(params) => {
   call(params.fromPath[String]("name", None)) { (name) =>
        invokeHandler(controllers.Application.getReputationForAuthor(name), HandlerDef(this, "controllers.Application", "getReputationForAuthor", Seq(classOf[String]),"GET", """""", Routes.prefix + """getReputation/$name<[^/]+>"""))
   }
}
        

// @LINE:28
case controllers_Application_getSocialNetwork6(params) => {
   call(params.fromPath[String]("name", None)) { (name) =>
        invokeHandler(controllers.Application.getSocialNetwork(name), HandlerDef(this, "controllers.Application", "getSocialNetwork", Seq(classOf[String]),"GET", """""", Routes.prefix + """getSocialNetwork/$name<[^/]+>"""))
   }
}
        

// @LINE:31
case controllers_Application_getCoAuthorsByTopic7(params) => {
   call(params.fromPath[String]("name", None), params.fromPath[String]("topics", None)) { (name, topics) =>
        invokeHandler(controllers.Application.getCoAuthorsByTopic(name, topics), HandlerDef(this, "controllers.Application", "getCoAuthorsByTopic", Seq(classOf[String], classOf[String]),"GET", """""", Routes.prefix + """getCoAuthorsByTopic/$name<[^/]+>/$topics<[^/]+>"""))
   }
}
        

// @LINE:34
case controllers_Application_getCoAuthorsByTopicAndTime8(params) => {
   call(params.fromPath[String]("name", None), params.fromPath[String]("topics", None), params.fromPath[Long]("year", None)) { (name, topics, year) =>
        invokeHandler(controllers.Application.getCoAuthorsByTopicAndTime(name, topics, year), HandlerDef(this, "controllers.Application", "getCoAuthorsByTopicAndTime", Seq(classOf[String], classOf[String], classOf[Long]),"GET", """""", Routes.prefix + """getCoAuthorsByTopicAndTime/$name<[^/]+>/$topics<[^/]+>/$year<[^/]+>"""))
   }
}
        

// @LINE:37
case controllers_Application_formSubmit9(params) => {
   call { 
        invokeHandler(controllers.Application.formSubmit(), HandlerDef(this, "controllers.Application", "formSubmit", Nil,"POST", """""", Routes.prefix + """show"""))
   }
}
        
}

}
     