package ybigta.emoticon.backend.common.environment

import org.springframework.context.annotation.Configuration

@Configuration
class ApplicationCommon {

    object Profile {
        const val LOCAL = "local"
        const val PRODUCTION = "production"
    }

    companion object {
        const val BASE_PACKAGE = "ybigta.emoticon.backend"
    }
}