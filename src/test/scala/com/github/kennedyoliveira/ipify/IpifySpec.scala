package com.github.kennedyoliveira.ipify

import org.scalatest.{AsyncFlatSpec, Matchers}

import scala.util.{Failure, Success}

class IpifySpec extends AsyncFlatSpec with Matchers {

  behavior of "Ipify"

  it should "return my ip address sync" in {
    Ipify.myIpSync() match {
      case Failure(ex) => fail(ex)
      case Success(ip) => validateIp(ip)
    }
  }

  it should "return my ip asynchronous" in {
    Ipify.myIp() map validateIp
  }

  private def validateIp(ip: String) = {
    // xxx.xxx.xxx.xxx
    ip should fullyMatch regex """(\d{1,3}\.){3}\d{1,3}"""
  }
}
