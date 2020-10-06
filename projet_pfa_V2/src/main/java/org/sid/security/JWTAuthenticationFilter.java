package org.sid.security;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.sid.beans.Charge;
import org.sid.beans.Depot;
import org.sid.beans.Utilisateur;
import org.sid.dao.ChargeRepository;
import org.sid.dao.DepotRepository;
import org.sid.dao.UtilisateurRepository;
import org.sid.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	@Autowired
	private AuthenticationManager authenticationManager;
	

	
	
	
	
	@Override
public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
		throws AuthenticationException {
		Utilisateur user=null;
		try {
		user = new ObjectMapper().readValue(request.getInputStream(), Utilisateur.class);
		} catch (Exception e) {
		throw new RuntimeException(e);
		}
		
		return authenticationManager.authenticate(
		new UsernamePasswordAuthenticationToken(
		user.getUsername(),
		user.getPassword()
		));
	
}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		User springUser=(User)authResult.getPrincipal();
		String jwtToken=Jwts.builder()
		   .setSubject(springUser.getUsername())
		   .setIssuedAt(new Date())
		   .setExpiration(new Date((new Date()).getTime() + SecurityConstants.EXPIRATION_TIME))
		   .signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET)
		   .claim("roles", springUser.getAuthorities())
		   //.claim("idUtilisateur",utilisateurRepository.loadCurrentUserId(springUser.getUsername()))
		   .compact();
		response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX+jwtToken);;
	}

	
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		super();
		this.authenticationManager = authenticationManager;
	}
	
	
}
