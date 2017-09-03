package com.github.kennedyoliveira.ipify

import java.net.URL
import java.nio.charset.StandardCharsets.UTF_8

import scala.concurrent.{ExecutionContext, Future}
import scala.io.{Codec, Source}
import scala.util.Try
import scala.util.control.NonFatal

/**
  * Client library for ipify: [[https://www.ipify.org]] - A Simple IP Address API.
  *
  * ipify will get your public IP address, and return it.
  */
object Ipify {

  /**
    * Default codec, the answer for the API are just numbers,
    * but we need this to use [[Source]]
    */
  implicit val DefaultCodec: Codec = Codec(UTF_8)

  /**
    * Api URL with HTTPS
    */
  val ApiUrlHttps = new URL("https://api.ipify.org")

  /**
    * Api Url with HTTP
    */
  val ApiUrlHttp = new URL("https://api.ipify.org")

  /**
    * Fetch your IP address in a blocking way
    *
    * @param useHttps Flag to use HTTPS or not.
    * @return Your IP address
    */
  def myIpSync(useHttps: Boolean = true): Try[String] = Try(fetchIp(useHttps))

  /**
    * Fetch your IP Address asynchronously.
    *
    * @param useHttps Flag to use HTTPS or not.
    * @param ec       [[ExecutionContext]] for running the blocking operation.
    * @return A [[Future]] with your IP address.
    */
  def myIp(useHttps: Boolean = true)(implicit ec: ExecutionContext): Future[String] = {
    Future {
      fetchIp(useHttps)
    }
  }

  private def fetchIp(useHttps: Boolean = true): String = {
    val url = if (useHttps) ApiUrlHttps else ApiUrlHttp
    managed(Source.fromURL(url))(_.mkString)
  }

  private def managed[T](s: Source)(f: Source => T): T = {
    try {
      f(s)
    } finally {
      try {
        s.close()
      } catch {
        case NonFatal(ex) => // suppress the non fatal exception
      }
    }
  }
}