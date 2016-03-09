package computerdatabase // 1

import io.gatling.core.Predef._ // 2
import io.gatling.http.Predef._
import scala.concurrent.duration._

/**
  * Created by dayani on 09/03/16.
  */
class ProducAsyncSimulation extends Simulation {

  val httpConf = http // 4
    .baseURL("http://localhost:8080") // 5
    .acceptHeader("text/html,application/xhtml+xml,application/json;q=0.9,*/*;q=0.8") // 6
    .doNotTrackHeader("1")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader("Mozilla/5.0 (Windows NT 5.1; rv:31.0) Gecko/20100101 Firefox/31.0")

  val scn = scenario("ProductSimulation")  // 7
    .during(60){
    exec(http("request_1")  // 8
      .get("/api/products/async")) // 9
      .pause(1000 milliseconds, 3000 milliseconds) // 10
  }
  setUp( // 11
    scn.inject(atOnceUsers(300)) // 12
  ).protocols(httpConf) // 13
}
