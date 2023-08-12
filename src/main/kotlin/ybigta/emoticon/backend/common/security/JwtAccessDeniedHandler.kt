package ybigta.emoticon.backend.common.security

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.security.access.AccessDeniedException as SpringSecurityAccessDeniedException

class JwtAccessDeniedHandler : AccessDeniedHandler {
    override fun handle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        accessDeniedException: SpringSecurityAccessDeniedException, // import aliased
    ) {
        response.sendError(HttpServletResponse.SC_FORBIDDEN)
    }
}