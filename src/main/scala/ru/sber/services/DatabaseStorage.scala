package ru.sber.services

import slick.jdbc.PostgresProfile.api._
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class DatabaseStorage extends Storage {
  private val db = Database.forConfig("slick.db")

  private class StorageTable(tag: Tag) extends Table[(String, String)](tag, "storage") {
    def key = column[String]("key", O.PrimaryKey)
    def value = column[String]("value")
    def * = (key, value)
  }

  private val storage = TableQuery[StorageTable]

  override def put(key: String, value: String): Future[String] = {
    val query = storage.insertOrUpdate((key, value))
    db.run(query).map(_ => s"Key '$key' with value '$value' added to storage.")
  }

  override def get(key: String): Future[Option[String]] = {
    val query = storage.filter(_.key === key).map(_.value).result.headOption
    db.run(query)
  }
}