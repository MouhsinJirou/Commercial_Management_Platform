package org.sid.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.sid.beans.AvoirClient;
import org.sid.beans.BonAchat;
import org.sid.beans.Client;
import org.sid.beans.Facture;
import org.sid.service.ClientService;
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
public class ClientController {
	
	@Autowired
	private ClientService clientService;
	
	@RequestMapping(value="/clients",method=RequestMethod.GET)
	public List<Client> getClients(){
		return clientService.findAll();
	}
	
	@RequestMapping(value="/clients/{id}",method=RequestMethod.GET)
	public Optional<Client> getClients(@PathVariable Long id){
		return clientService.findById(id);
	}
	
	@RequestMapping(value="/clients",method=RequestMethod.POST)
	public Client saveClients(@RequestBody Client client){
		return clientService.save(client);
	}
	
	@RequestMapping(value="/clients/{id}",method=RequestMethod.DELETE)
	public boolean deleteClients(@PathVariable Long id){
		clientService.deleteById(id);
		 return true;	 
	}
	
	@RequestMapping(value="/clients/{id}",method=RequestMethod.PUT)
	public Client updateClients(@PathVariable Long id,@RequestBody Client client){
		client.setIdClient(id); 
		return clientService.save(client);	 	 
	}
	
	 @RequestMapping(value="/chercherclients",method=RequestMethod.GET)
	    public Page<Client> chercherParNom(
	    		@RequestParam(name="nom",defaultValue="") String nom,
	    		@RequestParam(name="page",defaultValue="0") int page,
	    		@RequestParam(name="size",defaultValue="5") int size){
	    	
	    	return clientService.chercherParNom(nom, page, size);

	    }
	 @RequestMapping(value="/client/{id}/factures",method=RequestMethod.GET)
	 public List<Facture> factureParClient(@PathVariable Long id){
		 return clientService.factureParClient(id);
	 }
	 
	//Trouver tous les bon d'achat appartenant a un fournisseur 
		@RequestMapping(value="/client/{id}/facture",method=RequestMethod.GET)
		public Page<Facture> factureClientId(@PathVariable Long id,
				@RequestParam(name="page",defaultValue="0") int page,
				 @RequestParam(name="size",defaultValue="5") int size)
				{
			return clientService.factureClientId(id,page,size);	 	 
		}
		
		@RequestMapping(value="/client/{id}/facture/{debut}/{fin}",method=RequestMethod.GET)
		 public Page<Facture> factureClientDate(@PathVariable Long id,
				 @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date  debut,
				 @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date fin,
				 @RequestParam(name="page",defaultValue="0") int page,
				 @RequestParam(name="size",defaultValue="5") int size)
				 {
			
			 return clientService.factureClientDate(id,debut,fin,page,size);
			 //exemple de la rqt
			 //http://localhost:8080/fournisseur/3/bonachat/1300-06-06/2011-06-04
		 }
		//Les avoirs d'un client
				@RequestMapping(value="/client/{id}/avoir",method=RequestMethod.GET)
				public Page<AvoirClient> avoirClientId(@PathVariable Long id,
						@RequestParam(name="page",defaultValue="0") int page,
						 @RequestParam(name="size",defaultValue="5") int size)
						{
					return clientService.avoirClientId(id,page,size);	 	 
				}
				
				@RequestMapping(value="/client/{id}/avoir/{debut}/{fin}",method=RequestMethod.GET)
				 public Page<AvoirClient> avoirClientDate(@PathVariable Long id,
						 @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date  debut,
						 @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date fin,
						 @RequestParam(name="page",defaultValue="0") int page,
						 @RequestParam(name="size",defaultValue="5") int size)
						 {
					
					 return clientService.avoirClientDate(id,debut,fin,page,size);
						 }
				
		//Total Montant factures d'un client
		@RequestMapping(value="/totalfacturesclient/{id}",method=RequestMethod.GET)
		public Double totalFacturesClient(@PathVariable Long id) {
			 return clientService.totalFacturesClient(id);
		}
		//Total Montant versé d'un client
		@RequestMapping(value="/totalpayementsclient/{id}",method=RequestMethod.GET)
		public Double totalPayementsClient(@PathVariable Long id) {
			 return clientService.totalPayementsClient(id);
		}
		
		@RequestMapping(value="/clientdumois/{debut}/{fin}",method=RequestMethod.GET)
		public Page<Object> clientDuMois(
				@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date  debut,
				 @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date fin,
				 @RequestParam(name="page",defaultValue="0") int page,
				 @RequestParam(name="size",defaultValue="5") int size){
			return clientService.clientDuMois(debut,fin,page,size);
		}

}