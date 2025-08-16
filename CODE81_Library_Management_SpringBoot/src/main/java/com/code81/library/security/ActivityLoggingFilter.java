package com.code81.library.security;

import com.code81.library.entity.UserActivityLog;
import com.code81.library.repository.UserActivityLogRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
public class ActivityLoggingFilter extends OncePerRequestFilter {

    private final UserActivityLogRepository repo;
    public ActivityLoggingFilter(UserActivityLogRepository repo){ this.repo = repo; }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } finally {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = (auth!=null && auth.isAuthenticated()) ? auth.getName() : "anonymous";
            UserActivityLog log = new UserActivityLog();
            log.setUsername(username);
            log.setAction(request.getMethod());
            log.setResource(request.getRequestURI());
            log.setIp(request.getRemoteAddr());
            log.setDetails("status=" + response.getStatus());
            repo.save(log);
        }
    }
}
