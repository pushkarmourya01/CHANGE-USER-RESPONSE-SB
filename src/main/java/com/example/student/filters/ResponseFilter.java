package com.example.student.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;

@Component
public class ResponseFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpServletRequest =
                (HttpServletRequest) request;

        HttpServletResponse httpServletResponse =
                (HttpServletResponse) response;

        // pahle ContentCachingResponseWrapper ka object banaya
        ContentCachingResponseWrapper wrappedResponse =
                new ContentCachingResponseWrapper(httpServletResponse);

        // IMPORTANT: original response nahi, wrappedResponse pass karna hai
        chain.doFilter(request, wrappedResponse);

        // jaise hi humare pass response aya uske body ke bytes nikale
        byte[] originalBodyByte =
                wrappedResponse.getContentAsByteArray();

        // usko convert kiya string mein
        String originalBody =
                new String(originalBodyByte);

        String modifiedBody = """
                {
                    "originalResponse" : %s,
                    "appName" : "Student Managment System"
                }
                """.formatted(originalBody);

wrappedResponse.resetBuffer();

        // wapis copy kr diya response mein and writer mein
        wrappedResponse.getWriter().write(modifiedBody);

        wrappedResponse.copyBodyToResponse();
    }
}