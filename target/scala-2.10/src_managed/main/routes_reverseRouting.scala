// @SOURCE:/Users/Venkatesh/Documents/workspace/NASAPracticum-2013/conf/routes
// @HASH:af035c9b4b1116f2085bbaa8d39d9ab135184ad6
// @DATE:Sat Nov 09 20:11:15 PST 2013

import Routes.{prefix => _prefix, defaultPrefix => _defaultPrefix}
import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._
import play.libs.F

import Router.queryString


// @LINE:22
// @LINE:19
// @LINE:16
// @LINE:12
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
                          

// @LINE:22
// @LINE:19
// @LINE:16
// @LINE:12
// @LINE:6
class ReverseApplication {
    

// @LINE:19
def getCoAuthorInformation(name:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "getCoAuthorInformation/" + implicitly[PathBindable[String]].unbind("name", dynamicString(name)))
}
                                                

// @LINE:12
def getGraphWithoutRender(name:String, level:Integer): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "getGraphWithoutRender/" + implicitly[PathBindable[String]].unbind("name", dynamicString(name)) + "/" + implicitly[PathBindable[Integer]].unbind("level", level))
}
                                                

// @LINE:16
def getGraphWithRender(name:String, level:Integer): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "getGraphWithRender/" + implicitly[PathBindable[String]].unbind("name", dynamicString(name)) + "/" + implicitly[PathBindable[Integer]].unbind("level", level))
}
                                                

// @LINE:22
def formSubmit(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "show")
}
                                                

// @LINE:6
def index(): Call = {
   Call("GET", _prefix)
}
                                                
    
}
                          
}
                  


// @LINE:22
// @LINE:19
// @LINE:16
// @LINE:12
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
              

// @LINE:22
// @LINE:19
// @LINE:16
// @LINE:12
// @LINE:6
class ReverseApplication {
    

// @LINE:19
def getCoAuthorInformation : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.getCoAuthorInformation",
   """
      function(name) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "getCoAuthorInformation/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("name", encodeURIComponent(name))})
      }
   """
)
                        

// @LINE:12
def getGraphWithoutRender : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.getGraphWithoutRender",
   """
      function(name,level) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "getGraphWithoutRender/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("name", encodeURIComponent(name)) + "/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("level", level)})
      }
   """
)
                        

// @LINE:16
def getGraphWithRender : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.getGraphWithRender",
   """
      function(name,level) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "getGraphWithRender/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("name", encodeURIComponent(name)) + "/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("level", level)})
      }
   """
)
                        

// @LINE:22
def formSubmit : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.formSubmit",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "show"})
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
                        
    
}
              
}
        


// @LINE:22
// @LINE:19
// @LINE:16
// @LINE:12
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
                          

// @LINE:22
// @LINE:19
// @LINE:16
// @LINE:12
// @LINE:6
class ReverseApplication {
    

// @LINE:19
def getCoAuthorInformation(name:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.getCoAuthorInformation(name), HandlerDef(this, "controllers.Application", "getCoAuthorInformation", Seq(classOf[String]), "GET", """""", _prefix + """getCoAuthorInformation/$name<[^/]+>""")
)
                      

// @LINE:12
def getGraphWithoutRender(name:String, level:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.getGraphWithoutRender(name, level), HandlerDef(this, "controllers.Application", "getGraphWithoutRender", Seq(classOf[String], classOf[Integer]), "GET", """""", _prefix + """getGraphWithoutRender/$name<[^/]+>/$level<[^/]+>""")
)
                      

// @LINE:16
def getGraphWithRender(name:String, level:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.getGraphWithRender(name, level), HandlerDef(this, "controllers.Application", "getGraphWithRender", Seq(classOf[String], classOf[Integer]), "GET", """""", _prefix + """getGraphWithRender/$name<[^/]+>/$level<[^/]+>""")
)
                      

// @LINE:22
def formSubmit(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.formSubmit(), HandlerDef(this, "controllers.Application", "formSubmit", Seq(), "POST", """""", _prefix + """show""")
)
                      

// @LINE:6
def index(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.index(), HandlerDef(this, "controllers.Application", "index", Seq(), "GET", """ Home page""", _prefix + """""")
)
                      
    
}
                          
}
        
    