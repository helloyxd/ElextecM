package com.elextec.mdm.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.elextec.mdm.contorller.UserController;

/**
 * @author zhangkj
 *
 */
public class LoginFilter implements Filter{

	private static final Logger logger = LoggerFactory.getLogger(LoginFilter.class);   
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.debug("过滤器初始化");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String uri = req.getRequestURI();
		String method = req.getMethod();
		logger.debug(method + "-" + uri);
		String[] notFilterDirs = {"/user/signIn"};
		for (int i = 0; i < notFilterDirs.length; i++) {
			String notFilterDirValue = notFilterDirs[i];
			if (uri.indexOf(notFilterDirValue) != -1) {
				chain.doFilter(request, response);
				return;
			}
		}
		HttpSession session = req.getSession();
		if (session.getAttribute("mdm_user")==null){
			res.setStatus(401);
			return;
		}else{
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
		logger.debug("过滤器销毁");
	}

}
