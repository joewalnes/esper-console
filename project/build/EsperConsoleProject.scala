import sbt._

class EsperConsoleProject(info: ProjectInfo) extends DefaultProject(info) {
  
	// Dependencies
	val jetty = "org.mortbay.jetty" % "jetty" % "6.1.19"
	val jerseyServey = "com.sun.jersey" % "jersey-server" % "1.1.3-ea"
	//val jerseyJson = "com.sun.jersey" % "jersey-json" % "1.1.3-ea"
	val gson = "com.google.code.gson" % "gson" % "1.4"
	val esper = "com.espertech" % "esper" % "3.2.0"

	val sunRepository = "Sun Repository" at "http://download.java.net/maven/2"
	val gsonRepository = "GSON Repository" at "http://google-gson.googlecode.com/svn/mavenrepo"

}