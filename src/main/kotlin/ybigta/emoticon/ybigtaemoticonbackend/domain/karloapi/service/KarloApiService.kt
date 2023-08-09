package ybigta.emoticon.ybigtaemoticonbackend.domain.karloapi.service

import org.springframework.stereotype.Service
import ybigta.emoticon.ybigtaemoticonbackend.infra.karloapi.KarloApiClient
import ybigta.emoticon.ybigtaemoticonbackend.infra.karloapi.KarloApiRequest
import ybigta.emoticon.ybigtaemoticonbackend.infra.karloapi.KarloApiRequestReturnType
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

@Service
class KarloApiService(
    private val karloApiClient: KarloApiClient,
) {
    @OptIn(ExperimentalEncodingApi::class)
    fun base64StringToByteArray(base64String: String): ByteArray {
        return Base64.decode(base64String)
    }

    fun infer(prompt: String): ByteArray {
        val request = KarloApiRequest(
            prompt = prompt,
            returnType = KarloApiRequestReturnType.BASE64_STRING,
        )

        return karloApiClient
            .infer(request)
            .images
            .first()
            .image
            .let { base64StringToByteArray(it) }
    }
}

