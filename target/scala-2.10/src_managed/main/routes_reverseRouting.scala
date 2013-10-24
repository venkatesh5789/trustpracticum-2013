// @SOURCE:/Users/Shuai/test/conf/routes
// @HASH:32f0936c7069dda458518429bc370f43013f2ce9
// @DATE:Thu Oct 24 13:59:30 PDT 2013

import Routes.{prefix => _prefix, defaultPrefix => _defaultPrefix}
import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._
import play.libs.F

import Router.queryString


// @LINE:16
// @LINE:13
// @LINE:9
// @LINE:6
package controllers {

// @LINE:9
class ReverseAssets {
    

// @LINE:9
def at(file:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[PathBindable[String]].unbind("file", file))
}
                                                
    
}
                          

// @LINE:16
// @LINE:13
// @LINE:6
class ReverseApplication {
    

// @LINE:16
def myShow(name:String, level:Integer): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "show/" + implicitly[PathBindable[String]].unbind("name", dynamicString(name)) + "/" + implicitly[PathBindable[Integer]].unbind("level", level))
}
                                                

// @LINE:6
def index(): Call = {
   Call("GET", _prefix)
}
                                                

// @LINE:13
def formSubmit(name:String, level:Integer): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "show/" + implicitly[PathBindable[String]].unbind("name", dynamicString(name)) + "/" + implicitly[PathBindable[Integer]].unbind("level", level))
}
                                                
    
}
                          
}
                  


// @LINE:16
// @LINE:13
// @LINE:9
// @LINE:6
package controllers.javascript {

// @LINE:9
class ReverseAssets {
    

// @LINE:9
def at : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Assets.at",
   """
      function(file) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("file", file)})
      }
   """
)
                        
    
}
              

// @LINE:16
// @LINE:13
// @LINE:6
class ReverseApplication {
    

// @LINE:16
def myShow : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.myShow",
   """
      function(name,level) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "show/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("name", encodeURIComponent(name)) + "/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("level", level)})
      }
   """
)
                        

// @LINE:6
def index : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.index",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + """"})
      }
   """
)
                        

// @LINE:13
def formSubmit : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.formSubmit",
   """
      function(name,level) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "show/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("name", encodeURIComponent(name)) + "/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("level", level)})
      }
   """
)
                        
    
}
              
}
        


// @LINE:16
// @LINE:13
// @LINE:9
// @LINE:6
package controllers.ref {


// @LINE:9
class ReverseAssets {
    

// @LINE:9
def at(path:String, file:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String]), "GET", """ Map static resources from the /public folder to the /assets URL path""", _prefix + """assets/$file<.+>""")
)
                      
    
}
                          

// @LINE:16
// @LINE:13
// @LINE:6
class ReverseApplication {
    

// @LINE:16
def myShow(name:String, level:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.myShow(name, level), HandlerDef(this, "controllers.Application", "myShow", Seq(classOf[String], classOf[Integer]), "GET", """""", _prefix + """show/$name<[^/]+>/$level<[^/]+>""")
)
                      

// @LINE:6
def index(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.index(), HandlerDef(this, "controllers.Application", "index", Seq(), "GET", """ Home page""", _prefix + """""")
)
                      

// @LINE:13
def formSubmit(name:String, level:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.formSubmit(name, level), HandlerDef(this, "controllers.Application", "formSubmit", Seq(classOf[String], classOf[Integer]), "POST", """""", _prefix + """show/$name<[^/]+>/$level<[^/]+>""")
)
                      
    
}
                          
}
        
    