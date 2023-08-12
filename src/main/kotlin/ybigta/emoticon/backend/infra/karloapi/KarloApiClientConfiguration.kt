package ybigta.emoticon.backend.infra.karloapi

import feign.RequestInterceptor
import feign.RequestTemplate
import org.springframework.context.annotation.Bean
import ybigta.emoticon.backend.infra.aws.SecretsManagerInfra


class KarloApiClientConfiguration(
    private val secretsManagerInfra: SecretsManagerInfra,
) {
    @Bean
    fun requestInterceptor(): RequestInterceptor {
        return RequestInterceptor { requestTemplate: RequestTemplate ->
            val apiKey = secretsManagerInfra.getString("ybigta-emoticon/karlo-api-key")
            requestTemplate.header("Authorization", "KakaoAK $apiKey")
            requestTemplate.header("Content-Type", "application/json")
        }
    }
}
