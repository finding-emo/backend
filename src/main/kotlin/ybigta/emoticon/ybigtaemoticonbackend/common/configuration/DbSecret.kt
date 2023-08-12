package ybigta.emoticon.ybigtaemoticonbackend.common.configuration

import java.io.Serializable

data class DbSecret(
    val username: String,
    val password: String,
) : Serializable