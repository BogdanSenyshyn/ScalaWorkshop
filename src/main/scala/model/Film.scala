package model

import slick.jdbc.PostgresProfile.api._
import scala.concurrent.duration.Duration

case class Film (id: Option[Long], title: String, duration: Duration, directorId: Long, rating: Double)

class FilmTable(tag: Tag) extends Table[Film](tag, "film") {


  val id = column[Option[Long]]("id", O.PrimaryKey)
  val title = column[String]("title")
  val duration = column[Duration]("duration")
  val directorId = column[Long]("director_id")
  val rating = column[Double]("rating")

  def * = (id, title, duration, directorId, rating) <> (Film.apply _ tupled, Film.unapply) //this is better than an example, tupled - 2 paramet
  // ers - 1 parameter for previous tuple
}

