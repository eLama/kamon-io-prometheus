resolvers += "Elama Release" at "http://artifactory.navy.elama.ru:8081/artifactory/libs-release-local"

lazy val root: Project = project.in(file(".")).dependsOn(latestSbtUmbrella)
lazy val latestSbtUmbrella = RootProject(
  uri("git://github.com/kamon-io/kamon-sbt-umbrella.git"))
addSbtPlugin("com.github.gseitz" % "sbt-release" % "1.0.6")
addSbtPlugin("com.elama" % "navy-sbt-houserules" % "0.0.19")
