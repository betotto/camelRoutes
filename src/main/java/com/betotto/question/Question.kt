package com.betotto.question

import java.util.Date

data class Question(
        val idQuestion: Int = -1,
        var text: String = "",
        var correctAnswer: Int? = null,
        var creationDate: Date = Date())
