package ybigta.emoticon.ybigtaemoticonbackend.infra.karloapi

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping

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

