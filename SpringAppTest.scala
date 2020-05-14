/*
 * Copyright 2011-2019 GatlingCorp (https://gatling.io)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package computerdatabase.advanced

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._
import java.util.concurrent.ThreadLocalRandom

class SpringAppTest extends Simulation {

  object GetOne {

    val get = exec(
      http("GetOne")
        .get("/foo/1")
        //.check(status.is(200))
    )
  }

  object Post {

    val post = {
      exec(
        http("Post")
          .post("/foo")
          .body(StringBody("""{ "key": 1, "att1": "a", "att2": "b" }""")).asJson
      )
    }
  }

  object GetAll {

    val getall = {
      exec(
        http("GetAll")
          .get("/foo")
      )
    }
  }

  val httpProtocol = http
    .baseUrl("http://localhost:8080")
    
  val usersAdd = scenario("UsersAdd").exec(Post.post)
  val usersGet = scenario("UsersGet").exec(GetOne.get)
  val usersGetAll = scenario("UsersGetAll").exec(GetAll.getall)

  setUp(
    usersAdd.inject(rampUsers(100) during (0 seconds)),
    usersGet.inject(rampUsers(100) during (0 seconds)),
    usersGetAll.inject(rampUsers(100) during (0 seconds)),
  ).protocols(httpProtocol)
}
