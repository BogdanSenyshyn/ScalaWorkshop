import model._
import implicits._
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.Future

case class Country (id: Option[Long], title: String)//option because of autoinc

// Definition of the SUPPLIERS table
class CountryTable(tag: Tag) extends Table[Country](tag, "countries") {
  val id = column[Long]("id", O.PrimaryKey)
  val title = column[String]("title")

  def * = (id.?, title) <> (Country.apply _ tupled, Country.unapply) //this is better than an example, tupled - 2 paramet
  // ers - 1 parameter for previous tuple
}


object CountryTable {
  val table = TableQuery[CountryTable]
}

class CountryRepository(db: Database) {
  val countryTableQuery = TableQuery[CountryTable]
  def create(country: Country): Future[Country] = db.run(CountryTable.table returning CountryTable.table += country)
  def country(country: Country): Future[Country] = db.run(countryTableQuery returning countryTableQuery += country)
  def update(country: Country): Future[Int] = db.run(countryTableQuery.filter(_.id === country.id).update(country))
  def delete(countryId: Long): Future[Int] = db.run(countryTableQuery.filter(_.id === countryId).delete)
  def getById(countryId: Long): Future[Option[Country]] = db.run(countryTableQuery.filter(_.id === countryId).result.headOption)
}
