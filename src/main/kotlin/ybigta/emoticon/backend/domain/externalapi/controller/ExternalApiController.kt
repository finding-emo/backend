package ybigta.emoticon.backend.domain.externalapi.controller

import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ybigta.emoticon.backend.domain.externalapi.controller.request.InferKeywordExtractRequest
import ybigta.emoticon.backend.domain.externalapi.controller.request.InferNmtRequest
import ybigta.emoticon.backend.domain.externalapi.controller.request.InferT2iRequest
import ybigta.emoticon.backend.domain.externalapi.controller.response.InferKeywordExtractResponse
import ybigta.emoticon.backend.domain.externalapi.controller.response.InferNmtResponse
import ybigta.emoticon.backend.domain.externalapi.service.ExternalApiService

@RestController
@RequestMapping("/external")
class ExternalApiController(
    private val externalApiService: ExternalApiService,
) {
    @Operation(summary = "Karlo API의 T2I 추론입니다. 프롬프트에 대한 이미지 1장을 생성합니다.")
    @PostMapping(
        "/t2i",
        produces = [MediaType.IMAGE_JPEG_VALUE],
    )
    fun inferT2i(
        @RequestBody
        request: InferT2iRequest,
    ): ByteArray {
        val negativePrompt = if (request.negativePrompt.isNullOrBlank()) null else request.negativePrompt
        return externalApiService.inferT2iByKarloApi(
            request.prompt,
            negativePrompt,
        )
    }

    @Operation(summary = "Papago NMT API 추론입니다. 텍스트를 한글에서 영어로 번역합니다.")
    @PostMapping("/nmt")
    fun inferNmt(
        @RequestBody
        request: InferNmtRequest,
    ): InferNmtResponse {
        val result = externalApiService.inferNmtByPapagoApi(request.text)

        return InferNmtResponse(result)
    }

    @Operation(summary = "Yake 키워드 추출 추론입니다. 텍스트에서 키워드를 추출합니다.")
    @PostMapping("/keywordExtract")
    fun inferKeywordExtract(
        @RequestBody
        request: InferKeywordExtractRequest,
    ): InferKeywordExtractResponse {
        val result = externalApiService.extractKeywordsByKeywordModel(request.text)

        return InferKeywordExtractResponse(result)
    }
}

