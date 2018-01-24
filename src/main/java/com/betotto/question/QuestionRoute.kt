package com.betotto.question

import org.apache.camel.builder.RouteBuilder


class QuestionRoute: RouteBuilder() {

    override fun configure() {
        from("direct:questionRoute")
            .routeId("questionGroupe")
            .routeDescription("Servicios para consultar las preguntas")
            .process(QuestionIn())
            .choice()
                .`when`(header("operationName").isEqualTo("getAllQuestions"))
                    .to("mybatis:getAllQuestions?statementType=SelectList")
                .endChoice()
                .`when`(header("operationName").isEqualTo("getQuestionById"))
                    .to("mybatis:getQuestionById?statementType=SelectOne")
                .endChoice()
                .`when`(header("operationName").isEqualTo("addQuestion"))
                    .to("mybatis:addQuestion?statementType=Insert&outputHeader=newQuestion")
                .endChoice()
            .end()
            .process(QuestionOut())
    }
}