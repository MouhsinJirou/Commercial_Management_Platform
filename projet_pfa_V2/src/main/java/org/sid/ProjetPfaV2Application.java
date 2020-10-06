package org.sid;

import org.sid.beans.AppRole;
import org.sid.beans.Produit;
import org.sid.beans.Utilisateur;
import org.sid.dao.ProduitRepository;
import org.sid.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ProjetPfaV2Application implements CommandLineRunner{

	@Autowired
	private ProduitRepository produitRepository;
	@Autowired
	private UtilisateurService utilisateurService;
	public static void main(String[] args) {
		SpringApplication.run(ProjetPfaV2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		/*utilisateurService.saveUtilisateur(new Utilisateur("daoudi", "ayoub", "admin", "1234", "ayoub@gmail.com"));
		utilisateurService.saveUtilisateur(new Utilisateur("hibati", "hiba", "user", "1234", "hiba@gmail.com"));
		utilisateurService.saveRole(new AppRole("ADMIN"));
		utilisateurService.saveRole(new AppRole("USER"));
		utilisateurService.AddRoleToUser("admin", "ADMIN");
		utilisateurService.AddRoleToUser("admin", "USER");
		utilisateurService.AddRoleToUser("user", "USER");*/
	}
	
	@Bean
	public BCryptPasswordEncoder getBCPE() {
		return new BCryptPasswordEncoder();
	}

}
