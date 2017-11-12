package model
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.Future

case class Genre (id: Option[Long], title: String, description: Option[String])

class GenreTable(tag: Tag) extends Table[Genre](tag, "genre") {
  val id = column[Long]("id", O.PrimaryKey)
  val title = column[String]("title")
  val description = column[Option[String]]("description")

  def * = (id.?, title, description) <> (Genre.apply _ tupled, Genre.unapply) //this is better than an example, tupled - 2 paramet
  // ers - 1 parameter for previous tuple
}

object GenreTable {
  val table = TableQuery[GenreTable]
}

class GenreRepository(db: Database) {
  def create(genre: Genre): Future[Genre] = db.run(GenreTable.table returning GenreTable.table += genre)
}