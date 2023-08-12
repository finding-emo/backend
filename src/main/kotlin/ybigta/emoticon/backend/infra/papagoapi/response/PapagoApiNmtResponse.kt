package ybigta.emoticon.backend.infra.papagoapi.response

import java.io.Serializable

data class PapagoApiNmtResponse(
    val message: Message,
) : Serializable {
    data class Message(
        val result: Result,
    ) : Serializable {
        data class Result(
            val translatedText: String,
        ) : Serializable
    }
}