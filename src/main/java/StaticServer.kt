import org.apache.camel.Message
import org.apache.camel.builder.RouteBuilder
import javax.ws.rs.core.MediaType

class StaticServer: RouteBuilder() {

    private enum class FileTypes(val value: String) {
        HTML("html"),
        JS("js"),
        CSS("css")
    }
    
    private fun getMessageOut(fileExtension: String, file: String, messageOut: Message) = {
        messageOut.setHeader("ContentType", mimeType(fileExtension))
        messageOut.body = staticFile(file)
        messageOut
    }

    private fun mimeType(fileExtension: String =  "/index.html") : String {
        return when (fileExtension) {
            FileTypes.HTML.value -> MediaType.TEXT_HTML
            FileTypes.JS.value -> "application/javascript"
            FileTypes.CSS.value -> "text/css"
            else -> MediaType.TEXT_PLAIN
        }
    }

    private fun staticFile(file: String =  "/index.html") : String {
        val fileName = "webapp$file"
        return StaticServer::class.java.classLoader.getResource(fileName).readText()
    }

    override fun configure() {
        from("undertow:http://{{staticHost}}:{{staticPort}}/wizard")
            .choice()
                .`when`(header("CamelHttpPath").startsWith("/question"))
                    .to("undertow:http://{{host}}:{{port}}/wizard/question?enableOptions=true")
                .endChoice()
                .otherwise()
                    .process({ exchange ->
                        val file = exchange.`in`.getHeader("CamelHttpPath").toString()
                        val fileExtension = file.substringAfter(".")
                        val fileGetter = getMessageOut(fileExtension, file, exchange.out)
                        exchange.out = fileGetter()
                    })
                .endChoice()
            .end()

    }
}