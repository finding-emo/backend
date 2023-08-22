package ybigta.emoticon.backend.domain.suggestion.controller.service

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import ybigta.emoticon.backend.domain.externalapi.service.ExternalApiService
import ybigta.emoticon.backend.infra.firebase.FirestoreInfra

@Service
class SuggestionService(
    private val firestoreInfra: FirestoreInfra,
    private val externalApiService: ExternalApiService,
) {
    fun suggestEmoticon(
        text: String,
        clientId: String,
    ): Pair<MutableList<String>, Map<String, Any>> {
        val survey = firestoreInfra.getSurveyById(clientId)

        val translatedText = externalApiService.inferNmtByPapagoApi(text)

        val keywords = externalApiService.extractKeywordsByKeywordModel(translatedText)

        val (prompts, negativePrompt) = externalApiService.generatePromptByKeywordModel(
            text = text,
            translatedKeywords = keywords,
            survey = survey,
        )

        prompts
            .plus(negativePrompt)
            .filter { it.length > 256 }
            .forEach {
                throw ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "프롬프트가 너무 깁니다: $it",
                )
            }



        return prompts
            .parallelStream()
            .map {
                externalApiService
                    .inferT2iUrlsByKarloApi(
                        prompt = it.strip(),
                        negativePrompt = negativePrompt,
                        nSamples = 1,
                    )
                    .single()
            }
            .toList() to mapOf(
            "survey" to survey,
            "text" to text,
            "translatedText" to translatedText,
            "keywords" to keywords,
            "prompts" to prompts,
            "negativePrompt" to negativePrompt,
        )
    }
}