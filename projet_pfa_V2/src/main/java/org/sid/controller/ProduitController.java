package org.sid.controller;


import java.util.List;
import java.util.Optional;

import org.sid.beans.Produit;
import org.sid.service.ProduitService;
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
public class ProduitController {
	
	@Autowired
	private ProduitService produitService;
	
	@RequestMapping(value="/produits",method=RequestMethod.GET)
	public List<Produit> getProduits(){
		return produitService.findAll();
	}
	
	@RequestMapping(value="/produits/{id}",method=RequestMethod.GET)
	public Optional<Produit> getProduit(@PathVariable Long id){
		return produitService.findById(id);
	}
	
	@RequestMapping(value="/produits",method=RequestMethod.POST)
	public Produit save(@RequestBody Produit produit){
		return produitService.save(produit);
	}
	
	@RequestMapping(value="/produits/{id}",method=RequestMethod.DELETE)
	public void save(@PathVariable Long id){
		produitService.deleteById(id);
	}
	
	@RequestMapping(value="/produits/{id}",method=RequestMethod.PUT)
	public Produit update(@PathVariable Long id,@RequestBody Produit produit){
		produit.setIdProduit(id); 
		return produitService.save(produit);	 	 
	}
	 

	 
	 @RequestMapping(value="/chercherproduits",method=RequestMethod.GET)
	    public Page<Produit> chercherParDesignation(
	    		@RequestParam(name="des",defaultValue="") String des,
	    		@RequestParam(name="page",defaultValue="0") int page,
	    		@RequestParam(name="size",defaultValue="5") int size){
	    	
	    	return produitService.chercherParDesignation(des,page,size);

	    }
		
	 
	//Alert des produits
		 @RequestMapping(value="/alerterproduit",method=RequestMethod.GET)
		    public Page<Produit> alerterProduit(
		    		@RequestParam(name="des",defaultValue="") String des,
		    		@RequestParam(name="page",defaultValue="0") int page,
		    		@RequestParam(name="size",defaultValue="5") int size){
			 return produitService.alerterProduit(des,page, size);
			 }
		 
		 @RequestMapping(value="/nombrealerteproduit",method=RequestMethod.GET)
	        public int nombreAlerteProduit() {
	        	return produitService.nombreAlerteProduit();
	        }
		 
   //nombre produit
		 @RequestMapping(value="/nombreproduit",method=RequestMethod.GET)
		 public int nombreProduit() {
			 return produitService.nombreProduit();
}
		 
		 @RequestMapping(value="/nombreproduitrupture",method=RequestMethod.GET)
		 public int nombreProduitRupture() {
			 return produitService.nombreProduitRupture();
		 }
}