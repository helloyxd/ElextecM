package com.elextec.mdm.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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

import com.alibaba.fastjson.JSON;
import com.elextec.mdm.common.entity.VoResponse;
import com.elextec.mdm.contorller.UserController;
import com.elextec.mdm.entity.Menu;
import com.elextec.mdm.entity.Role;
import com.elextec.mdm.entity.User;

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
		VoResponse voRes = new VoResponse();
		voRes.setNull(voRes);
		voRes.setCode(401);
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String url = req.getRequestURI();
		String method = req.getMethod();
		
		//logger.debug(method + "-" + url);
		System.out.println(method + "-" + url);
		//“/mdm”
		String[] notFilterDirs = {"/mdm/user/signIn","/mdm/ws","/mdm/model","mdm/flow"};
		for (int i = 0; i < notFilterDirs.length; i++) {
			String notFilterDirValue = notFilterDirs[i];
			if (url.indexOf(notFilterDirValue) != -1) {
				chain.doFilter(request, response);
				return;
			}
		}
		//HttpSession session = req.getSession();
		HttpSession session = null;
		User user = null;
		session = req.getSession();
		if(session != null) {
			user = (User) session.getAttribute("mdm_user");
		}
		if(user == null) {
			String token = req.getHeader("token");
			if(token == null) {
				res.setStatus(200);
				PrintWriter writer = response.getWriter();
				writer.write(JSON.toJSON(voRes).toString());
				return;
			}
			session = UserController.sessionMap.get(token);
			if(session == null) {
				//res.setStatus(401);
				res.setStatus(200);
				PrintWriter writer = response.getWriter();
				writer.write(JSON.toJSON(voRes).toString());
				return;
			}
			user = (User) session.getAttribute("mdm_user");
		}
		if (user == null){
			res.setStatus(401);
			res.setStatus(200);
			PrintWriter writer = response.getWriter();
			
			writer.write(JSON.toJSON(voRes).toString());
			return;
		}else if(user.getUserName().equals("admin")){
			
		}else{
			for(Role role : user.getRoles()) {
				//System.out.println(role.getRoleName());
				if(role.getRoleName().equals("admin")) {
					chain.doFilter(request, response);
					return;
				}
			}
			
			List<Menu> menus = user.getMenus();
			
			boolean flag = false;
			for(Menu menu : menus){
				if(method.toLowerCase().equals(menu.getMethod())){
					if(url.equals(menu.getMenuUrl())){
						flag = true;
						break;
					}
				}
			}
			if(!flag){
				res.setStatus(402);
				return;
			}
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		logger.debug("过滤器销毁");
	}

}
