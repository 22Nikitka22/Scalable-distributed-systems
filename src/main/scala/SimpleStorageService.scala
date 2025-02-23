import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route

import scala.collection.mutable
import scala.concurrent.ExecutionContextExecutor

object SimpleStorageService {
  private val storage = mutable.HashMap[String, String]()

  // Маршруты
  private val routes: Route =
    path("put") {
      post {
        parameters("key", "value") { (key, value) =>
          storage.put(key, value)
          complete(s"Key '$key' with value '$value' added to storage.")
        }
      }
    } ~
      path("get") {
        get {
          parameters("key") { key =>
            storage.get(key) match {
              case Some(value) => complete(s"Value for key '$key' is '$value'.")
              case None => complete(s"Key '$key' not found.")
            }
          }
        }
      }

  def main(args: Array[String]): Unit = {
    implicit val system: ActorSystem[Nothing] = ActorSystem(Behaviors.empty, "SimpleStorageService")
    implicit val executionContext: ExecutionContextExecutor = system.executionContext

    val bindingFuture = Http().newServerAt("localhost", 8080).bind(routes)

    println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")
    scala.io.StdIn.readLine()
    bindingFuture
      .flatMap(_.unbind())
      .onComplete(_ => system.terminate())
  }
}