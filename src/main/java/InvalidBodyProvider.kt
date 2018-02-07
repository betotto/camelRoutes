import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException
import org.slf4j.LoggerFactory
import javax.ws.rs.core.Response
import javax.ws.rs.ext.ExceptionMapper

@javax.ws.rs.ext.Provider
class InvalidBodyProvider: ExceptionMapper<UnrecognizedPropertyException> {

    private val log = LoggerFactory.getLogger(InvalidBodyProvider::class.java)

    override fun toResponse(exception: UnrecognizedPropertyException): Response {
        var message = "Los parametros de entrada no son validos, por favor verifica tu peticion"
        log.info(message)
        return Response.status(Response.Status.BAD_REQUEST).entity(message).build()
    }
}
