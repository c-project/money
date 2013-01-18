// Comment to get more information during initialization
logLevel := Level.Warn

// The Typesafe repository 
resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

resolvers += "olegychRepo" at "https://bitbucket.org/olegych/mvn/raw/default"

resolvers += "remeniuk repo" at "http://remeniuk.github.com/maven"

// Use the Play sbt plugin for Play projects
//addSbtPlugin("com.olegych" % "sbt-sources-plugin" % "0.2.0")

addSbtPlugin("play" % "sbt-plugin" % "2.0.4")

libraryDependencies += "org.netbeans" %% "sbt-netbeans-plugin" % "0.1.4"
