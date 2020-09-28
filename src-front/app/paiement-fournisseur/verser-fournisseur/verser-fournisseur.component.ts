import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {DatePipe} from "@angular/common";
import {BonAchatService} from "../../../service/bonachat.service";
import {PaiementFournisseurService} from "../../../service/paiementfournisseur.service";
import {PaiementFournisseur} from "../../../model/model.paiementFournisseur";
import {FactureService} from "../../../service/facture.service";

@Component({
  selector: 'app-verser-fournisseur',
  templateUrl: './verser-fournisseur.component.html',
  styleUrls: ['./verser-fournisseur.component.scss'],
  providers: [DatePipe]

})
export class VerserFournisseurComponent implements OnInit {
  myDate:string;
  todayString : string = new Date().toDateString();
  idBonAchat:number;
  bonAchat:any;
  montantTotalBon:any;
  totalPaiementsBon:any;
  credits:any;
  overlapPayement:boolean;
  paiementFournisseur:PaiementFournisseur=new PaiementFournisseur();
  confirmation:boolean;
  idFournisseur:any;


  constructor(public activatedroute:ActivatedRoute,public datePipe:DatePipe,public bonAchatService:BonAchatService,public paiementFournisseurService:PaiementFournisseurService,public router:Router ) {
    this.idBonAchat = activatedroute.snapshot.params['id'];
    this.myDate = this.datePipe.transform(this.todayString, 'yyyy-MM-dd');
  }

  ngOnInit(): void {
    this.getInformationBonAchat();


  }

  getInformationBonAchat(){
    this.bonAchatService.getBonAchat(this.idBonAchat).subscribe(data=>{
      this.bonAchat=data;
      this.idFournisseur=this.bonAchat.fournisseur.idFournisseur;
      this.montantTotalBon=this.bonAchat.montantTotal;
    },error => {
      console.log(error);
    });
    this.paiementFournisseurService.totalPaiementsBon(this.idBonAchat).subscribe(data=>{
      this.totalPaiementsBon=data;
      this.credits=this.montantTotalBon-this.totalPaiementsBon;
    },error => {
      console.log(error);
    });
  }

  verser(dataForm){
    if(dataForm.montantVerse>this.credits){
      this.overlapPayement=true;
      alert('le montant versé est supérieur au crédit');
    }
    else {
      this.paiementFournisseur.date=dataForm.date;
      this.paiementFournisseur.montantVerse=dataForm.montantVerse;
      this.paiementFournisseur.bonAchat=this.bonAchat;
      this.paiementFournisseurService.savePaiementFournisseur(this.paiementFournisseur).subscribe(data=>{
        console.log(data);
        alert("Le montant est versé avec succée !");
        this.router.navigate(['listbonAchatsfournisseur',this.idFournisseur]); //passer l'id dans le lien
      },error => {
        console.log(error);
        this.confirmation=false;
      })
    }
  }
}
