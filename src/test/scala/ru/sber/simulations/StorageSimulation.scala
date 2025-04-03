package ru.sber.simulations

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder
import scala.concurrent.duration._

class StorageSimulation extends Simulation {

  val httpProtocol: HttpProtocolBuilder = http
    .baseUrl("http://localhost:8080")
    .acceptHeader("text/plain")

  val scn: ScenarioBuilder = scenario("Storage Service Simulation")
    .exec(http("Put Request")
      .post("/put")
      .formParam("key", "testKey")
      .formParam("value", "testValue"))
    .pause(1)
    .exec(http("Get Request")
      .get("/get")
      .queryParam("key", "testKey"))

  setUp(
    scn.inject(rampUsersPerSec(10) to 125 during (60.seconds))
  ).protocols(httpProtocol)
}