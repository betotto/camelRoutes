package com.betotto.question

import javax.ws.rs.GET
import javax.ws.rs.PathParam
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("/")
interface QuestionApi {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun getAllQuestions(): Response

    @GET
    @Path("/{idQuestion}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getQuestionById(@PathParam("idQuestion") idQuestion: Int): Response
}
