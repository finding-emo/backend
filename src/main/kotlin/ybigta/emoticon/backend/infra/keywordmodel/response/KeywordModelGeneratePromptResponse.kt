package ybigta.emoticon.backend.infra.keywordmodel.response

import java.io.Serializable

data class KeywordModelGeneratePromptResponse(
    val prompts: List<String>,
    val negativePrompt: String,
) : Serializable