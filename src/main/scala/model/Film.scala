package model

import slick.jdbc.PostgresProfile.api._
import scala.concurrent.duration.Duration

case class Film (id: Option[Long], title: String, duration: Duration, directorId: Long, rating: Double)

class FilmTable(tag: Tag) extends Table[Film](tag, "film") {
  val id = column[Long]("id", O.PrimaryKey)
  val title = column[String]("title")
  val duration = column[Duration]("duration")
  val directorId = column[Long]("director_id")
  val rating = column[Double]("rating")

  val directorFk = foreignKey("director_id_fk", directorId, TableQuery[StaffTable])(_.id)
  def * = (id.?, title, duration, directorId, rating) <> (Film.apply _ tupled, Film.unapply) //this is better than an example, tupled - 2 paramet
  // ers - 1 parameter for previous tuple
}

case class FilmToGenre (id: Option[Long], filmId: Long, genreId: Long)
class FilmToGenreTable(tag: Tag) extends Table[(FilmToGenre)](tag, "film_to_genre") {
  val id = column[Long]("id", O.PrimaryKey)
  val filmId = column[Long]("film_id")
  val genreId = column[Long]("genre_id")

  val filmIdFk = foreignKey("film_id_fk", filmId, TableQuery[FilmTable])(_.id)
  val genreIdFk = foreignKey("genre_id_fk", genreId, TableQuery[GenreTable])(_.id)
  def * = (id.?, filmId, genreId) <> (FilmToGenre.apply _ tupled, FilmToGenre.unapply) //this is better than an example, tupled - 2 paramet
  // ers - 1 parameter for previous tuple
}

case class FilmToCast (id: Option[Long], filmId: Long, genreId: Long)
class FilmToCastTable(tag: Tag) extends Table[(FilmToCast)](tag, "film_to_cast") {

  val id = column[Long]("id", O.PrimaryKey)
  val filmId = column[Long]("film_id")
  val staffId = column[Long]("staff_id")


  val filmIdFk = foreignKey("film_id_fk", filmId, TableQuery[FilmTable])(_.id)
  val staffIdFk = foreignKey("staff_id_fk", staffId, TableQuery[StaffTable])(_.id)

  def * = (id.?, filmId, staffId) <> (FilmToCast.apply _ tupled, FilmToCast.unapply) //this is better than an example, tupled - 2 paramet
  // ers - 1 parameter for previous tuple
}

case class FilmToCountry (id: Option[Long], filmId: Long, countryId: Long)
class FilmToCountryTable(tag: Tag) extends Table[(FilmToCountry)](tag, "film_to_cast") {

  val id = column[Long]("id", O.PrimaryKey)
  val filmId = column[Long]("film_id")
  val countryId = column[Long]("country_id")

  val filmIdFk = foreignKey("film_id_fk", filmId, TableQuery[FilmTable])(_.id)
  val countryIdFk = foreignKey("country_id_fk", countryId, TableQuery[CountryTable])(_.id)

  def * = (id.?, filmId, countryId) <> (FilmToCountry.apply _ tupled, FilmToCountry.unapply) //this is better than an example, tupled - 2 paramet
  // ers - 1 parameter for previous tuple
}