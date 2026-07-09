package com.example.student.filters;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

//@Component //contenct-cache-response-wrapper jo response ke liye hai
public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

  String token = httpServletResponse.getHeader("token");
  if(token==null || !token.equals(12345)){
      httpServletResponse.setContentType("application/json");
      httpServletResponse.getWriter().write("{\"message\": \"Authentication Needed\"}");
      return;
  }

      chain.doFilter(request,response);

    }
}
