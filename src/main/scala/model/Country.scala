package model
import slick.jdbc.PostgresProfile.api._


case class Country (id: Option[Long], title: String)//option because of autoinc

// Definition of the SUPPLIERS table
class CountryTable(tag: Tag) extends Table[Country](tag, "countries") {
  val id = column[Long]("id", O.PrimaryKey)
  val title = column[String]("title")

  def * = (id.?, title) <> (Country.apply _ tupled, Country.unapply) //this is better than an example, tupled - 2 paramet
  // ers - 1 parameter for previous tuple
}
