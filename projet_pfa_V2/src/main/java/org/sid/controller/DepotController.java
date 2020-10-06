package org.sid.controller;

import java.util.List;
import java.util.Optional;

import org.sid.beans.Depot;
import org.sid.beans.Produit;
import org.sid.service.DepotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class DepotController {
	
	@Autowired
	private DepotService depotService;
	
	
	@RequestMapping(value="/depots",method=RequestMethod.GET)
	public List<Depot> getDepots(){
		return depotService.findAll();
	}
	
	@RequestMapping(value="/depots/{id}",method=RequestMethod.GET)
	public Optional<Depot> getDepot(@PathVariable Long id){
		return depotService.findById(id);
	}
	
	@RequestMapping(value="/depots",method=RequestMethod.POST)
	public Depot saveDepot(@RequestBody Depot depot ){
		return depotService.save(depot);
	}
	
	@RequestMapping(value="/depots/{id}",method=RequestMethod.DELETE)
	public boolean deleteDepot(@PathVariable Long id){
		depotService.deleteById(id);
		 return true;	 
	}
	
	@RequestMapping(value="/depots/{id}",method=RequestMethod.PUT)
	public Depot updateDepot(@PathVariable Long id,@RequestBody Depot depot){
		depot.setIdDepot(id); 
		return depotService.save(depot);	 	 
	}
	

	

	 @RequestMapping(value="/chercherdepots",method=RequestMethod.GET)
	    public Page<Depot> chercherParEmplacement(
	    		@RequestParam(name="emp",defaultValue="") String emp,
	    		@RequestParam(name="page",defaultValue="0") int page,
	    		@RequestParam(name="size",defaultValue="50") int size){
	    	
	    	return depotService.chercherParEmplacement(emp, page, size);

	    }

	//Chercher les produits d'un depot par idDepot .... depot not depots
	 @RequestMapping(value="/depot/{id}/produits",method=RequestMethod.GET)
	    public Page<Produit> getProduitsFromDepot(
	    		@PathVariable Long id,
	    		@RequestParam(name="page",defaultValue="0") int page,
	    		@RequestParam(name="size",defaultValue="5") int size){
	    	
	    	return depotService.getProduitsFromDepot(id,page, size);

	    }
	 // Chercher les produits d'un depot(id) selon la designation ... url depots avec s
	 @RequestMapping(value="/depots/{id}/produits",method=RequestMethod.GET)
	    public Page<Produit> getProduitsByDesignation(
	    		@PathVariable Long id,
	    		@RequestParam(name="designation",defaultValue="") String designation,
	    		@RequestParam(name="page",defaultValue="0") int page,
	    		@RequestParam(name="size",defaultValue="5") int size){
	    	
	    	return depotService.getProduitsByDesignation(id,designation,page, size);

	    }
}
