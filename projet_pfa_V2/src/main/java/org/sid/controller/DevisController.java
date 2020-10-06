package org.sid.controller;

import java.util.List;
import java.util.Optional;

import org.sid.beans.BonAchat;
import org.sid.beans.Devis;
import org.sid.dao.DevisRepository;
import org.sid.service.DevisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@RestController
@CrossOrigin
public class DevisController {
	
	@Autowired
	private DevisService devisService;
	
	
	@RequestMapping(value="/devis",method=RequestMethod.GET)
	public List<Devis> getDeviss(){
		return devisService.findAll();
	}
	
	@RequestMapping(value="/devis/{id}",method=RequestMethod.GET)
	public Optional<Devis> getDevis(@PathVariable Long id){
		return devisService.findById(id);
	}
	
	//Ljadid - Ajouter devis/detailDevis
			@RequestMapping(value="/devis",method=RequestMethod.POST)
			public boolean ajouterDevis(@RequestBody Devis devis){
				return devisService.ajouterDevis(devis);
			}
	
	@RequestMapping(value="/devis/{id}",method=RequestMethod.DELETE)
	public boolean save(@PathVariable Long id){
		devisService.deleteById(id);
		 return true;	 
	}
	
	@RequestMapping(value="/devis/{id}",method=RequestMethod.PUT)
	public Devis update(@PathVariable Long id,@RequestBody Devis devis){
		devis.setIdDevis(id); 
		return devisService.save(devis);	 	 
	}
	
	 @RequestMapping(value="/chercherdevis",method=RequestMethod.GET)
	    public Page<Devis> chercherAllDevis(
	    		@RequestParam(name="page",defaultValue="0") int page,
	    		@RequestParam(name="size",defaultValue="50") int size){
	    	
	    	return devisService.chercherAllDevis(page, size);

}

}
