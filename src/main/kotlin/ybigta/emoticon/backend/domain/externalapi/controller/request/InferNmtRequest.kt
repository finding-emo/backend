package ybigta.emoticon.backend.domain.externalapi.controller.request

import java.io.Serializable

data class InferNmtRequest(
    val text: String,
) : Serializable