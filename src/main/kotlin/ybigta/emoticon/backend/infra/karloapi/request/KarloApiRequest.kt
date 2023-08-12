package ybigta.emoticon.backend.infra.karloapi.request

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.io.Serializable

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class KarloApiRequest(
    val prompt: String,
    val negativePrompt: String? = null,
    val returnType: ReturnType? = null,
) : Serializable {
    enum class ReturnType {
        @JsonProperty("base64_string")
        BASE64_STRING,

        @JsonProperty("base64_url")
        URL,
    }
}

