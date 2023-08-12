package ybigta.emoticon.backend.infra.karloapi

import com.fasterxml.jackson.annotation.JsonProperty

enum class KarloApiRequestReturnType {
    @JsonProperty("base64_string")
    BASE64_STRING,

    @JsonProperty("base64_url")
    URL,
}