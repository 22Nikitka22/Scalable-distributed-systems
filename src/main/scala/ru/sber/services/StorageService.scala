package ru.sber.services

import scala.collection.mutable

class StorageService {
  private val storage = mutable.HashMap[String, String]()

  def put(key: String, value: String): String = {
    storage.put(key, value)
    s"Key '$key' with value '$value' added to storage."
  }

  def get(key: String): Option[String] = {
    storage.get(key)
  }
}