/*
 * MIT License
 *
 * Copyright (c) 2016 Gonçalo Marques
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

package com.spread0x.slickrepo.datetime
import java.time.{Instant, LocalDateTime}

object MockDateTimeHelper {
  def start(): Unit = {
    MockDateTimeProvider.reset()
    DateTimeHelper.setDateTimeProvider(MockDateTimeProvider)
  }
  def mock(instants: Instant*): Unit = {
    MockDateTimeProvider.mock(instants)
  }
  def restore(): Unit = {
    DateTimeHelper.restore()
  }
}

private object MockDateTimeProvider extends DateTimeProvider {
  var instants: Seq[Instant] = _
  def reset(): Unit = {
    instants = Seq()
  }
  override def currentInstant: Instant = {
    val result: Instant = instants.head
    instants = instants.tail
    result
  }
  override def currentLocalDateTime: LocalDateTime = {
    LocalDateTime.ofInstant(currentInstant, DateTimeHelper.localDateTimeZone)
  }
  def mock(instants: Seq[Instant]): Unit = {
    instants.foreach(instant => this.instants = this.instants :+ instant)
  }
}
