package com.betotto.question

import org.apache.camel.Exchange
import org.apache.camel.Processor
import org.slf4j.LoggerFactory

class QuestionIn: Processor {

    private val log = LoggerFactory.getLogger(QuestionIn::class.java)

    override fun process(exchange :Exchange) {
        val operationName = exchange.`in`.getHeader("operationName") as String
        log.info("operationName: $operationName receiving objects")
        when(operationName) {
            QuestionOperations.GET_QUESTION_BY_ID.value -> {
                val body = exchange.`in`.getBody(List::class.java)
                val question = Question(body[0] as Int)
                exchange.out.body = question
                exchange.out.headers = exchange.`in`.headers
            }
        }
    }
}
