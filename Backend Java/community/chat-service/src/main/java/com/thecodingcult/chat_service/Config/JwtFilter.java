package com.thecodingcult.chat_service.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtils;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        String jwt = parseJwt(request);

        if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
            String username = jwtUtils.getUsernameFromToken(jwt);
            // Set authentication in SecurityContext
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                    username, null, Collections.emptyList()
            );
            SecurityContextHolder.getContext().setAuthentication(auth);
            System.out.println("✅ Valid JWT for user: " + username);
            filterChain.doFilter(request, response);
        } else {
            System.out.println("❌ Invalid or missing JWT. Ensure the token is included in the 'Authorization' header and properly formatted.");
            response.addHeader("WWW-Authenticate", "Bearer"); // Add this header
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid or missing JWT");
        }
    }

    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");
        if (headerAuth == null) {
            System.out.println("❌ Authorization header is missing.");
            return null;
        }
        if (!headerAuth.startsWith("Bearer ")) {
            System.out.println("❌ Authorization header does not start with 'Bearer '. Value: " + headerAuth);
            return null;
        }
        String token = headerAuth.substring(7);
        if (token.split("\\.").length != 3) {
            System.out.println("❌ Malformed JWT. Token: " + token);
            return null;
        }
        return token;
    }
}