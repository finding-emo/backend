package ybigta.emoticon.backend.common.swagger

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.ExternalDocumentation
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.media.Schema
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import io.swagger.v3.oas.models.servers.Server
import org.springdoc.core.utils.SpringDocUtils
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ybigta.emoticon.backend.common.environment.ApplicationCommon
import ybigta.emoticon.backend.common.environment.ProfileConfiguration
import java.awt.Color
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Configuration
class SwaggerConfiguration(
    @Qualifier(ProfileConfiguration.BeanName.PROFILE)
    private val profile: String,
) {
    private val securityScheme = SecurityScheme()
        .type(SecurityScheme.Type.HTTP)
        .scheme("Bearer")
        .bearerFormat("JWT")
        .`in`(SecurityScheme.In.HEADER)
        .name("Authorization")
    private val securityRequirementName = "customAuth"

    init {
        SpringDocUtils
            .getConfig()
            .replaceWithSchema(
                Color::class.java,
                Schema<String>()
                    .type("string")
                    .format("color")
                    .example("#FFFFFFFF"),
            )
            .replaceWithSchema(
                LocalTime::class.java,
                Schema<LocalTime>()
                    .type("string")
                    .format("time")
                    .example(
                        LocalTime
                            .now()
                            .format(DateTimeFormatter.ofPattern("HH:mm")),
                    ),
            )
    }

    @Bean
    fun openApi(): OpenAPI {
        return OpenAPI()
            .servers(listOf(Server().apply { url = "/" }))
            .security(
                listOf(
                    SecurityRequirement()
                        .addList(securityRequirementName),
                ),
            )
            .components(
                Components()
                    .addSecuritySchemes(
                        securityRequirementName,
                        securityScheme,
                    ),
            )
            .info(
                Info()
                    .title("YBIGTA 23th Emoticon API")
                    .description(description)
                    .version("prototype"),
            )
            .externalDocs(
                ExternalDocumentation()
                    .description("YBIGTA 23th Emoticon API"),
            )
    }

    private val apiRootUrl =
        if (profile != ApplicationCommon.Profile.LOCAL) "http://\${주소}"
        else "http://localhost:8080"
    private val description = """
        ## Swagger 사용법
        
        ### 인증
        
        오른쪽 아래의 Authorize 버튼을 눌러 토큰을 입력하고 확인 버튼을 누르면 API를 사용할 수 있습니다.
        버튼을 찾기 어렵다면 Ctrl + F 또는 Cmd + F 를 눌러 찾기 기능으로 검색하실 수 있습니다.
        
        ### API 호출
        
        하단에 표시되는 각 API를 expand한 다음, Try it out 버튼을 눌러 파라미터 입력 화면을 띄웁니다.
        파라미터를 형식에 맞게 입력한 다음, Execute 버튼을 누르면 API를 호출할 수 있습니다.
        
        ## 클라이언트 적용
        
        ### 필수 요청 헤더

        필요한 요청 헤더 2개는 다음과 같습니다. <br />
        이때 "{토큰}" 대신 발급된 토큰을 넣어주세요. <br />

        * "Authorization: Bearer {토큰}"
        * "Content-Type: application/json"

        ### cURL 예시
        
        본 문서에서 API 호출을 시도해볼 경우, 해당 요청에 대한 cURL 코드가 생성됩니다.
        """.trimIndent()
}
