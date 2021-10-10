ThisBuild / scalaVersion := "2.13.6"
ThisBuild / organization := "ru.delimobil"

val cabbitVersion = "0.1.0"

name := "cabbit-example"

libraryDependencies ++=
  Seq(
    "ru.delimobil" %% "cabbit" % cabbitVersion,
    "ru.delimobil" %% "cabbit-circe" % cabbitVersion,
    "io.circe" %% "circe-generic" % "0.14.1",
  )
