package ybigta.emoticon.backend.infra.papagoapi

import java.io.Serializable

data class PapagoApiCredential(
    val clientId: String,
    val clientSecret: String,
) : Serializable