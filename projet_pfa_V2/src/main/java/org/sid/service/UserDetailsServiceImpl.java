package org.sid.service;

import java.util.ArrayList;
import java.util.Collection;

import org.sid.beans.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
    private UtilisateurService utilisateurService;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Utilisateur user=utilisateurService.findUserByUsername(username);
		if(user==null) throw new UsernameNotFoundException(username);
		Collection<GrantedAuthority> autorities=new ArrayList<>();
		user.getRoles().forEach(r->{
			autorities.add(new SimpleGrantedAuthority(r.getRoleName()));
		});
		return new User(user.getUsername(),user.getPassword(),autorities);
	}

	
}
