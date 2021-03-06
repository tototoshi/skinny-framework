package controller

import service._

class RootController extends ApplicationController {

  val echoService: EchoService = EchoService()

  def index = {
    set("echo" -> params.get("echo").map(v => echoService.echo(v)))
    render("/root/index")
  }

  def renewSessionAttributes = {
    val locale = params.getAs[String]("locale").filter(_.length > 0).orNull[String]
    setCurrentLocale(locale)
    redirect(params.getAs[String]("returnTo").map(_.replaceFirst(s"^${contextPath}", "")).getOrElse("/"))
  }

}
