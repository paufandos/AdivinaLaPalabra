package com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.security.jwt;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.services.impl.UserDetailsServiceImpl;
import com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.utilities.Base64Utils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {

            String jwt = parseJwt(request);
            if (jwt != null) authenticateUser(getUserFromToken(jwt), request);

        } catch (Exception e) {
            log.error("Cannot set user authentication: {}", e);
        }

        filterChain.doFilter(request, response);
    }

    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");
        if (!StringUtils.hasText(headerAuth) || !headerAuth.startsWith("Bearer "))  return null;

        return headerAuth.substring(7, headerAuth.length());
    }

    private UserDetails getUserFromToken(String token) {
        String userName = jwtUtils.validateTokenAndRetrieveSubject(token);
        userName = Base64Utils.decode(userName);

        return userDetailsService.loadUserByUsername(userName);
    }

    private void authenticateUser(UserDetails userDetails, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

}
