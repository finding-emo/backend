package ybigta.emoticon.backend.infra.keywordmodel

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import ybigta.emoticon.backend.infra.keywordmodel.request.KeywordModelExtractRequest
import ybigta.emoticon.backend.infra.keywordmodel.response.KeywordModelExtractResponse

@FeignClient(
    "KeywordModelClient",
    url = "http://localhost:9000",
)
interface KeywordModelClient {
    @PostMapping("/")
    fun extract(
        request: KeywordModelExtractRequest,
    ): KeywordModelExtractResponse
}

