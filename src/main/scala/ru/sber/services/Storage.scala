package ru.sber.services

import scala.concurrent.Future

trait Storage {
  def put(key: String, value: String): Future[String]
  def get(key: String): Future[Option[String]]
}