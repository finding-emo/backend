package ybigta.emoticon.backend.domain.suggestion.controller.response

import io.swagger.v3.oas.annotations.media.Schema
import java.io.Serializable

data class SuggestionResponse(
    @Schema(description = "이모티콘 URL 리스트입니다. string 형태로 3개를 반환합니다.")
    val emoticonUrls: List<String>,
    val detail: Map<String, Any>,
) : Serializable