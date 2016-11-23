name := "Gatling-UDP"

version := "1.0.15"
scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "io.gatling" % "gatling-core" % "2.2.2" % "provided"
)

// Gatling contains scala-library
assemblyOption in assembly := (assemblyOption in assembly).value
  .copy(includeScala = false)