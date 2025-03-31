package ru.sber.services

import com.typesafe.config.ConfigFactory
import scala.concurrent.Future

class StorageService {
  private val config = ConfigFactory.load()
  private val storageType = config.getString("storage.type")

  private val storage: Storage = storageType match {
    case "database" => new DatabaseStorage
    case "hashmap"  => new HashMapStorage
    case _          => throw new IllegalArgumentException(s"Unknown storage type: $storageType")
  }

  def put(key: String, value: String): Future[String] = storage.put(key, value)
  def get(key: String): Future[Option[String]] = storage.get(key)
}