package ybigta.emoticon.ybigtaemoticonbackend.infra.aws

import org.springframework.stereotype.Service
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest

@Service
class SecretsManagerInfra {
    private val client: SecretsManagerClient = SecretsManagerClient.builder().build()
    fun getString(secretName: String): String {
        val secretName = "ybigta-emoticon/karlo-api-key"

        val getSecretValueRequest: GetSecretValueRequest = GetSecretValueRequest.builder()
            .secretId(secretName)
            .build()

        return client
            .getSecretValue(getSecretValueRequest)
            .secretString()
    }
}