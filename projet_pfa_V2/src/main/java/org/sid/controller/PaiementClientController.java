package org.sid.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.sid.beans.PaiementClient;
import org.sid.beans.PaiementFournisseur;
import org.sid.service.PaiementClientService;
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
public class PaiementClientController {

	@Autowired
	private PaiementClientService PaiementClientService;
	
	@RequestMapping(value="/paiementclients",method=RequestMethod.GET)
	public List<PaiementClient> getPaiementClients(){
		return PaiementClientService.findAll();
	}
	
	@RequestMapping(value="/paiementclients/{id}",method=RequestMethod.GET)
	public Optional<PaiementClient> getPaiementClient(@PathVariable Long id){
		return PaiementClientService.findById(id);
	}
	
	@RequestMapping(value="/paiementclients",method=RequestMethod.POST)
	public PaiementClient savePaiementClient(@RequestBody PaiementClient paiementclient ){
		return PaiementClientService.save(paiementclient);
	}
	
	@RequestMapping(value="/paiementclients/{id}",method=RequestMethod.DELETE)
	public boolean deletePaiementClient(@PathVariable Long id){
		PaiementClientService.deleteById(id);
		 return true;	 
	}
	
	@RequestMapping(value="/paiementclients/{id}",method=RequestMethod.PUT)
	public PaiementClient updatePaiementClient(@PathVariable Long id,@RequestBody PaiementClient paiementclient){
		paiementclient.setIdPaiementClient(id); 
		return PaiementClientService.save(paiementclient);	 	 
	}
	
	@RequestMapping(value="/chercherpaiementclients",method=RequestMethod.GET)
    public Page<PaiementClient> chercherPaiementParClient(
    		    @RequestParam(name="nom",defaultValue="") String nom,
	    		@RequestParam(name="page",defaultValue="0") int page,
	    		@RequestParam(name="size",defaultValue="50") int size){
		
		return PaiementClientService.chercherPaiementParClient(nom, page, size);
	}
	
	//paiement par id facture
	@RequestMapping(value="/chercherpaiementfacture/{id}",method=RequestMethod.GET)
    public List<PaiementClient> chercherPaiementParId(@PathVariable Long id)
	    		{
		
		return PaiementClientService.chercherPaiementParId(id);
	}
	
	//calculer credit restant
	
	@RequestMapping(value="/calculercredit/{id}",method=RequestMethod.GET)
    public Long calculerCredit(@PathVariable Long id) {
		return PaiementClientService.calculerCredit(id);
	}
	//caisse
	@RequestMapping(value="/caisse/{debut}/{fin}",method=RequestMethod.GET)
	public Double caisse(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date  debut,
				         @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date fin) {
		return PaiementClientService.caisse(debut,fin);
	}
	
	//total paiement Client
	@RequestMapping(value="/totalpc/{debut}/{fin}",method=RequestMethod.GET)
	public Double totalPaiementClient(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date  debut,
		         @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date fin) {
		return PaiementClientService.totalPaiementClient(debut,fin);
		}
	//profit
		@RequestMapping(value="/profit/{debut}/{fin}",method=RequestMethod.GET)
		public Double profit(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date  debut,
					         @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date fin) {
			return PaiementClientService.profit(debut,fin);
		}
	
		}


