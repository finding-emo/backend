package ybigta.emoticon.backend.infra.keywordmodel.request

import ybigta.emoticon.backend.domain.survey.entity.Survey
import java.io.Serializable

data class KeywordModelGeneratePromptRequest(
    val text: String,
    val translatedKeywords: List<String>,
    val survey: Survey,
) : Serializable