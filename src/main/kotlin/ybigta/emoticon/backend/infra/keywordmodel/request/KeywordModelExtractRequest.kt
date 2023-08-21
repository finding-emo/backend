package ybigta.emoticon.backend.infra.keywordmodel.request

import java.io.Serializable

data class KeywordModelExtractRequest(
    val text: String,
) : Serializable