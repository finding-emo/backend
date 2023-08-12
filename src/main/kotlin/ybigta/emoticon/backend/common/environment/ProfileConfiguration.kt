package ybigta.emoticon.backend.common.environment

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Configuration
class ProfileConfiguration {
    @Profile("local")
    @Bean(BeanName.PROFILE)
    fun localProfile(): String = ApplicationCommon.Profile.LOCAL

    @ConditionalOnMissingBean
    @Bean(BeanName.PROFILE)
    fun productionProfile(): String = ApplicationCommon.Profile.PRODUCTION

    object BeanName {
        const val PROFILE = "profile"
    }
}