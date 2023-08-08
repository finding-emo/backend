package ybigta.emoticon.ybigtaemoticonbackend.infra.karloapi

import feign.RequestInterceptor
import feign.RequestTemplate
import org.springframework.context.annotation.Bean


class KarloApiClientConfiguration {
    @Bean
    fun requestInterceptor(): RequestInterceptor {
        return RequestInterceptor { requestTemplate: RequestTemplate ->
            val apiKey = "TODO"
            requestTemplate.header("Authorization", "KakaoAK $apiKey")
            requestTemplate.header("Content-Type", "application/json")
        }
    }
}