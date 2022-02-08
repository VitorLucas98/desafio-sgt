package com.vitor.desafioviceri.config;

import com.vitor.desafioviceri.entities.Usuario;
import com.vitor.desafioviceri.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenEnhacer implements TokenEnhancer {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Usuario usuario = repository.findByEmail(authentication.getName());

        Map<String, Object> map = new HashMap<>();
        map.put("nome", usuario.getNome());
        map.put("IdUsuario", usuario.getId());

        DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) accessToken;
        token.setAdditionalInformation(map);
        return accessToken;
    }
}
