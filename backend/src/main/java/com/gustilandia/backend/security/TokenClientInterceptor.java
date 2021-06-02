package com.gustilandia.backend.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.gustilandia.backend.controller.ClienteController;

@Component
public class TokenClientInterceptor implements HandlerInterceptor{
	
	public static String token = "";
	
	private final static Logger LOGGER = LoggerFactory.getLogger(TokenClientInterceptor.class);

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		token = request.getHeader("Authorization");
		LOGGER.info(token + " PostHandle");
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		token = request.getHeader("Authorization");
		LOGGER.info(token + " PreHandle");
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
	
	
	
	

}
