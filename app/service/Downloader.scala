package service

import java.io.InputStream
import java.net.URL
import javax.inject.Singleton

import akka.stream.IOResult
import akka.stream.scaladsl.{Source, StreamConverters}
import akka.util.ByteString

import scala.concurrent.Future

@Singleton
class Downloader {

  def asStream(url: String): InputStream = {
    new URL(url).openConnection().getInputStream
  }

  def asSource(url: String): Source[ByteString, Future[IOResult]] = {
    StreamConverters.fromInputStream(
      () => asStream(url)
    )
  }
}
