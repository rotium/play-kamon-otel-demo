package controllers

import kamon.Kamon
import play.api.Configuration

import javax.inject._
import play.api.libs.ws.WSClient

import scala.concurrent.{ExecutionContext, Future}
import play.api.mvc.{AnyContent, BaseController, ControllerComponents, Request}

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(ws: WSClient, config: Configuration, val controllerComponents: ControllerComponents)(implicit ec: ExecutionContext) extends BaseController {

  val url = config.get[String]("recursiveCall.url")
//  val count = config.get[Int]("recursiveCall.count")

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }

  def recursiveCall(count: Int) = Action.async {
    val span = Kamon.spanBuilder("bsonParserWrapper").start()
    if (count == 0) {
      Future.successful(Ok("fdsf"))
    } else {
      println(s"recursiveCall: $count")
      val callUrl = s"$url/${count-1}"
      val res = ws.url(callUrl).get()
      res.map { r =>
        println(s"called: ${r.body}")
        Ok(r.body)
      }
    }.andThen { _ => span.finish() }
  }
}
