package ybigta.emoticon.backend.infra.keywordmodel

import java.io.Serializable

data class KeywordModelExtractRequest(
    val text: String,
) : Serializable