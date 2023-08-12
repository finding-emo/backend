package ybigta.emoticon.backend.infra.papagoapi

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import ybigta.emoticon.backend.infra.papagoapi.request.PapagoApiNmtRequest
import ybigta.emoticon.backend.infra.papagoapi.response.PapagoApiNmtResponse

/**
 * @see <a href="https://developers.naver.com/docs/papago/papago-nmt-api-reference.md">Papago NMT API</a>
 */
@FeignClient(
    "PapagoApiClient",
    url = "https://openapi.naver.com",
    configuration = [PapagoApiClientConfiguration::class]
)
interface PapagoApiClient {
    @PostMapping("/v1/papago/n2mt")
    fun nmt(
        request: PapagoApiNmtRequest,
    ): PapagoApiNmtResponse
}

