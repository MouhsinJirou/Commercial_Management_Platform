package org.sid.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.sid.beans.AvoirClient;
import org.sid.beans.AvoirFournisseur;
import org.sid.beans.DetailAvoirFr;
import org.sid.service.AvoirFournisseurService;
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
public class AvoirFournisseurController {
	
	@Autowired
	private AvoirFournisseurService avoirFournisseurService;
	
	
	@RequestMapping(value="/avoirfournisseurs",method=RequestMethod.GET)
	public List<AvoirFournisseur> getAvoirFournisseurs(){
		return avoirFournisseurService.findAll();
	}
	
	@RequestMapping(value="/avoirfournisseurs/{id}",method=RequestMethod.GET)
	public Optional<AvoirFournisseur> getAvoirFournisseur(@PathVariable Long id){
		return avoirFournisseurService.findById(id);
	}
	
	//Ljadid - ajouter avoirFourn/detailAvoirFourn
			@RequestMapping(value="/avoirfournisseurs",method=RequestMethod.POST)
			public boolean ajouterAvoirFournisseur(@RequestBody AvoirFournisseur avoirFournisseur){
				return avoirFournisseurService.ajouterAvoirFr(avoirFournisseur);
			}
	
	@RequestMapping(value="/avoirfournisseurs/{id}",method=RequestMethod.DELETE)
	public boolean save(@PathVariable Long id){
		 avoirFournisseurService.deleteById(id);
		 return true;	 
	}
	
	@RequestMapping(value="/avoirfournisseurs/{id}",method=RequestMethod.PUT)
	public AvoirFournisseur update(@PathVariable Long id,@RequestBody AvoirFournisseur avoirFournisseur){
		avoirFournisseur.setIdAvoirFr(id);
		return avoirFournisseurService.save(avoirFournisseur);	 	 
	}
	
	@RequestMapping(value="/chercheravoirfournisseurs",method=RequestMethod.GET)
    public Page<AvoirFournisseur> chercherAvoirParFournisseur(
    		    @RequestParam(name="nom",defaultValue="") String nom,
	    		@RequestParam(name="page",defaultValue="0") int page,
	    		@RequestParam(name="size",defaultValue="50") int size){
	
		return avoirFournisseurService.chercherAvoirParFournisseur(nom, page, size);
	}
	
	@RequestMapping(value="/avoirfournisseur/{id}/details",method=RequestMethod.GET)
	public List<DetailAvoirFr> chercherDetailAvoirFournisseur(@PathVariable Long id){
		return avoirFournisseurService.chercherDetailAvoirFournisseur(id);
	}
	//total avoir Fournisseur
	@RequestMapping(value="/totalaf/{debut}/{fin}",method=RequestMethod.GET)
	public Double totalAvoirFournisseur(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date  debut,
		         @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date fin) {
		return avoirFournisseurService.totalAvoirFournisseur(debut,fin);
		}



}
