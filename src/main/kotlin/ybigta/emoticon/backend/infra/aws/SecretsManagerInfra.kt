package ybigta.emoticon.backend.infra.aws

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Service
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient
import software.amazon.awssdk.services.secretsmanager.model.Filter
import software.amazon.awssdk.services.secretsmanager.model.FilterNameStringType
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest
import kotlin.reflect.KClass

@Service
class SecretsManagerInfra(
    private val objectMapper: ObjectMapper,
) {
    private val client: SecretsManagerClient = SecretsManagerClient.builder().build()

    fun getString(secretName: String): String {
        val getSecretValueRequest: GetSecretValueRequest = GetSecretValueRequest
            .builder()
            .secretId(secretName)
            .build()

        return client
            .getSecretValue(getSecretValueRequest)
            .secretString()
    }

    fun getStringByTag(tagKey: String, tagValue: String): String {
        val tagKeyFilter = Filter
            .builder()
            .key(FilterNameStringType.TAG_KEY)
            .values(tagKey)
            .build()
        val tagValueFilter = Filter
            .builder()
            .key(FilterNameStringType.TAG_VALUE)
            .values(tagValue)
            .build()

        val secretName = client
            .listSecrets {
                it.filters(tagKeyFilter, tagValueFilter)
            }
            .secretList()
            .single()
            .name()

        return getString(secretName)
    }

    fun <T : Any> getObject(secretName: String, type: KClass<T>): T {
        val string = getString(secretName)

        return objectMapper.readValue(string, type.java)
    }

    fun <T : Any> getObjectByTag(tagKey: String, tagValue: String, type: KClass<T>): T {
        val string = getStringByTag(tagKey, tagValue)

        return objectMapper.readValue(string, type.java)
    }
}