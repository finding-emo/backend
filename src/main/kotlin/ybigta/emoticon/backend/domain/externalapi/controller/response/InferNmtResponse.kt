package ybigta.emoticon.backend.domain.externalapi.controller.response

import java.io.Serializable

data class InferNmtResponse(
    val translatedText: String,
) : Serializable