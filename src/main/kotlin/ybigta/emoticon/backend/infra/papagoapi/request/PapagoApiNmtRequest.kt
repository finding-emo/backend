package ybigta.emoticon.backend.infra.papagoapi.request

import java.io.Serializable

data class PapagoApiNmtRequest(
    val text: String,
    val source: String = "ko",
    val target: String = "en",
) : Serializable