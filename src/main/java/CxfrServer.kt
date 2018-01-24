import org.apache.camel.builder.RouteBuilder


class CxfrServer: RouteBuilder() {

    private val resourceClasses = "com.betotto.question.QuestionApi"
    private val uri= "cxfrs://http://{{host}}:{{port}}/wizard?resourceClasses=$resourceClasses&providers=#restJacksonProviderList"

    override fun configure() {
        from(uri)
            .routeId("Wizard Group rest services")
                .choice()
                    .`when`(header("CamelHttpPath").startsWith("/question"))
                        .to("direct:questionRoute")
                    .endChoice()
                .end()
    }
}