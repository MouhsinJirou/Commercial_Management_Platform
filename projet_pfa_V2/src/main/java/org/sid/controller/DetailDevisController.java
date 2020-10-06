package org.sid.controller;

import java.util.List;
import java.util.Optional;

import org.sid.beans.DetailDevis;
import org.sid.beans.Devis;
import org.sid.beans.Produit;
import org.sid.dao.DetailDevisRepository;
import org.sid.service.DetailDevisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class DetailDevisController {

	@Autowired
	private DetailDevisService detailDevisService;
	
	@RequestMapping(value="/detaildevis",method=RequestMethod.GET)
	public List<DetailDevis> getDetailDeviss(){
		return detailDevisService.findAll();
	}
	
	@RequestMapping(value="/devis/{idDevis}/produits/{idProduit}",method=RequestMethod.GET)
	public Optional<DetailDevis> getDetailDevis(@PathVariable(name="idDevis")Long idDevis,@PathVariable(name="idDevis")Long idProduit){
		return detailDevisService.findByDevisProduit(idDevis,idProduit);
	}
		
	
//	@RequestMapping(value="/detaildevis/{id}",method=RequestMethod.POST)
//	public DetailDevis save(@RequestBody DetailDevis detaildevis){
//		return detaildevisRepository.save(detaildevis);
//	}
//	
//	@RequestMapping(value="/detaildevis/{id}",method=RequestMethod.DELETE)
//	public boolean save(@PathVariable DetailDevis_PK id){
//		 detaildevisRepository.deleteById(id);
//		 return true;	 
//	}
//	
//	@RequestMapping(value="/detaildevis/{id}",method=RequestMethod.PUT)
//	public DetailDevis update(@PathVariable DetailDevis_PK id,@RequestBody DetailDevis detaildevis){
//		detaildevis.setPkd(id); 
//		return detaildevisRepository.save(detaildevis);	 	 
//	}
	//Afficher tous les produits d'une facture précise(idFacture)
	@RequestMapping(value="/devis/{id}/produits",method=RequestMethod.GET)
	public List<Object> getProduitsFromDevis(@PathVariable Long id)
			{
		return detailDevisService.getProduitsFromDevis(id);
	}
}