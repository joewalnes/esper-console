import javax.servlet.http.HttpServlet

class WebServer(port: Int) {

	import java.util._
	import javax.ws.rs.core.Application
	import com.sun.jersey.spi.container.servlet.ServletContainer
	import org.mortbay.jetty.Server
	import org.mortbay.jetty.servlet.{Context,ServletHolder}

	val server = new Server(port)
	val context = new Context(server, "/")

	val jerseyResources = new HashSet[AnyRef]()
	addServlet("/", new ServletContainer(new Application {
		override def getClasses(): Set[Class[_]] = Collections.emptySet()
		override def getSingletons(): Set[AnyRef] = jerseyResources
	}))

	def addServlet(path: String, servlet: HttpServlet) = context.addServlet(new ServletHolder(servlet), path)
	def addResource(resource: AnyRef) = jerseyResources.add(resource)		

	def start = server.start()
	
}
