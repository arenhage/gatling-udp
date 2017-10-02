/*
 * MIT License
 *
 * Copyright (c) 2016 Daniel Arenhage
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package io.github.gatling.udp

import akka.actor.ActorSystem
import io.gatling.core.CoreComponents
import io.gatling.core.config.GatlingConfiguration
import io.gatling.core.protocol.{Protocol, ProtocolKey}

object UdpProtocol {

  def apply(configuration: GatlingConfiguration): UdpProtocol = UdpProtocol(
    host = "localhost",
    port = 0
  )

  val UdpProtocolKey = new ProtocolKey {

    type Protocol = UdpProtocol
    type Components = UdpComponents

    def protocolClass: Class[io.gatling.core.protocol.Protocol] =
      classOf[UdpProtocol].asInstanceOf[Class[io.gatling.core.protocol.Protocol]]

    def defaultProtocolValue(configuration: GatlingConfiguration): UdpProtocol =
      UdpProtocol(configuration)

    def newComponents(system: ActorSystem, coreComponents: CoreComponents): UdpProtocol => UdpComponents = {
      udpProtocol => UdpComponents(udpProtocol)
    }
  }
}

case class UdpProtocol(host: String, port: Int) extends Protocol {

  def host(host: String): UdpProtocol = copy(host = host)

  def port(port: Int): UdpProtocol = copy(port = port)

}
