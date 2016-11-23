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
package io.github.gatling.udp.action

import java.net.{DatagramPacket, DatagramSocket, InetAddress}

import akka.actor.ActorSystem
import io.gatling.commons.stats.OK
import io.gatling.commons.util.TimeHelper._
import io.gatling.core.action.{Action, ExitableAction}
import io.gatling.core.session.Session
import io.gatling.core.stats.StatsEngine
import io.gatling.core.stats.message.ResponseTimings
import io.github.gatling.udp.UdpProtocol

class UdpSendAction(
   val name: String,
   val message: String,
   val next: Action,
   system: ActorSystem,
   val statsEngine: StatsEngine,
   protocol: UdpProtocol) extends ExitableAction {

  override def execute(session: Session): Unit = {
    val start = nowMillis
    send(message, protocol)
    statsEngine.logResponse(session, name, ResponseTimings(start, nowMillis), OK, None, None, Nil)
    next ! session.markAsSucceeded
  }

  def send(message: String, protocol: UdpProtocol): Unit = {
    val clientSocket = new DatagramSocket()
    val IPAddress = InetAddress.getByName(protocol.address)
    var sendData = new Array[Byte](1024)
    sendData = message.getBytes()
    val sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, protocol.port);
    clientSocket.send(sendPacket)
    clientSocket.close()
  }
}