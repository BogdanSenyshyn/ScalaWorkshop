val model = Project("model", file("model"))

val root = Project(id = "ScalaWorkshop", base = file("."))
  .settings(
    name := "example",

    version := "0.1",

    scalaVersion := "2.12.4",

    libraryDependencies ++= Seq(
      "org.postgresql" % "postgresql" % "9.3-1100-jdbc41",
      "org.slf4j" % "slf4j-nop" % "1.6.4",
      "com.typesafe.slick" %% "slick" % "3.2.1",
      "ch.qos.logback" % "logback-classic" % "1.1.2"
    )
  ).dependsOn(model)