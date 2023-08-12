package ybigta.emoticon.backend.infra.karloapi

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import ybigta.emoticon.backend.infra.karloapi.request.KarloApiT2iRequest
import ybigta.emoticon.backend.infra.karloapi.response.KarloApiT2iResponse

/**
 * @see <a href="https://developers.kakao.com/docs/latest/ko/karlo/common">Karlo API</a>
 */
@FeignClient(
    "KarloApiClient",
    url = "https://api.kakaobrain.com",
    configuration = [KarloApiClientConfiguration::class]
)
interface KarloApiClient {
    @PostMapping("/v2/inference/karlo/t2i")
    fun t2i(
        request: KarloApiT2iRequest,
    ): KarloApiT2iResponse
}

