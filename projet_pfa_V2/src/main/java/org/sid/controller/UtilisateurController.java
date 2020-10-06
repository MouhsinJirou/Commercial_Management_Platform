package org.sid.controller;

import java.util.List;
import java.util.Optional;

import org.sid.beans.Utilisateur;
import org.sid.dao.UtilisateurRepository;
import org.sid.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UtilisateurController {
	
	@Autowired
	private UtilisateurService utilisateurService;
	
	@RequestMapping(value="/utilisateurs",method=RequestMethod.GET)
	public List<Utilisateur> getUtilisateurs(){
		return utilisateurService.findAll();
	}
	
	@RequestMapping(value="/utilisateurs/{id}",method=RequestMethod.GET)
	public Optional<Utilisateur> getUtilisateur(@PathVariable Long id){
		return utilisateurService.findById(id);
	}
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public Utilisateur saveUtilisateur(@RequestBody RegisterForm userForm ){
		Utilisateur utilisateur=new Utilisateur();
		if(!userForm.getPassword().equals(userForm.getRepassword())) throw new RuntimeException("Vous devez confirmer votre mot de passe");
		
		Utilisateur user=utilisateurService.findUserByUsername(userForm.getUsername());
		if(user!=null) {
			throw new RuntimeException(" nom d'utilisateur existe déja");
		}
		
		Utilisateur u=utilisateurService.findByEmail(userForm.getEmail());
		if(u!=null) {
			throw new RuntimeException("adresse mail existe déjà");
		}
				
				
		utilisateur.setNomUtilisateur(userForm.getNomUtilisateur());
		utilisateur.setPrenomUtilisateur(userForm.getPrenomUtilisateur());
		utilisateur.setUsername(userForm.getUsername());
		utilisateur.setPassword(userForm.getPassword());
		utilisateur.setEmail(userForm.getEmail());

		utilisateurService.saveUtilisateur(utilisateur); 
		utilisateurService.AddRoleToUser(userForm.getUsername(), "USER");
		
		return utilisateur;
	}
	
	@RequestMapping(value="/utilisateurs/{id}",method=RequestMethod.DELETE)
	public boolean deleteUtilisateur(@PathVariable Long id){
		utilisateurService.deleteById(id);
		 return true;	 
	}
	
	@RequestMapping(value="/utilisateurs/{id}",method=RequestMethod.PUT)
	public Utilisateur updateUtilisateur(@PathVariable Long id,@RequestBody Utilisateur utilisateur){
		utilisateur.setIdUtilisateur(id); 
		return utilisateurService.saveUtilisateur(utilisateur);	 	 
	}
	
	@RequestMapping(value="/loadcurrentuser",method=RequestMethod.GET)
	public Utilisateur loadCurrentUser(@RequestParam(name="username") String username) {
		return utilisateurService.loadCurrentUser(username);
	}
	
	@RequestMapping(value="/chercherutilisateur",method=RequestMethod.GET)
	public List<Utilisateur> chercherUtilisateur(@RequestParam(name="nom",defaultValue="") String nom){
		return utilisateurService.chercherUtilisateur(nom);
	}
	
	
	

}
