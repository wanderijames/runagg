
name := "runagg"

version := "0.1"

scalaVersion := "2.11.12"

val scope = "provided"

val sparkVersion = "2.3.1"

resolvers ++= Seq(
  "apache-snapshots" at "http://repository.apache.org/snapshots/"
)

resolvers ++= Seq("Bintray sbt plugin releases"
  at "http://dl.bintray.com/sbt/sbt-plugin-releases/")

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core"  %  sparkVersion % scope,
  "org.apache.spark" %% "spark-sql"   %  sparkVersion % scope
)

libraryDependencies += "com.github.scopt" %  "scopt_2.11" % "3.7.1"

libraryDependencies += "org.scalactic" %% "scalactic" % "3.1.0"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.1.0" % "test"

