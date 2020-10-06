package org.sid.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.sid.beans.AvoirClient;
import org.sid.beans.DetailAvoirClient;

import org.sid.service.AvoirClientService;
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
public class AvoirClientController {
	@Autowired
	private AvoirClientService avoirClientService;
	
	@RequestMapping(value="/avoirclients",method=RequestMethod.GET)
	public List<AvoirClient> getAvoirClients(){
		return avoirClientService.findAll();
	}
	
	@RequestMapping(value="/avoirclients/{id}",method=RequestMethod.GET)
	public Optional<AvoirClient> getAvoirClient(@PathVariable Long id){
		return avoirClientService.findById(id);
	}
	
	//Ljadid - ajouter avoirClient/detailAvoirClient
		@RequestMapping(value="/avoirclients",method=RequestMethod.POST)
		public boolean ajouterAvoirClient(@RequestBody AvoirClient avoirClient){
			return avoirClientService.ajouterAvoirClient(avoirClient);
		}
	
	@RequestMapping(value="/avoirclients/{id}",method=RequestMethod.DELETE)
	public boolean deleteAvoirClient(@PathVariable Long id){
		avoirClientService.deleteById(id);
		 return true;	 
	}
	
	@RequestMapping(value="/avoirclients/{id}",method=RequestMethod.PUT)
	public AvoirClient updateAvoirClient(@PathVariable Long id,@RequestBody AvoirClient avoirClient){
		avoirClient.setIdAvoirClient(id);
		return avoirClientService.save(avoirClient);	 	 
	}
	@RequestMapping(value="/chercheravoirclients",method=RequestMethod.GET)
    public Page<AvoirClient> chercherAvoirParClient(
    		    @RequestParam(name="nom",defaultValue="") String nom,
	    		@RequestParam(name="page",defaultValue="0") int page,
	    		@RequestParam(name="size",defaultValue="50") int size){
		
		return avoirClientService.chercherAvoirParClient(nom, page, size);
	}
	
	@RequestMapping(value="/avoirclient/{id}/details",method=RequestMethod.GET)
	public List<DetailAvoirClient> chercherDetailsAvoirClient(@PathVariable Long id){
		return avoirClientService.chercherDetailsAvoirClient(id);
	}
	
	//total avoir Client
		@RequestMapping(value="/totalac/{debut}/{fin}",method=RequestMethod.GET)
		public Double totalAvoirClient(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date  debut,
			         @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date fin) {
			return avoirClientService.totalAvoirClient(debut,fin);
			}
		
	
	


}
