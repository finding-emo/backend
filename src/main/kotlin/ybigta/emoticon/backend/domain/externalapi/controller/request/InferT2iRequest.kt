package ybigta.emoticon.backend.domain.externalapi.controller.request

import java.io.Serializable

data class InferT2iRequest(
    val prompt: String,
) : Serializable