package com.thecodingcult.chat_service.Controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")
public class JwtTestController {

    @GetMapping("/username")
    public String getUsernameFromJwt(HttpServletRequest request) {
        String username = (String) request.getAttribute("username");

        if (username != null) {
            return "✅ Token is valid. Logged in as: " + username;
        } else {
            return "❌ Invalid or missing token.";
        }
    }
}
