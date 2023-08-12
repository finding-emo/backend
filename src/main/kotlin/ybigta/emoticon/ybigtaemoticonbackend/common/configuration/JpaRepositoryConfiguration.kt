package ybigta.emoticon.ybigtaemoticonbackend.common.configuration

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import ybigta.emoticon.ybigtaemoticonbackend.common.ApplicationCommon
import ybigta.emoticon.ybigtaemoticonbackend.infra.aws.RdsConnectionInfra
import ybigta.emoticon.ybigtaemoticonbackend.infra.aws.SecretsManagerInfra
import javax.sql.DataSource

@EnableJpaRepositories
@Configuration
class JpaRepositoryConfiguration {
    @Profile(ApplicationCommon.Profile.LOCAL)
    @Bean
    fun localDataSource(): DataSource {
        return DataSourceBuilder
            .create()
            .url("jdbc:mysql://localhost:3310/ybigta_emoticon")
            .username("root")
            .password("6ea0f01f74b24bca2c4125fab1b5e691")
            .build()
    }

    @ConditionalOnMissingBean
    @Bean
    fun dataSource(
        rdsConnectionInfra: RdsConnectionInfra,
        secretsManagerInfra: SecretsManagerInfra,
    ): DataSource {
        val dbInstanceIdentifier = "ybigta-emoticon"
        val dbArn = rdsConnectionInfra.getArn(dbInstanceIdentifier)

        val dbSecret = secretsManagerInfra.getObjectByTag(
            "aws:rds:primaryDBInstanceArn",
            dbArn,
            DbSecret::class,
        )

        val dbEndpoint = rdsConnectionInfra.getEndpoint(dbInstanceIdentifier)

        return DataSourceBuilder
            .create()
            .url("jdbc:mysql://$dbEndpoint/ybigta_emoticon")
            .username(dbSecret.username)
            .password(dbSecret.password)
            .build()
    }
}
