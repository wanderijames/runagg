name := "runagg"

version := "0.1"

scalaVersion := "2.12.4"

val scope = "provided"

val sparkVersion = "2.4.4"

resolvers ++= Seq(
  "apache-snapshots" at "http://repository.apache.org/snapshots/"
)

resolvers ++= Seq("Bintray sbt plugin releases"
  at "http://dl.bintray.com/sbt/sbt-plugin-releases/")

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion % scope,
  "org.apache.spark" %% "spark-sql" % sparkVersion % scope
)

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.1" % "test"
