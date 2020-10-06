package org.sid.controller;

import java.util.List;

import org.sid.service.AchatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin 
public class AchatController {

	@Autowired AchatService achatService;
	
	@RequestMapping(value="/bonachat/{id}/produits",method=RequestMethod.GET)
	public List<Object> getProduitsFromBonAchat(@PathVariable Long id)
			{
		return achatService.getProduitsFromBonAchat(id);
	}
	//calcule prix achat moyenne
		@RequestMapping(value="/produits/prixachat/{id}",method=RequestMethod.GET)
		public Double getPrixAchat(@PathVariable Long id)
				{
			return achatService.getPrixAchat(id);
		}
		
		//prix achat total d'un produit
		@RequestMapping(value="/produits/prixachattotal/{id}",method=RequestMethod.GET)
		public Double prixAchatTotal(@PathVariable Long id) {
			return achatService.prixAchatTotal(id);
		}
		
		//les produits d'un bon qui retourne un produit
		@RequestMapping(value="/bonachats/{id}/produits",method=RequestMethod.GET)
		public List<Object> getProduitsFromBonAchat2(@PathVariable Long id)
				{
			return achatService.getProduitsFromBonAchat2(id);
}}