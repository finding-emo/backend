package ybigta.emoticon.backend.infra.karloapi

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.io.Serializable

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class KarloApiResponse(
    val id: String,
    val modelVersion: String,
    val images: List<KarloApiResponseImage>,
) : Serializable {
    data class KarloApiResponseImage(
        val id: String,
        val seed: Long,
        val image: String,
    ) : Serializable
}