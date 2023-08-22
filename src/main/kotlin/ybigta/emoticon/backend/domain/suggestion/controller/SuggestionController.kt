package ybigta.emoticon.backend.domain.suggestion.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ybigta.emoticon.backend.domain.suggestion.controller.request.SuggestionRequest
import ybigta.emoticon.backend.domain.suggestion.controller.response.SuggestionResponse
import ybigta.emoticon.backend.domain.suggestion.controller.service.SuggestionService

@RestController
@RequestMapping("/suggestions")
class SuggestionController(
    private val suggestionService: SuggestionService,
) {
    @PostMapping
    fun suggest(
        @RequestBody
        request: SuggestionRequest,
    ): SuggestionResponse {
        val (emoticonUrls, detail) = suggestionService.suggestEmoticon(
            text = request.text,
            clientId = request.clientId,
        )

        return SuggestionResponse(
            emoticonUrls = emoticonUrls,
            detail = detail,
        )
    }
}
