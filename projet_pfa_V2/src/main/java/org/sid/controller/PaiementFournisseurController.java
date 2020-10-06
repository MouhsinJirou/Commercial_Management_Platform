package org.sid.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.sid.beans.PaiementFournisseur;

import org.sid.dao.PaiementFournisseurRepository;
import org.sid.service.PaiementFournisseurService;
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
public class PaiementFournisseurController {
	
	@Autowired
	private PaiementFournisseurService paiementFournisseurService;
	
	@RequestMapping(value="/paiementfournisseurs",method=RequestMethod.GET)
	public List<PaiementFournisseur> getPaiementFournisseur(){
		return paiementFournisseurService.findAll();
	}
	
	@RequestMapping(value="/paiementfournisseurs/{id}",method=RequestMethod.GET)
	public Optional<PaiementFournisseur> getProduit(@PathVariable Long id){
		return paiementFournisseurService.findById(id);
	}
	
	@RequestMapping(value="/paiementfournisseurs",method=RequestMethod.POST)
	public PaiementFournisseur save(@RequestBody PaiementFournisseur paiementFournisseur){
		return paiementFournisseurService.save(paiementFournisseur);
	}
	
	@RequestMapping(value="/paiementfournisseurs/{id}",method=RequestMethod.DELETE)
	public boolean delete(@PathVariable Long id){
		paiementFournisseurService.deleteById(id);
		 return true;	 
	}
	
	@RequestMapping(value="/paiementfournisseurs/{id}",method=RequestMethod.PUT)
	public PaiementFournisseur update(@PathVariable Long id,@RequestBody PaiementFournisseur paiementFournisseur){
		paiementFournisseur.setIdPaiementFournisseur(id);
		return paiementFournisseurService.save(paiementFournisseur);	 	 
	}
	@RequestMapping(value="/chercherpaiementfournisseurs",method=RequestMethod.GET)
    public Page<PaiementFournisseur> chercherPaiementParFournisseur(
    		    @RequestParam(name="nom",defaultValue="") String nom,
	    		@RequestParam(name="page",defaultValue="0") int page,
	    		@RequestParam(name="size",defaultValue="50") int size){
		
		return paiementFournisseurService.chercherPaiementParFournisseur(nom, page, size);
	}
	@RequestMapping(value="/chercherpaiementsparbon/{id}",method=RequestMethod.GET)
	public List<PaiementFournisseur> chercherPaiementParBon(@PathVariable Long id){
		return paiementFournisseurService.chercherPaiementParBon(id);
	}
	
	@RequestMapping(value="/totalpaiementsbon/{id}",method=RequestMethod.GET)
	public Long totalPaiementsBon(@PathVariable Long id){
		return paiementFournisseurService.totalPaiementsBon(id);
				}
	
	//total paiement Fournisseur
		@RequestMapping(value="/totalpf/{debut}/{fin}",method=RequestMethod.GET)
		public Double totalPaiementFournisseur(
				     @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date  debut,
			         @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date fin) {
			return paiementFournisseurService.totalPaiementFournisseur(debut,fin);
			}

}
