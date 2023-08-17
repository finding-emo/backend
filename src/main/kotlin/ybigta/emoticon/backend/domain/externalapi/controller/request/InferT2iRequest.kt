package ybigta.emoticon.backend.domain.externalapi.controller.request

import io.swagger.v3.oas.annotations.media.Schema
import java.io.Serializable

data class InferT2iRequest(
    val prompt: String,
    @Schema(description = "옵셔널입니다. 사용하지 않을 경우 빈 문자열 `\"\"` 혹은 `null`을 보내거나, 또는 필드를 생략해주세요.")
    val negativePrompt: String? = null,
) : Serializable