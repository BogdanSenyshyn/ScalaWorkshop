package model
import slick.jdbc.PostgresProfile.api._

case class Genre (id: Option[Long], title: String, description: Option[String])

class GenreTable(tag: Tag) extends Table[Genre](tag, "genre") {
  val id = column[Option[Long]]("id", O.PrimaryKey)
  val title = column[String]("title")
  val description = column[Option[String]]("description")

  def * = (id, title, description) <> (Genre.apply _ tupled, Genre.unapply) //this is better than an example, tupled - 2 paramet
  // ers - 1 parameter for previous tuple
}
