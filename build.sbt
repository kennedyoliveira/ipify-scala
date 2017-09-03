import java.net.URL

import Dependencies._

lazy val commonsSettings = Seq(
  version := "1.0.0",
  organization := "com.github.kennedyoliveira",
  organizationName := "Kennedy Oliveira",
  organizationHomepage := Some(new URL("https://github.com/kennedyoliveira")),
  description := "Client library for ipify",
  scalaVersion := "2.12.3",
  crossScalaVersions := Seq("2.12.3", "2.11.11")
)

lazy val `ipify-scala` = (project in file("."))
  .settings(
    name := "ipify-scala",
    licenses += ("MIT", url("http://opensource.org/licenses/MIT")),
    useGpg := true,
    pomExtra := pom,
    commonsSettings,
    libraryDependencies ++= Seq(scalaTest % Test)
  )

lazy val pom =
  <developers>
    <developer>
      <id>kennedyoliveira</id>
      <name>Kennedy Oliveira</name>
      <email>kennedy.oliveira@outlook.com</email>
      <roles>
        <role>owner</role>
        <role>developer</role>
        <role>notify</role>
      </roles>
    </developer>
  </developers>
  <scm>
      <url>https://github.com/kennedyoliveira/ipify-scala</url>
  </scm>