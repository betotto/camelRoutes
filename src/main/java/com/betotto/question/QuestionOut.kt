package com.betotto.question

import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.camel.Exchange
import org.apache.camel.Processor
import org.slf4j.LoggerFactory

import javax.ws.rs.core.Response

class QuestionOut: Processor {

    private val log = LoggerFactory.getLogger(QuestionOut::class.java)

    override fun process(exchange: Exchange) {

        val mapper = ObjectMapper();
        val operationName = exchange.`in`.getHeader("operationName") as String

        log.info("operationName: $operationName sending objects")

        var status = Response.Status.OK
        var content = ""

        when(operationName) {
            QuestionOperations.GET_ALL_QUESTIONS.value -> {
                status = Response.Status.OK
                content = mapper.writeValueAsString(exchange.`in`.body)
            }
            QuestionOperations.ADD_QUESTION.value -> {
                val body = exchange.`in`.getBody(List::class.java)
                status = Response.Status.CREATED
                content = mapper.writeValueAsString(body[0])
            }
            QuestionOperations.GET_QUESTION_BY_ID.value -> {
                if (exchange.`in`.body != null) {
                    content = mapper.writeValueAsString(exchange.`in`.body)
                } else {
                    status = Response.Status.NOT_FOUND
                }
            }
        }

        val response = Response.status(status).entity(content).build()
        exchange.out.body = response
    }
}