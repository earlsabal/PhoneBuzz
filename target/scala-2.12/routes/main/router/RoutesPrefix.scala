
// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/Earl/Desktop/projects/PhoneBuzz/conf/routes
// @DATE:Fri Jul 21 22:51:54 PDT 2017


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
