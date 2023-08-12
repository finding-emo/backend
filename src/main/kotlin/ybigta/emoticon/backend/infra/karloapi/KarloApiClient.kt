package ybigta.emoticon.backend.infra.karloapi

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping

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
    fun infer(
        request: KarloApiRequest,
    ): KarloApiResponse
}

