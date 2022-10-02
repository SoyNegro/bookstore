package dev.coffeecult.bookstore.security.jwt;

import dev.coffeecult.bookstore.security.service.BaseUserDetailsService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthTokenFilter extends OncePerRequestFilter {
    private JwtUtils jwtUtils;
    private BaseUserDetailsService baseUserDetailsService;
    public AuthTokenFilter(JwtUtils jwtUtils, BaseUserDetailsService baseUserDetailsService){
        this.jwtUtils = jwtUtils;
        this.baseUserDetailsService = baseUserDetailsService;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
      var jwt = parseJWT(request);
      try {
          if (jwt != null) {
              var username = jwtUtils.getUsernameFromJWT(jwt);
              var userDetails = baseUserDetailsService.loadUserByUsername(username);
              UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                      userDetails,
                      null,
                      userDetails.getAuthorities()
              );
              authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
              SecurityContextHolder.getContext().setAuthentication(authenticationToken);
          }
      }catch (Exception e){
          logger.error(e);
      }
      filterChain.doFilter(request,response);
    }

    private String parseJWT(HttpServletRequest request){
        String headerAuth = request.getHeader("Authorization");
        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7);
        }
        return null;
    }
}
