import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "DLProxy"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
    javaCore,
    javaJdbc,
    javaEbean,

    "commons-io" % "commons-io" % "2.4",

    "org.webjars" % "webjars-play" % "2.1.0-1",
    "org.webjars" % "html5shiv" % "3.6.2",
    "org.webjars" % "jquery" % "2.0.0",
    "org.webjars" % "bootstrap" % "2.3.1-1"
  )

  val main = play.Project(appName, appVersion, appDependencies).settings(
    // Add your own project settings here      
  )

}
