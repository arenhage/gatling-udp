name := "Gatling-UDP"

version := "2.0.0"
scalaVersion := "2.12.0"
organization := "io.github.gatling.udp"

libraryDependencies ++= Seq(
  "io.gatling" % "gatling-core" % "2.3.0" % "provided"
)

// Gatling contains scala-library
assemblyOption in assembly := (assemblyOption in assembly).value
  .copy(includeScala = false)