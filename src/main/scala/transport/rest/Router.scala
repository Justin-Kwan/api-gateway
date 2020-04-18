package rest

object Router extends cask.MainRoutes {

  @cask.get("/hello")
  def hello() = {
    "Hello World!"
  }

  initialize()

}
