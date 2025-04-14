package ru.sber.services

import scala.collection.mutable
import scala.concurrent.Future

class HashMapStorage extends Storage {
  private val storage = mutable.HashMap[String, String]()

  override def put(key: String, value: String): Future[String] = {
    storage.put(key, value)
    Future.successful(s"Key '$key' with value '$value' added to storage.")
  }

  override def get(key: String): Future[Option[String]] = {
    Future.successful(storage.get(key))
  }
}