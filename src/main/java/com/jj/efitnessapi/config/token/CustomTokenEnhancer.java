package com.jj.efitnessapi.config.token;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import com.jj.efitnessapi.security.UserLoggedIn;

public class CustomTokenEnhancer implements TokenEnhancer {

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

		UserLoggedIn user = (UserLoggedIn) authentication.getPrincipal();

		Map<String, Object> map = new HashMap<>();
		map.put("name", user.getSystemUser().getName());

		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(map);

		return accessToken;
	}

}
