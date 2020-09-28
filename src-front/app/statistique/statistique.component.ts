import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {BonAchatService} from "../../service/bonachat.service";
import {FactureService} from "../../service/facture.service";
import {ChargeService} from "../../service/charge.service";
import {ProduitService} from "../../service/produit.service";
import {PaiementClientService} from "../../service/paiementclient.service";

@Component({
  selector: 'app-statistique',
  templateUrl: './statistique.component.html',
  styleUrls: ['./statistique.component.scss']
})
export class StatistiqueComponent implements OnInit {
  d1:Date;
  d2:Date;
  statut:boolean=false //pour afficher le bloc de la recherche par dates
  totalCharge:any;
  totalVente:any;
  totalAchat:any;
  totalChargeDate:any=0;
  totalVenteDate:any=0;
  totalAchatDate:any=0;
  listChargeDate:any;
  totalpageCharge:number;
  currentPageCharge:number=0;
  currentPageVente:number=0;
  pagesCharge:Array<number>;
  pagesVente:Array<number>;
  size:number=5;
  listVenteDate:any;
  totalpageVente:number;
  array=[];
  caisseDate:any;

  constructor(public http:HttpClient,public chargeService:ChargeService,public factureService:FactureService,public bonAchatService:BonAchatService,public produitService:ProduitService,public paiementClient:PaiementClientService) { }

  chercherDate(){
    if (this.d1 != null && this.d2!=null) {
      this.statut=true;
      this.chargeService.totalChargeDate(this.d1,this.d2).subscribe(data=>{
        this.totalChargeDate=data;
      },error => {
        console.log(error);
      })
      this.factureService.totalVenteDate(this.d1,this.d2).subscribe(data=>{
        this.totalVenteDate=data;
      },error => {
        console.log(error);
      })
      this.bonAchatService.totalAchatDate(this.d1,this.d2).subscribe(data=>{
        this.totalAchatDate=data;
      },error => {
        console.log(error);
      })
      this.chargeService.listChargeParDate(this.d1,this.d2,this.currentPageCharge,this.size).subscribe(data=>{
        this.listChargeDate=data;
        this.totalpageCharge=this.listChargeDate.totalPages;
        this.pagesCharge=new Array<number>(this.listChargeDate.totalPages);
      },error => {
        console.log(error);
      })
      this.factureService.rapportVente(this.d1,this.d2,this.currentPageVente,this.size).subscribe(data=>{
        this.listVenteDate=data;
        this.totalpageVente=this.listVenteDate.totalPages;
        this.pagesVente=new Array<number>(this.listVenteDate.totalPages);

        //prix achat total d'un produit
        for (let i in this.listVenteDate?.content){
          this.produitService.prixAchatTotal(this.listVenteDate.content[i][0].idProduit).subscribe(data=>{
            this.array.push(data);
          },error => {
            console.log(error);
          })
        }
      },error => {
        console.log(error);
      })
        this.paiementClient.caisse(this.d1,this.d2).subscribe(data=>{
          this.caisseDate=data;
        },error => {
          console.log(error);
        })
    }
    else this.statut=false;
  }
  gotoPageCharge(i:number){
    if(i>=this.totalpageCharge){
      this.currentPageCharge=this.totalpageCharge-1;
      this.chercherDate();
    }
    else if(i<=0){
      this.currentPageCharge=0;
      this.chercherDate();
    }
    else{
      this.currentPageCharge=i;
      this.chercherDate();
    }}

  gotoPageVente(i:number){
    if(i>=this.totalpageVente){
      this.currentPageVente=this.totalpageVente-1;
      this.chercherDate();
    }
    else if(i<=0){
      this.currentPageVente=0;
      this.chercherDate();
    }
    else{
      this.currentPageVente=i;
      this.chercherDate();
    }}

  ngOnInit(): void {
    this.chercher();
  }

  chercher(){
    this.chargeService.totalCharge().subscribe(data=>{
      this.totalCharge=data;
    },error => {
      console.log(error);
    })
    this.factureService.totalVente().subscribe(data=>{
      this.totalVente=data;
    },error => {
      console.log(error);
    })
    this.bonAchatService.totalAchat().subscribe(data=>{
      this.totalAchat=data;
    },error => {
      console.log(error);
    })
  }

}
