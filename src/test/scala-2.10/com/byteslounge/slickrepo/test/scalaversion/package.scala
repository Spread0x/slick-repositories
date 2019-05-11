/*
 * MIT License
 *
 * Copyright (c) 2017 Gonçalo Marques
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

package com.spread0x.slickrepo.test

package object scalaversion {
  type H2Profile = slick.driver.H2Driver
  type OracleProfile = com.typesafe.slick.driver.oracle.OracleDriver
  val DB2Profile = com.typesafe.slick.driver.db2.DB2Driver
  val SQLServerProfile = com.typesafe.slick.driver.ms.SQLServerDriver
  val OracleProfile = com.typesafe.slick.driver.oracle.OracleDriver
  val H2Profile = slick.driver.H2Driver
  val MySQLProfile = slick.driver.MySQLDriver
  val PostgresProfile = slick.driver.PostgresDriver
  val DerbyProfile = slick.driver.DerbyDriver
  val HsqldbProfile = slick.driver.HsqldbDriver
}
