package ru.sber

import akka.http.scaladsl.Http
import akka.actor.ActorSystem
import akka.stream.Materializer
import ru.sber.controllers.StorageController

object SimpleStorageService extends App {
  implicit val system: ActorSystem = ActorSystem("simple-storage-service")
  implicit val materializer: Materializer = Materializer(system)

  Http().bindAndHandle(StorageController.routes, "localhost", 8080)
  println("Server online at http://localhost:8080/")
}