import scala.reflect.BeanProperty
import com.espertech.esper.client._

object SampleApp {

	case class Foo(@BeanProperty n: Int, @BeanProperty v: String)
	
	def main(args: Array[String]) {
	
		val esperConfig = new Configuration()
		esperConfig.addEventType(classOf[Foo])

		val esperService = EPServiceProviderManager.getDefaultProvider(esperConfig)
		val esperAdmin = esperService.getEPAdministrator
		
		esperAdmin.createEPL("select count(*) from Foo.win:time(1 sec)", "secondcount")
		esperAdmin.createEPL("select count(*) from Foo.win:time(1 min)", "minutecount")

		val webServer = new WebServer(9999)
		webServer.addResource(new resources.EsperStatementsResource(esperAdmin))
		
		
		webServer.start
		println("Start")
		Thread.sleep(100000)
	}
	
}