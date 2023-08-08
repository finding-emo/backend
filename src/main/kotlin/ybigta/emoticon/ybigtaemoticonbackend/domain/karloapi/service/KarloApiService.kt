package ybigta.emoticon.ybigtaemoticonbackend.domain.karloapi.service

import org.springframework.stereotype.Service
import ybigta.emoticon.ybigtaemoticonbackend.infra.karloapi.KarloApiClient
import ybigta.emoticon.ybigtaemoticonbackend.infra.karloapi.KarloApiRequest
import ybigta.emoticon.ybigtaemoticonbackend.infra.karloapi.KarloApiResponse

@Service
class KarloApiService(
    private val karloApiClient: KarloApiClient,
) {
    fun infer(prompt: String): KarloApiResponse {
        val request = KarloApiRequest(prompt = prompt)
        return karloApiClient.infer(request)
    }
}

