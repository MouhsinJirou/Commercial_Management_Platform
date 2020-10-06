package org.sid.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.sid.beans.Charge;
import org.sid.beans.Produit;
import org.sid.service.ChargeService;
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
public class ChargeController {
	
	@Autowired
	private ChargeService chargeService;
	
	@RequestMapping(value="/charges",method=RequestMethod.GET)
	public List<Charge> getCharges(){
		return chargeService.findAll();
	}
	
	@RequestMapping(value="/charges/{id}",method=RequestMethod.GET)
	public Optional<Charge> getCharge(@PathVariable Long id){
		return chargeService.findById(id);
	}
	
	@RequestMapping(value="/charges",method=RequestMethod.POST)
	public Charge saveCharge(@RequestBody Charge charge ){
		return chargeService.save(charge);
	}
	
	@RequestMapping(value="/charges/{id}",method=RequestMethod.DELETE)
	public boolean deleteCharge(@PathVariable Long id){
		chargeService.deleteById(id);
		 return true;	 
	}
	
	@RequestMapping(value="/charges/{id}",method=RequestMethod.PUT)
	public Charge updateCharge(@PathVariable Long id,@RequestBody Charge charge){
		charge.setIdCharge(id); 
		return chargeService.save(charge);	 	 
	}


	 @RequestMapping(value="/cherchercharges",method=RequestMethod.GET)
	    public Page<Charge> chercherParLibelle(
	    		@RequestParam(name="libelle",defaultValue="") String libelle,
	    		@RequestParam(name="page",defaultValue="0") int page,
	    		@RequestParam(name="size",defaultValue="5") int size){
	    	
	    	return chargeService.chercherParLibelle(libelle,page,size);

	    }
	 
	 // le tolal des charges avec ou sans date
	 @RequestMapping(value="/totalcharge",method=RequestMethod.GET)
	    public Double totalCharge() {
		 return chargeService.totalCharge();
	 }
	 
	 @RequestMapping(value="/totalcharge/{debut}/{fin}",method=RequestMethod.GET)
	    public Double totalChargeDate(
	    		 @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date  debut,
				 @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date fin) 
	 {
		 return chargeService.totalChargeDate(debut,fin);
	 }

	 @RequestMapping(value="/nombrecharge/{debut}/{fin}",method=RequestMethod.GET)
	    public int nombreChargeDate(
	    		 @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date  debut,
				 @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date fin) 
	 {
		 return chargeService.nombreChargeDate(debut,fin);
	 }
	 
	 //chercher charge par date
	 @RequestMapping(value="/chargepardate/{debut}/{fin}",method=RequestMethod.GET)
	    public Page<Charge> chercherParDate(
	    		 @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date  debut,
				 @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date fin,
				 @RequestParam(name="page",defaultValue="0") int page,
		    	@RequestParam(name="size",defaultValue="5") int size) 
	 {
		 return chargeService.chercherParDate(debut,fin,page,size);
	 }
	 
	//charge des jour
		 @RequestMapping(value="/chargejour/{debut}/{fin}",method=RequestMethod.GET)
		 public List<Object> chargeJour(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date  debut,
				 @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date fin){
			 
			 return chargeService.chargeJour(debut, fin);
		 }
		 
		 //charge mois
		 @RequestMapping(value="/chargemois/{debut}/{fin}",method=RequestMethod.GET)
		 public List<Object> chargeMois(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date  debut,
				 @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date fin){
			 
			 return chargeService.chargeMois(debut, fin);
		 }
	 
		//tracabilité
			@RequestMapping(value="/utilisateurs/tracercharge/{id}",method=RequestMethod.GET)
			public List<Object> tracerCharge(@PathVariable Long id) {
				return chargeService.tracerCharge(id);
			}
}