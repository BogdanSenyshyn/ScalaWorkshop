package model
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.Future

case class Staff (id: Option[Long], name: String, rate: Double, age: Int)

class StaffTable(tag: Tag) extends Table[Staff](tag, "staff") {
  val id = column[Long]("id", O.PrimaryKey)
  val name = column[String]("name")
  val rate = column[Double]("rate")
  val age = column[Int]("age")

  def * = (id.?, name, rate, age) <> (Staff.apply _ tupled, Staff.unapply) //this is better than an example, tupled - 2 paramet
  // ers - 1 parameter for previous tuple
}

object StaffTable {
  val table = TableQuery[StaffTable]
}

class StaffRepository(db: Database) {
  def create(staff: Staff): Future[Staff] = db.run(StaffTable.table returning StaffTable.table += staff)
}