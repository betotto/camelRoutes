package com.betotto.question

import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("/question")
interface QuestionApi {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun getAllQuestions(): Response

    @PUT
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    fun addQuestion(question: Question): Response

    @GET
    @Path("/{idQuestion}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getQuestionById(@PathParam("idQuestion") idQuestion: Int): Response

}
