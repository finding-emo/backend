package ybigta.emoticon.backend.infra.papagoapi

import feign.RequestInterceptor
import feign.RequestTemplate
import org.springframework.context.annotation.Bean
import ybigta.emoticon.backend.infra.aws.SecretsManagerInfra


class PapagoApiClientConfiguration(
    private val secretsManagerInfra: SecretsManagerInfra,
) {
    @Bean
    fun requestInterceptor(): RequestInterceptor {
        return RequestInterceptor { requestTemplate: RequestTemplate ->
            val credential =
                secretsManagerInfra.getObject("ybigta-emoticon/papago-api-credential", PapagoApiCredential::class)
            requestTemplate.header("X-Naver-Client-Id", credential.clientId)
            requestTemplate.header("X-Naver-Client-Secret", credential.clientSecret)
            requestTemplate.header("Content-Type", "application/json; charset=UTF-8")
        }
    }
}

