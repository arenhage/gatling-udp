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

import io.gatling.core.action.Action
import io.gatling.core.action.builder.ActionBuilder
import io.gatling.core.session._
import io.gatling.core.structure.ScenarioContext
import io.gatling.core.util.NameGen
import io.github.gatling.udp.{UdpAttributes, UdpProtocol}

class UdpSendActionBuilder(attr: UdpAttributes) extends ActionBuilder with NameGen {

  override def build(ctx: ScenarioContext, next: Action): Action =  {
    val udpProtocol = ctx.protocolComponentsRegistry.protocols.protocol[UdpProtocol].getOrElse(
      throw new UnsupportedOperationException("UDP protocol wasn't registered"))

    new UdpSendAction("UDP:" + attr.tag, attr.message, next, ctx.system, ctx.coreComponents.statsEngine, udpProtocol)
  }

}
