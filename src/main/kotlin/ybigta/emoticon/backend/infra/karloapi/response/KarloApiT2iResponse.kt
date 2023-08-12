package ybigta.emoticon.backend.infra.karloapi.response

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.io.Serializable

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class KarloApiT2iResponse(
    val id: String,
    val modelVersion: String,
    val images: List<Image>,
) : Serializable {
    data class Image(
        val id: String,
        val seed: Long,
        val image: String,
    ) : Serializable
}