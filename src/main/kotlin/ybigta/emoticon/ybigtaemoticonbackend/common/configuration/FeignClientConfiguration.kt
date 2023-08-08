package ybigta.emoticon.ybigtaemoticonbackend.common.configuration

import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Configuration
import ybigta.emoticon.ybigtaemoticonbackend.common.ApplicationCommon

@EnableFeignClients(basePackages = [ApplicationCommon.BASE_PACKAGE])
@Configuration
class FeignClientConfiguration