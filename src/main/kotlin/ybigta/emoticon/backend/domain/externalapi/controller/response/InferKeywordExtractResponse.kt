package ybigta.emoticon.backend.domain.externalapi.controller.response

import java.io.Serializable

data class InferKeywordExtractResponse(
    val keywords: List<String>,
) : Serializable