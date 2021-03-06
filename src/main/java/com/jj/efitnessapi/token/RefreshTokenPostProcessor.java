package com.jj.efitnessapi.token;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.jj.efitnessapi.config.property.EfitnessApiProperty;

@ControllerAdvice
public class RefreshTokenPostProcessor implements ResponseBodyAdvice<OAuth2AccessToken> {

	@Autowired
	private EfitnessApiProperty efitnessApiProperty;
	
	@Override
	public OAuth2AccessToken beforeBodyWrite(OAuth2AccessToken body, MethodParameter returnType, MediaType selectedContenType,
			Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
		
		HttpServletRequest req = ((ServletServerHttpRequest) request).getServletRequest();
		HttpServletResponse res = ((ServletServerHttpResponse) response).getServletResponse();
		
		DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) body;
		
		addRefreshTokenCookie(body.getRefreshToken().getValue(), req, res);
		removeAccessToken(token);
		
		return body;
	}


	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> convrterType) {
		return returnType.getMethod().getName().equals("postAccessToken");
	}

	private void addRefreshTokenCookie(String refresToken, HttpServletRequest req, HttpServletResponse res) {
		Cookie cookie = new Cookie("refreshToken", refresToken);
		cookie.setHttpOnly(true);
		cookie.setSecure(efitnessApiProperty.getSecurity().isEnableHttps());
		cookie.setPath(req.getContextPath() + "/oauth/token");
		cookie.setMaxAge(864000);
		res.addCookie(cookie);
	}

	private void removeAccessToken(DefaultOAuth2AccessToken token) {
		token.setRefreshToken(null);
	}
}
