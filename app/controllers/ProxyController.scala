package controllers


import java.net.{URL, URLDecoder, URLEncoder}
import java.nio.charset.Charset
import javax.inject.{Inject, Singleton}

import play.api.data.Form
import play.api.data.Forms.{mapping, text}
import play.api.libs.ws.WSClient
import play.api.mvc.{Action, Controller, Request}

import scala.concurrent.ExecutionContext.Implicits.global

@Singleton
class ProxyController @Inject()(wsClient: WSClient) extends Controller {

  val proxyRequestForm = Form(
    mapping(
      "url" -> text
    )(ProxyRequest.apply)(ProxyRequest.unapply)
  )

  def index() = Action { implicit request =>
    Ok(views.html.index(proxyRequestForm))
  }

  def proxy(url: String) = Action.async { implicit request =>
    val targetUrl = URLDecoder.decode(url, Charset.defaultCharset().name())
    getRemote(url)
  }

  def serveProxied() = Action.async(parse.form(proxyRequestForm)) { implicit request =>
    val proxyRequest: ProxyRequest = request.body
    getRemote(proxyRequest.url)
  }

  private def getRemote(url: String)(implicit request: Request[_]) = {
    wsClient.url(url)
      .get()
      .map { response =>
        val contentType: String = response.header("Content-Type").getOrElse("")
        if (contentType.toLowerCase.startsWith("text/")) {
          Ok(rewriteTextBody(response.body, new URL(url))).as(contentType)
        }
        else {
          Ok(response.bodyAsBytes).as(contentType)
        }
      }
  }

  private def rewriteTextBody[T](responseBody: String, pageUrl: URL)(implicit request: Request[_]) = {
    import ProxyController._
    UrlContainers.foldLeft(responseBody) { case (body, matcher) =>
      matcher.r.replaceAllIn(
        body,
        m => {
          val open = m.group(1)
          val url = rewriteUrl(m.group(3), pageUrl)
          println("----")
          println(m.matched)
          (0 to m.groupCount).foreach(i => println(s"$i -> ${m.group(i)}"))
          s"""$open="$url""""
        }
      )
    }
  }

  private def rewriteUrl(url: String, pageUrl: URL)(implicit request: Request[_]): String = {
    val prefix =
      if (url.startsWith("//")) {
        s"${pageUrl.getProtocol}:"
      }
      else if (url.startsWith("/")) {
        // Won't work on non-usual ports...
        s"${pageUrl.getProtocol}://${pageUrl.getAuthority}"
      }
      else {
        ""
      }
    s"""//${request.host}/proxy/${urlEncode(prefix + url)}"""
  }

  private def urlEncode(str: String): String = URLEncoder.encode(str, Charset.defaultCharset().name())
}

object ProxyController {
  val UrlCharset = "[0-9a-zA-Z$-_.+!*'(),]*"
  val UrlContainers = Seq(
    s"""(src)=("|')($UrlCharset)("|')""",
    s"""(href)=("|')($UrlCharset)("|')"""
  )
}
