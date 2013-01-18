import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

    val appName         = "money"
    val appVersion      = "1.0-SNAPSHOT"

    val appDependencies = Seq(
    		"com.github.aselab" % "scala-activerecord" % "0.2-SNAPSHOT",
    		"com.h2database" % "h2" % "1.3.168",
    		"org.slf4j" % "slf4j-nop" % "1.6.6"
    )

    val main = PlayProject(appName, appVersion, appDependencies, mainLang = SCALA).settings(
    		resolvers ++= List("aselab repo" at "http://aselab.github.com/maven/")
    )

}
