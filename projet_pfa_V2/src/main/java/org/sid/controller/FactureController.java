package org.sid.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.sid.beans.BonAchat;
import org.sid.beans.Facture;
import org.sid.service.FactureService;
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
public class FactureController {
	
	@Autowired
	private FactureService factureService;
	
	@RequestMapping(value="/factures",method=RequestMethod.GET)
	public List<Facture> getFactures(){
		return factureService.findAll();
	}
	
	@RequestMapping(value="/factures/{id}",method=RequestMethod.GET)
	public Optional<Facture> getFacture(@PathVariable Long id){
		return factureService.findById(id);
	}
	
	//Ljadid - ajouter facture/detailFacture
	@RequestMapping(value="/factures",method=RequestMethod.POST)
	public Facture ajouterFacture(@RequestBody Facture facture){
		return factureService.ajouterFacture(facture);
	}
	
	@RequestMapping(value="/factures/{id}",method=RequestMethod.DELETE)
	public boolean save(@PathVariable Long id){
		factureService.deleteById(id);
		 return true;	 
	}
	
	@RequestMapping(value="/factures/{id}",method=RequestMethod.PUT)
	public Facture update(@PathVariable Long id,@RequestBody Facture facture){
		facture.setIdFacture(id); 
		return factureService.save(facture);	 	 
	}
	
	 @RequestMapping(value="/chercherfactures",method=RequestMethod.GET)
	    public Page<Facture> chercherAllFacture(
	    		@RequestParam(name="page",defaultValue="0") int page,
	    		@RequestParam(name="size",defaultValue="50") int size){
	    	
	    	return factureService.chercherAllFacture(page, size);
}
	
	 // le tolal des vente avec ou sans date
	 @RequestMapping(value="/totalvente",method=RequestMethod.GET)
	    public Double totalVente() {
		 return factureService.totalVente();
	 }
	 
	 @RequestMapping(value="/totalvente/{debut}/{fin}",method=RequestMethod.GET)
	    public Double totalVenteDate(
	    		 @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date  debut,
				 @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date fin) 
	 {
		 return factureService.totalVenteDate(debut,fin);
	 }
	 @RequestMapping(value="/nombrevente/{debut}/{fin}",method=RequestMethod.GET)
	 public int nombreVenteDate(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date  debut,
				 @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date fin)
	 {
		 return factureService.nombreVenteDate(debut,fin);
	 }
     
	 //statistique rapport vente
	 @RequestMapping(value="/rapportvente/{debut}/{fin}",method=RequestMethod.GET)
	    public Page<Object> rapportVente(
	    		@RequestParam(name="page",defaultValue="0") int page,
	    		@RequestParam(name="size",defaultValue="50") int size,
	    		@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date  debut,
				 @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date fin){
	    	
	    	return factureService.rapportVente(debut,fin,page, size);
}
	//vente du jour
		 @RequestMapping(value="/ventejour/{debut}/{fin}",method=RequestMethod.GET)
		 public List<Object> venteJour(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date  debut,
				 @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date fin){
			 
			 return factureService.venteJour(debut, fin);
		 }

		 //Vente du mois
		 @RequestMapping(value="/ventemois/{debut}/{fin}",method=RequestMethod.GET)
		 public List<Object> venteMois(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date  debut,
				 @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date fin){
			 
			 return factureService.venteMois(debut, fin);
		 }
		//tracabilité
			@RequestMapping(value="/utilisateurs/tracerfacture/{id}",method=RequestMethod.GET)
			public List<Object> tracerFacture(@PathVariable Long id) {
				return factureService.tracerFacture(id);
			}
}
