package ybigta.emoticon.ybigtaemoticonbackend.domain.karloapi.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ybigta.emoticon.ybigtaemoticonbackend.domain.karloapi.service.KarloApiService
import ybigta.emoticon.ybigtaemoticonbackend.infra.karloapi.KarloApiResponse

@RestController
@RequestMapping("/test")
class KarloApiController(
    private val karloApiService: KarloApiService,
) {
    @PostMapping("/infer")
    fun infer(
        @RequestBody
        prompt: String,
    ): KarloApiResponse {
        return karloApiService.infer(prompt)
    }
}