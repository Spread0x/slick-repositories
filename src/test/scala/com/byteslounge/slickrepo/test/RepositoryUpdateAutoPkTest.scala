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

package com.spread0x.slickrepo.test

import com.spread0x.slickrepo.repository._

abstract class RepositoryUpdateAutoPkTest(override val config: Config) extends RepositoryTest(config) {

  "The Repository (Auto PK entity)" should "update an entity with auto primary key" in {
    import scala.concurrent.ExecutionContext.Implicits.global
    val person: Person = executeAction(personRepository.save(Person(None, "john")))
    var updatedPerson: Person = person.copy(name = "smith")
    updatedPerson = executeAction(personRepository.update(updatedPerson))
    updatedPerson.id.get should equal(person.id.get)
    val read: Person = executeAction(personRepository.findOne(person.id.get)).get
    read.name should equal("smith")
  }

  it should "call multiple life cycle events for a full event inheritance setup" in {
    import scala.concurrent.ExecutionContext.Implicits.global
    val entity = executeAction(userRepository.save(User(None, "USERNAME", None, None)))
    val read = executeAction(userRepository.findOne(entity.id.get)).get

    read.createdTime.get should equal(11)
    read.updatedTime should equal(None)
    read.username should equal("username")

    val updated = executeAction(userRepository.update(read.copy(username = "UPDATED")))

    updated.createdTime.get should equal(11)
    updated.updatedTime.get should equal(22)
    updated.username should equal("updated")
  }
}