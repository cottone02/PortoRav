package it.rjcsoft.prv.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
 
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
@WebFilter("/*")
public class CORSFilter implements Filter {
 
	protected final Logger log = LoggerFactory.getLogger(getClass());
	
    @Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
 
        ((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Origin", "*");
        ((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Methods","GET, OPTIONS, HEAD, PUT, POST, PATCH, DELETE");
 
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        String accessControlAllowHeaders = request.getHeader("Access-Control-Request-Headers");
    	log.debug("request={} {}, resp={}, accessControlAllowHeaders={}",request.getMethod(),request.getRequestURI(), resp, accessControlAllowHeaders);
		
		if (StringUtils.isNotBlank(accessControlAllowHeaders)){
			log.debug("accessControlAllowHeaders IS NOT BLANK");
			((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Headers", accessControlAllowHeaders);
		}
        
        if (request.getMethod().equals("OPTIONS")) {
            resp.setStatus(HttpServletResponse.SC_ACCEPTED);
            return;
        }
 
        chain.doFilter(request, servletResponse);
    }
  
}