package ru.sber.controllers

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import ru.sber.services.StorageService

object StorageController {
  private val storageService = new StorageService()

  val routes: Route =
    path("put") {
      post {
        formFields("key", "value") { (key, value) =>
          onSuccess(storageService.put(key, value)) { response =>
            complete(response)
          }
        }
      }
    } ~
      path("get") {
        get {
          parameters("key") { key =>
            onSuccess(storageService.get(key)) {
              case Some(value) => complete(value)
              case None => complete((404, s"Key '$key' not found."))
            }
          }
        }
      }
}