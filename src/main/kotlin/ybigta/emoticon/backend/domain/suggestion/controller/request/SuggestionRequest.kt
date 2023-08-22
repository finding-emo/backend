package ybigta.emoticon.backend.domain.suggestion.controller.request

import java.io.Serializable

data class SuggestionRequest(
    val text: String,
    val clientId: String,
) : Serializable