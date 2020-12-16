import com.natpryce.konfig.ConfigurationProperties
import com.natpryce.konfig.Key
import com.natpryce.konfig.stringType
import org.apache.logging.log4j.kotlin.Logging

open class AbstractTest: Logging {
    val hhApiHost = Key("hhapi.host", stringType)
    val vacanciesEndpoint = Key("hhapi.vacancyies.endpoint", stringType)
    val token = Key("hhapi.token", stringType)
    val config = ConfigurationProperties.fromResource("environment.properties")
}