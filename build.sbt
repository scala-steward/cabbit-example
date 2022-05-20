ThisBuild / scalaVersion := "2.13.8"
ThisBuild / organization := "ru.delimobil"

val cabbitVersion = "0.1.2"

name := "cabbit-example"

libraryDependencies ++=
  Seq(
    "ru.delimobil" %% "cabbit" % cabbitVersion,
    "ru.delimobil" %% "cabbit-circe" % cabbitVersion,
    "io.circe" %% "circe-generic" % "0.14.2",
  )
