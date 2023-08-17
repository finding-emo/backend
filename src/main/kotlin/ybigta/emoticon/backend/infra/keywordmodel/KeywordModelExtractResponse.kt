package ybigta.emoticon.backend.infra.keywordmodel

import java.io.Serializable

data class KeywordModelExtractResponse(
    val keywords: List<Keyword>,
) : Serializable {
    data class Keyword(
        val keyword: String,
        val score: Double,
    )
}