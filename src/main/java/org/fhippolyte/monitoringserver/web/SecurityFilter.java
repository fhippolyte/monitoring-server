package org.fhippolyte.monitoringserver.web;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fhippolyte.monitoringserver.service.MetricsPersistence;
import org.springframework.web.filter.OncePerRequestFilter;

public class SecurityFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        String xAuth = request.getHeader("X-Authorization");
        
        if(!MetricsPersistence.getInstance().isSecurityKeyValid(xAuth)){
        	response.sendError(403, "No valid authentication token found.");
        } else {
        	filterChain.doFilter(request, response);
        }
 
    }
	
}
