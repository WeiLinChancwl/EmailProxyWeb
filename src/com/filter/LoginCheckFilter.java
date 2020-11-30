package com.filter;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebFilter(dispatcherTypes = {
		DispatcherType.REQUEST, 
		DispatcherType.FORWARD, 
		DispatcherType.INCLUDE, 
		DispatcherType.ERROR
}
			, urlPatterns = { "/index.jsp" })
public class LoginCheckFilter implements Filter {
	private FilterConfig config = null;
	private String webRoot = null;
	
    public LoginCheckFilter() {
       
    }

	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		HttpSession session = req.getSession();
		if(session.getAttribute("userName") != null) {
			chain.doFilter(request, response);
		} else {
			res.sendRedirect("adminlogin.jsp");
		}
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		this.config = fConfig;
		ServletContext ctx = config.getServletContext();
		webRoot = ctx.getContextPath();
	}

}
