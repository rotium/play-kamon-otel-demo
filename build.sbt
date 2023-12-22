name := """play-kamon-otel-demo"""
organization := "demo"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala, JavaAgent)

scalaVersion := "2.13.8"

libraryDependencies += guice
libraryDependencies += ws
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "5.1.0" % Test

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "demo.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "demo.binders._"

val kamonVersion = "2.7.0"
val kamon = Seq(
  "io.kamon" %% "kamon-bundle" % kamonVersion,
  "org.reactivemongo" %% "reactivemongo-kamon" % "1.1.0-RC11",
)
val kamonReporter = Seq(
  "io.kamon" %% "kamon-apm-reporter" % kamonVersion,
  "io.kamon" %% "kamon-opentelemetry" % kamonVersion,
  //    "io.kamon" %% "kamon-datadog" % kamonVersion,
)
libraryDependencies ++= kamon ++ kamonReporter

val guiceWorkAround = Seq(
  "com.google.inject"            % "guice"                % "5.1.0",
  "com.google.inject.extensions" % "guice-assistedinject" % "5.1.0"
)
libraryDependencies ++= guiceWorkAround