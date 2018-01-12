import org.apache.camel.CamelContext
import org.apache.camel.impl.DefaultCamelContext
import org.apache.camel.impl.SimpleRegistry

import com.betotto.question.QuestionRoute

import org.apache.camel.component.properties.PropertiesComponent

fun main(args: Array<String>) {
    val simpleRegistry = SimpleRegistry()
    val context: CamelContext = DefaultCamelContext(simpleRegistry)

    val props = PropertiesComponent()
    props.setLocation("classpath:camel.properties")
    context.addComponent("properties", props)

    context.addRoutes(QuestionRoute())

    context.start()
}
