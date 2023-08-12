package ybigta.emoticon.ybigtaemoticonbackend.infra.aws

import org.springframework.stereotype.Service
import software.amazon.awssdk.services.rds.RdsClient
import software.amazon.awssdk.services.rds.model.DBInstance

@Service
class RdsConnectionInfra {
    val rdsClient: RdsClient = RdsClient.builder().build()

    fun describeDbInstanceByIdentifier(dbInstanceIdentifier: String): DBInstance {
        val describeDbInstancesRequest = rdsClient.describeDBInstances {
            it.dbInstanceIdentifier(dbInstanceIdentifier)
        }
        return describeDbInstancesRequest.dbInstances()[0]
    }

    fun getEndpoint(dbInstanceIdentifier: String): String {
        return describeDbInstanceByIdentifier(dbInstanceIdentifier).endpoint().let { "${it.address()}:${it.port()}" }
    }

    fun getArn(dbInstanceIdentifier: String): String {
        return describeDbInstanceByIdentifier(dbInstanceIdentifier).dbInstanceArn()
    }
}