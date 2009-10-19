import javax.ws.rs._
import com.espertech.esper.client._

package resources {

	@Path("/esper/statements")
	class EsperStatementsResource(esperAdmin: EPAdministrator) extends JsonSerializer {

		@GET @Path("/") @Produces(Array("text/json"))
		def listStatements: String = toJson(for(name <- esperAdmin.getStatementNames()) yield  name)
		
		@GET @Path("/{name}") 
		def getStatement(@PathParam("name") name: String): String = {
			"Statement: " + name
		}
		
	}

	trait JsonSerializer {
		import com.google.gson.Gson
		
		private val gson = new Gson
		
		def toJson(obj: Any): String = gson.toJson(obj)
		def fromJson[T](json: String, cls: Class[T]): T = gson.fromJson(json, cls)
	}

}