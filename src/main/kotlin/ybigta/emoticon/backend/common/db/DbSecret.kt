package ybigta.emoticon.backend.common.db

import java.io.Serializable

data class DbSecret(
    val username: String,
    val password: String,
) : Serializable