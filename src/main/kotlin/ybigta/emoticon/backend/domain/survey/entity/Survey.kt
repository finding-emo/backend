package ybigta.emoticon.backend.domain.survey.entity

import java.io.Serializable

class Survey private constructor(map: Map<String, String>) : Serializable {
    val iconColor by map
    val iconMood by map
    val iconType by map

    companion object {
        fun fromMap(map: Map<String, Any>): Survey {
            @Suppress("UNCHECKED_CAST")
            return Survey(map as Map<String, String>)
        }
    }
}