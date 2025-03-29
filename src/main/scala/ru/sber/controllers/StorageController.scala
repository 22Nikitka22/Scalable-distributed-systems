package ru.sber.controllers

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import ru.sber.services.StorageService

object StorageController {
  private val storageService = new StorageService()

  val routes: Route =
    path("put") {
      post {
        parameters("key", "value") { (key, value) =>
          complete(storageService.put(key, value))
        }
      }
    } ~
      path("get") {
        get {
          parameters("key") { key =>
            storageService.get(key) match {
              case Some(value) => complete(value)
              case None => complete((404, s"Key '$key' not found."))
            }
          }
        }
      }
}