package ybigta.emoticon.backend.common.security


import org.springframework.context.annotation.AdviceMode
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(
    securedEnabled = true,
    prePostEnabled = true,
    mode = AdviceMode.PROXY,
)
class SecurityConfiguration {
    @Bean
    fun authenticationEntryPoint(): AuthenticationEntryPoint = JwtAuthenticationEntryPoint()

    @Bean
    fun accessDeniedHandler(): AccessDeniedHandler = JwtAccessDeniedHandler()

    @Bean
    fun jwtAuthenticationFilter(): JwtAuthenticationFilter = JwtAuthenticationFilter()

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .cors {}
            .csrf {
                it.disable()
            }
            .exceptionHandling {
                it.authenticationEntryPoint(authenticationEntryPoint())
                it.accessDeniedHandler(accessDeniedHandler())
            }
            .sessionManagement {
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .authorizeHttpRequests {
                it.shouldFilterAllDispatcherTypes(false)
                it.requestMatchers(*URLS_DOCUMENT).permitAll()
                it.anyRequest().authenticated()
            }
            .addFilterBefore(
                jwtAuthenticationFilter(),
                UsernamePasswordAuthenticationFilter::class.java,
            )
            .build()
    }

    companion object {
        private val URLS_DOCUMENT = arrayOf(
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/docs",
        )
    }
}