package org.sid.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.sid.beans.AvoirFournisseur;
import org.sid.beans.BonAchat;
import org.sid.beans.Fournisseur;
import org.sid.service.FournisseurService;
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
public class FournisseurController {
	
	@Autowired
	private FournisseurService fournisseurService;
	
	@RequestMapping(value="/fournisseurs",method=RequestMethod.GET)
	public List<Fournisseur> getFournisseurs(){
		return fournisseurService.findAll();
	}
	
	@RequestMapping(value="/fournisseurs/{id}",method=RequestMethod.GET)
	public Optional<Fournisseur> getFournisseur(@PathVariable Long id){
		return fournisseurService.findById(id);
	}
	
	@RequestMapping(value="/fournisseurs",method=RequestMethod.POST)
	public Fournisseur save(@RequestBody Fournisseur fournisseur){
		return fournisseurService.save(fournisseur);
	}
	
	@RequestMapping(value="/fournisseurs/{id}",method=RequestMethod.DELETE)
	public boolean delete(@PathVariable Long id){
		 fournisseurService.deleteById(id);
		 return true;	 
	}
	
	@RequestMapping(value="/fournisseurs/{id}",method=RequestMethod.PUT)
	public Fournisseur update(@PathVariable Long id,@RequestBody Fournisseur fournisseur){
		fournisseur.setIdFournisseur(id); 
		return fournisseurService.save(fournisseur);	 	 
	}
	
	 @RequestMapping(value="/chercherfournisseurs",method=RequestMethod.GET)
	    public Page<Fournisseur> chercherParNomSociete(
	    		@RequestParam(name="nom",defaultValue="") String nom,
	    		@RequestParam(name="page",defaultValue="0") int page,
	    		@RequestParam(name="size",defaultValue="5") int size){
	    	
	    	return fournisseurService.chercherParNomSociete(nom,page,size);

	    }
	 
	    //Trouver tous les bon d'achat appartenant a un fournisseur 
		@RequestMapping(value="/fournisseur/{id}/bonachat",method=RequestMethod.GET)
		public Page<BonAchat> getBonAchatFromFournisseur(@PathVariable Long id,
				@RequestParam(name="page",defaultValue="0") int page,
				 @RequestParam(name="size",defaultValue="5") int size)
				{
			return fournisseurService.getBonAchatFromFournisseur(id,page,size);	 	 
		}
		
		@RequestMapping(value="/fournisseur/{id}/bonachat/{debut}/{fin}",method=RequestMethod.GET)
		 public Page<BonAchat> bonParFournisseur(@PathVariable Long id,
				 @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date  debut,
				 @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date fin,
				 @RequestParam(name="page",defaultValue="0") int page,
				 @RequestParam(name="size",defaultValue="5") int size)
				 {
			
			 return fournisseurService.bonParFournisseur(id,debut,fin,page,size);
			 //exemple de la rqt
			 //http://localhost:8080/fournisseur/3/bonachat/1300-06-06/2011-06-04
		 }
		
		//avoirs fournisseurs
		
		@RequestMapping(value="/fournisseur/{id}/avoir",method=RequestMethod.GET)
		public Page<AvoirFournisseur> avoirFournisseurId(@PathVariable Long id,
				@RequestParam(name="page",defaultValue="0") int page,
				 @RequestParam(name="size",defaultValue="5") int size)
				{
			return fournisseurService.avoirFournisseurId(id,page,size);	 	 
		}
		
		@RequestMapping(value="/fournisseur/{id}/avoir/{debut}/{fin}",method=RequestMethod.GET)
		 public Page<AvoirFournisseur> avoirFournisseurDate(@PathVariable Long id,
				 @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date  debut,
				 @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date fin,
				 @RequestParam(name="page",defaultValue="0") int page,
				 @RequestParam(name="size",defaultValue="5") int size)
				 {
			
			 return fournisseurService.avoirFournisseurDate(id,debut,fin,page,size);
				 }
		
		//Total Montant bonachat d'un fournisseur
		@RequestMapping(value="/totalbonachatsfournisseur/{id}",method=RequestMethod.GET)
		public Double totalBonAchatsFournisseur(@PathVariable Long id) {
			return fournisseurService.totalBonAchatsFournisseur(id);
		}
		//Total Montant versé d'un fournisseur
		@RequestMapping(value="/totalpayementsfournisseur/{id}",method=RequestMethod.GET)
		public Double totalPayementsFournisseur(@PathVariable Long id) {
			return fournisseurService.totalPayementsFournisseur(id);
		}

}
