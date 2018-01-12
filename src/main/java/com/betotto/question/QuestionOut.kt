package com.betotto.question

import com.google.gson.Gson
import org.apache.camel.Exchange
import org.apache.camel.Processor
import org.slf4j.LoggerFactory

import javax.ws.rs.core.Response

class QuestionOut: Processor {

    private val log = LoggerFactory.getLogger(QuestionOut::class.java)

    override fun process(exchange: Exchange) {

        val gson = Gson()
        val operationName = exchange.`in`.getHeader("operationName") as String

        log.info("operationName: $operationName sending objects")

        val questions = arrayOf(
            Question(1, "Porque?"),
            Question(2, "Como?"),
            Question(3, "Donde?"),
            Question(4, "Cuando?")
        )

        var status = Response.Status.OK

        var content = ""

        when(operationName) {
            QuestionOperations.GET_ALL_QUESTIONS.value -> {
                content = gson.toJson(questions)
            }
            QuestionOperations.GET_QUESTION_BY_ID.value -> {
                val questionInput = exchange.`in`.body as Question
                val question = questions.filter { q -> questionInput.id == q.id }
                if(question.isNotEmpty()) {
                    content = gson.toJson(question[0])
                } else {
                    content = ""
                    status = Response.Status.NOT_FOUND
                }

            }
        }

        val response = Response.status(status).entity(content).build()
        exchange.out.body = response
    }
}