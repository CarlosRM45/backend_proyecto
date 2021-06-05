package com.gustilandia.backend.security;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;

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
		List<String> rol = usuarioJwt.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
		return Jwts.builder()
				.setSubject(usuarioJwt.getUsername())
				.claim("rol", rol)
				.setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime() + expiration))
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
	
	public String refresh(String token) throws ParseException{
		JWT jwt = JWTParser.parse(token);
		JWTClaimsSet claims = jwt.getJWTClaimsSet();
		String usuario = claims.getSubject();
		List<String> rol = (List<String>) claims.getClaim("rol");
		
		return Jwts.builder()
				.setSubject(usuario)
				.claim("rol", rol)
				.setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime() + expiration))
				.signWith(SignatureAlgorithm.HS512, secret)
				.compact();
		
	}

}
