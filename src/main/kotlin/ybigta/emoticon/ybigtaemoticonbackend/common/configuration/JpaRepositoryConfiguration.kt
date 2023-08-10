package ybigta.emoticon.ybigtaemoticonbackend.common.configuration

import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import ybigta.emoticon.ybigtaemoticonbackend.common.ApplicationCommon
import javax.sql.DataSource

@EnableJpaRepositories
@Configuration
class JpaRepositoryConfiguration {
    @Profile(ApplicationCommon.Profile.LOCAL)
    @Bean
    fun dataSource(): DataSource {
        return DataSourceBuilder
            .create()
            .url("jdbc:mysql://localhost:3310/ybigta-emoticon")
            .username("root")
            .password("6ea0f01f74b24bca2c4125fab1b5e691")
            .build()
    }
}
