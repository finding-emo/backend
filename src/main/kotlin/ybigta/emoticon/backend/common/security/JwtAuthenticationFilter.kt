package ybigta.emoticon.backend.common.security

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter

class JwtAuthenticationFilter : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        // TODO: temporal authentication for development
        if (request.getHeader("Authorization") == "Bearer 29af196d3bb3b46177f3ec5cbe4c44d8") {
            val authentication: Authentication = UsernamePasswordAuthenticationToken(
                request.remoteAddr,
                "29af196d3bb3b46177f3ec5cbe4c44d8",
                emptyList(),
            )

            SecurityContextHolder.getContext().authentication = authentication
        }

        filterChain.doFilter(request, response)
    }

}