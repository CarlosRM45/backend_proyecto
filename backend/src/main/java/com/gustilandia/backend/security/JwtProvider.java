package com.gustilandia.backend.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtProvider {
	
	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expiration}")
	private int expiration;
	
	public String generateToken(Authentication authentication){
		
		UsuarioJWT usuarioJwt = (UsuarioJWT) authentication.getPrincipal();
		return Jwts.builder().setSubject(usuarioJwt.getUsername())
				.setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime() + expiration * 1000))
				.signWith(SignatureAlgorithm.HS512, secret)
				.compact();
	}
	
	public String getUsuarioToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
	}
	
	public boolean validateToken(String token) {
		
		try {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
			return true;
		} catch (MalformedJwtException e) {
			System.out.println("token malformed");
		}catch (UnsupportedJwtException e) {
			System.out.println("token unsupported");
		}catch (ExpiredJwtException e) {
			System.out.println("token expired");
		}catch (IllegalArgumentException e) {
			System.out.println("token null");
		}catch (SignatureException e) {
			System.out.println("token firmed fail");
		}
		
		return false;
	}

}
