lazy val commonsSettings = Seq(
  version := "1.0.0",
  scalaVersion := "2.12.3"
)

import Dependencies._

lazy val `ipify-scala` = (project in file("."))
  .settings(
    name := "ipify-scala",
    commonsSettings,
    libraryDependencies ++= Seq(scalaTest % Test)
  )