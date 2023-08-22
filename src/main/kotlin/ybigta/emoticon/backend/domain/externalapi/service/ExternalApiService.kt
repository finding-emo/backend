package ybigta.emoticon.backend.domain.externalapi.service

import org.springframework.stereotype.Service
import ybigta.emoticon.backend.domain.survey.entity.Survey
import ybigta.emoticon.backend.infra.karloapi.KarloApiClient
import ybigta.emoticon.backend.infra.karloapi.request.KarloApiT2iRequest
import ybigta.emoticon.backend.infra.keywordmodel.KeywordModelClient
import ybigta.emoticon.backend.infra.keywordmodel.request.KeywordModelExtractRequest
import ybigta.emoticon.backend.infra.keywordmodel.request.KeywordModelGeneratePromptRequest
import ybigta.emoticon.backend.infra.papagoapi.PapagoApiClient
import ybigta.emoticon.backend.infra.papagoapi.request.PapagoApiNmtRequest
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

@Service
class ExternalApiService(
    private val karloApiClient: KarloApiClient,
    private val papagoApiClient: PapagoApiClient,
    private val keywordModelClient: KeywordModelClient,
) {
    @OptIn(ExperimentalEncodingApi::class)
    fun base64StringToByteArray(base64String: String): ByteArray {
        return Base64.decode(base64String)
    }

    fun inferT2iByKarloApi(
        prompt: String,
        negativePrompt: String?,
    ): ByteArray {
        val request = KarloApiT2iRequest(
            prompt = prompt,
            negativePrompt = negativePrompt,
            returnType = KarloApiT2iRequest.ReturnType.BASE64_STRING,
            nsfwChecker = true,
        )

        return karloApiClient
            .t2i(request)
            .images
            .first()
            .image
            .let { base64StringToByteArray(it) }
    }

    fun inferT2iUrlsByKarloApi(
        prompt: String,
        negativePrompt: String?,
        nSamples: Int,
    ): List<String> {
        val request = KarloApiT2iRequest(
            prompt = prompt,
            negativePrompt = negativePrompt,
            samples = nSamples,
            returnType = KarloApiT2iRequest.ReturnType.URL,
            nsfwChecker = true,
        )

        return karloApiClient
            .t2i(request)
            .images
            .map { it.image }
    }

    fun inferNmtByPapagoApi(text: String): String {
        val request = PapagoApiNmtRequest(
            text = text,
        )

        return papagoApiClient
            .nmt(request)
            .message
            .result
            .translatedText
    }

    fun extractKeywordsByKeywordModel(
        text: String,
    ): List<String> {
        val request = KeywordModelExtractRequest(text = text)

        return keywordModelClient
            .extractKeywords(request)
            .keywords
            .sortedByDescending { it.score }
            .map { it.keyword }
    }

    fun generatePromptByKeywordModel(
        text: String,
        translatedKeywords: List<String>,
        survey: Survey,
    ): Pair<List<String>, String> {
        val request = KeywordModelGeneratePromptRequest(
            text = text,
            translatedKeywords = translatedKeywords,
            survey = survey,
        )

        return keywordModelClient
            .generatePrompt(request)
            .let { it.prompts to it.negativePrompt }
    }
}

