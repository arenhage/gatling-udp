package io.github.gatling.udp

import io.gatling.core.protocol.ProtocolComponents
import io.gatling.core.session.Session

case class UdpComponents(udpProtocol: UdpProtocol) extends ProtocolComponents {

  override def onStart: Option[Session => Session] = None

  override def onExit: Option[Session => Unit] = None

}