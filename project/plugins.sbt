// Comment to get more information during initialization
logLevel := Level.Warn

// The Typesafe repository
resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

resolvers += "Play2war plugins release" at "http://repository-play-war.forge.cloudbees.com/release/"

// Use the Play sbt plugin for Play projects
addSbtPlugin("play" %% "sbt-plugin" % "2.1.1")

// play2-war-plugin
addSbtPlugin("com.github.play2war" %% "play2-war-plugin" % "1.0")
