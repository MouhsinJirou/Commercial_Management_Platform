package org.sid.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.sid.beans.AppRole;
import org.sid.beans.Utilisateur;
import org.sid.dao.RoleRepository;
import org.sid.dao.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UtilisateurService {

	@Autowired
	private UtilisateurRepository utilisateurRepository;
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private RoleRepository roleRepository;
	
	public List<Utilisateur> findAll() {
		return utilisateurRepository.findAll();
		
	}

	public Optional<Utilisateur> findById(Long id) {
		return utilisateurRepository.findById(id);
		 
	}

	public Utilisateur save(Utilisateur utilisateur) {
		return utilisateurRepository.save(utilisateur);
		
	}

	public void deleteById(Long id) {
		utilisateurRepository.deleteById(id);
		
	}
	
	public Utilisateur saveUtilisateur(Utilisateur user) {
		String hashPW=bCryptPasswordEncoder.encode(user.getPassword());
		user.setPassword(hashPW);
		return utilisateurRepository.save(user);
	}
	
	public AppRole saveRole(AppRole role) {
		return roleRepository.save(role);
	}
	
	public void AddRoleToUser(String username,String roleName) {
		AppRole role=roleRepository.findByRoleName(roleName);
		Utilisateur user=utilisateurRepository.findByUsername(username);
		user.getRoles().add(role);
	}
	
	public Utilisateur findUserByUsername(String username) {
		return utilisateurRepository.findByUsername(username);
	}
	
	public Utilisateur loadCurrentUser(String username) {
		return utilisateurRepository.loadCurrentUser(username);
	}
	
	public Utilisateur findByEmail(String email) {
		return utilisateurRepository.findByEmail(email);
	}
	
	public List<Utilisateur> chercherUtilisateur(String nom){
		return utilisateurRepository.chercherUtilisateur("%"+nom+"%");
	}

}
