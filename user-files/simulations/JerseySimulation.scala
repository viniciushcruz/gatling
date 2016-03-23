package computerdatabase // 1

import io.gatling.core.Predef._ // 2
import io.gatling.http.Predef._
import scala.concurrent.duration._

class JerseySimulation extends Simulation { // 3

  val httpConf = http // 4
    .baseURL("http://localhost:8090") // 5
    .acceptHeader("application/json;q=0.9,*/*;q=0.8") // 6
    .doNotTrackHeader("1")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptEncodingHeader("gzip, deflate")

  val scn = scenario("JerseySimulation")  // 7
  .during(300){
     exec(http("request_1")  // 8
    .get("/jerseyendpoint?size=100")) // 9
     .pause(1000 milliseconds, 3000 milliseconds) // 10
	}
  setUp( // 11
    scn.inject(atOnceUsers(3000)) // 12
  ).protocols(httpConf) // 13
}
