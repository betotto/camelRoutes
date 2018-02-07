import org.apache.camel.CamelContext
import org.apache.camel.impl.DefaultCamelContext

import com.betotto.question.QuestionRoute
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider

import org.apache.camel.component.properties.PropertiesComponent
import java.util.Arrays
import org.apache.camel.impl.JndiRegistry

fun main(args: Array<String>) {
    val registry = JndiRegistry()
    registry.bind("restJacksonProviderList", Arrays.asList<Any>(JacksonJsonProvider(),
            InvalidBodyProvider()))
    val context: CamelContext = DefaultCamelContext(registry)

    val props = PropertiesComponent()
    props.setLocation("classpath:camel.properties")
    context.addComponent("properties", props)

    context.addRoutes(CxfrServer())
    context.addRoutes(StaticServer())
    context.addRoutes(QuestionRoute())

    context.start()
}
