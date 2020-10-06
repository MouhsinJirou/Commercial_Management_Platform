package org.sid.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.sid.beans.BonAchat;
import org.sid.beans.Depot;
import org.sid.service.BonAchatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class BonAchatController {
	
	@Autowired
	private BonAchatService bonAchatService;
	
	@RequestMapping(value="/bonachats",method=RequestMethod.GET)
	public List<BonAchat> getBonAchats(){
		return bonAchatService.findAll();
	}
	
	@RequestMapping(value="/bonachats/{id}",method=RequestMethod.GET)
	public Optional<BonAchat> getBonAchat(@PathVariable Long id){
		return bonAchatService.findById(id);
	}

	
	@RequestMapping(value="/bonachats/{id}",method=RequestMethod.DELETE)
	public boolean deleteBonAchat(@PathVariable Long id){
		bonAchatService.deleteById(id);
		 return true;	 
	}
	
	@RequestMapping(value="/bonachats/{id}",method=RequestMethod.PUT)
	public BonAchat updateBonAchat(@PathVariable Long id,@RequestBody BonAchat bonAchat){
		bonAchat.setIdBonAchat(id); 
		return bonAchatService.save(bonAchat);	 	 
	}



	 @RequestMapping(value="/chercherbonachats",method=RequestMethod.GET)
	    public Page<BonAchat> chercherAllAchat(
	    		@RequestParam(name="page",defaultValue="0") int page,
	    		@RequestParam(name="size",defaultValue="50") int size){
	    	
	    	return bonAchatService.chercherAllAchat(page, size);
}
	
	 // le tolal des achats avec ou sans date
	 @RequestMapping(value="/totalachat",method=RequestMethod.GET)
	    public Double totalAchat() {
		 return bonAchatService.totalAchat();
	 }
	 
	 @RequestMapping(value="/totalachat/{debut}/{fin}",method=RequestMethod.GET)
	    public Double totalAchatDate(
	    		 @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date  debut,
				 @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date fin) 
	 {
		 return bonAchatService.totalAchatDate(debut,fin);
	 }
	 @RequestMapping(value="/nombreachat/{debut}/{fin}",method=RequestMethod.GET)
	 public int nombreAchatDate(
			     @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date  debut,
				 @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date fin) {
		 return bonAchatService.nombreAchatDate(debut,fin);
	 }
	 
	//Achat jour
		 @RequestMapping(value="/achatjour/{debut}/{fin}",method=RequestMethod.GET)
		 public List<Object> achatJour(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date  debut,
				 @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date fin){
			 
			 return bonAchatService.achatJour(debut, fin);
		 }
		 //achat mois
		 @RequestMapping(value="/achatmois/{debut}/{fin}",method=RequestMethod.GET)
		 public List<Object> achatMois(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date  debut,
				 @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date fin){
			 
			 return bonAchatService.achatMois(debut, fin);
		 }
		 
		 
		//Ljadid - Ajouter bon achat/achat
			@RequestMapping(value="/bonachats",method=RequestMethod.POST)
			public BonAchat ajouterAchat(@RequestBody BonAchat bonAchat){
				return bonAchatService.ajouterAchat(bonAchat);
			}
			
			//tracabilité
			@RequestMapping(value="/utilisateurs/tracerbon/{id}",method=RequestMethod.GET)
			public List<Object> tracerBon(@PathVariable Long id) {
				return bonAchatService.tracerBon(id);
			}
}
