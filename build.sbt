ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.16"

lazy val root = (project in file("."))
  .settings(
    name := "simple-storage-service"
  )

scalacOptions := Seq(
  "-encoding", "UTF-8", "-release:8", "-deprecation",
  "-feature", "-unchecked", "-language:implicitConversions", "-language:postfixOps")

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http" % "10.5.3",
  "com.typesafe.akka" %% "akka-stream" % "2.8.7",
  "com.typesafe.slick" %% "slick" % "3.5.2",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.5.2",
  "org.postgresql" % "postgresql" % "42.7.4",
  "io.gatling" % "gatling-core" % "3.13.5" % "test",
  "io.gatling" % "gatling-http" % "3.13.5" % "test",
  "io.gatling" % "gatling-test-framework" % "3.13.5" % "test",
  "io.gatling" % "gatling-charts" % "3.13.5" % "test",
  "io.gatling.highcharts" % "gatling-charts-highcharts" % "3.13.5" % "test"
)

enablePlugins(GatlingPlugin)