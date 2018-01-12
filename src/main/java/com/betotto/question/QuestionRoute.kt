package com.betotto.question

import org.apache.camel.builder.RouteBuilder


class QuestionRoute: RouteBuilder() {

    override fun configure() {
        from("cxfrs://http://{{host}}:{{port}}/wizard/question?resourceClasses=com.betotto.question.QuestionApi")
            .routeId("questionGroupe")
            .routeDescription("Servicios para consultar las preguntas")
            .process(QuestionIn())
            .process(QuestionOut())
    }
}