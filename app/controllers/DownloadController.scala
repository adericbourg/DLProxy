package controllers

import java.net.URLDecoder
import java.nio.charset.Charset
import javax.inject._

import play.api.mvc._
import service.Downloader


@Singleton
class DownloadController @Inject()(downloader: Downloader) extends Controller {

  def index(url: String) = Action { implicit request =>
    val downloadUrl = URLDecoder.decode(url, Charset.defaultCharset().name())

    Ok.chunked {
      downloader.asSource(downloadUrl)
    }
  }
}
