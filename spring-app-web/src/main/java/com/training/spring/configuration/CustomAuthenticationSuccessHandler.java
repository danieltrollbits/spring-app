package com.training.spring.configuration;  
  
import org.springframework.security.core.Authentication;  
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;  
  
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;  
import java.io.IOException;  

import com.training.spring.model.User;

import org.springframework.stereotype.Component;

@Component  
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {  
  
    @Override  
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,  
                                        HttpServletResponse httpServletResponse,  
                                        Authentication authentication)  
            throws IOException, ServletException {
  
        HttpSession session = httpServletRequest.getSession();  
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();  
        session.setAttribute("username", authUser.getUsername());  
        session.setAttribute("authorities", authentication.getAuthorities());  
  
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);  
  
        httpServletResponse.sendRedirect("/");  
    }  
}  