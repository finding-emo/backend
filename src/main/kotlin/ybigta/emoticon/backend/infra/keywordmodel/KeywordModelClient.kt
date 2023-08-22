package ybigta.emoticon.backend.infra.keywordmodel

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import ybigta.emoticon.backend.infra.keywordmodel.request.KeywordModelExtractRequest
import ybigta.emoticon.backend.infra.keywordmodel.request.KeywordModelGeneratePromptRequest
import ybigta.emoticon.backend.infra.keywordmodel.response.KeywordModelExtractResponse
import ybigta.emoticon.backend.infra.keywordmodel.response.KeywordModelGeneratePromptResponse

@FeignClient(
    "KeywordModelClient",
    url = "http://localhost:9000",
)
interface KeywordModelClient {
    @PostMapping("/keywords")
    fun extractKeywords(
        request: KeywordModelExtractRequest,
    ): KeywordModelExtractResponse


    @PostMapping("/prompt")
    fun generatePrompt(
        request: KeywordModelGeneratePromptRequest,
    ): KeywordModelGeneratePromptResponse
}

