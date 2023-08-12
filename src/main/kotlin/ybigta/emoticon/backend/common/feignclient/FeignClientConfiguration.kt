package ybigta.emoticon.backend.common.feignclient

import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Configuration
import ybigta.emoticon.backend.common.ApplicationCommon

@EnableFeignClients(basePackages = [ApplicationCommon.BASE_PACKAGE])
@Configuration
class FeignClientConfiguration