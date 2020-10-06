package org.sid.controller;

import java.util.List;

import org.sid.service.DetailAvoirClientService;
import org.sid.service.DetailFactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
public class DetailFactureController {
	@Autowired
	private DetailFactureService detailFactureService;
	
	@RequestMapping(value="/facture/{id}/produits",method=RequestMethod.GET)
	public List<Object> getProduitsFromFacture(@PathVariable Long id){
		return detailFactureService.getProduitsFromFacture(id);
	}
	//elle retourne aussi le produit
		@RequestMapping(value="/factures/{id}/produits",method=RequestMethod.GET)
		public List<Object> getProduitsFromFacture2(@PathVariable Long id){
			return detailFactureService.getProduitsFromFacture2(id);
		}
}
