package ybigta.emoticon.ybigtaemoticonbackend.infra.karloapi

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.io.Serializable

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class KarloApiRequest(
    val prompt: String,
    val negativePrompt: String? = null,
    val returnType: KarloApiRequestReturnType? = null,
) : Serializable

