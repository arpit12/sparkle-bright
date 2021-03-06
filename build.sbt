name := "sparkle-bright"

version := "1.0"

scalaVersion := "2.10.4"

libraryDependencies += "org.apache.spark" %% "spark-core" % "1.0.2"

libraryDependencies += "org.apache.spark" %% "spark-streaming" % "1.0.2"

libraryDependencies += "org.apache.spark" %% "spark-mllib" % "1.0.2"

libraryDependencies += "org.apache.spark" %% "spark-graphx" % "1.0.2"

resolvers += "Akka Repository" at "http://repo.akka.io/releases/"

