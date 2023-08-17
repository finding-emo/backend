package ybigta.emoticon.backend.domain.externalapi.controller.request

import java.io.Serializable

data class InferKeywordExtractRequest(
    val text: String,
) : Serializable