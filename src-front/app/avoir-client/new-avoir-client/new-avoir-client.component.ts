import { Component, OnInit } from '@angular/core';
import {Row} from "../../../model/row";
import {AvoirClient} from "../../../model/model.avoirclient";
import {FactureService} from "../../../service/facture.service";
import {AvoirClientService} from "../../../service/avoirclient.service";
import {DatePipe} from "@angular/common";
import {PaiementFournisseurService} from "../../../service/paiementfournisseur.service";
import {AuthService} from "../../../service/AuthService.service";

@Component({
  selector: 'app-new-avoir-client',
  templateUrl: './new-avoir-client.component.html',
  styleUrls: ['./new-avoir-client.component.scss'],
  providers: [DatePipe]

})
export class NewAvoirClientComponent implements OnInit {

  row:Row=new Row();
  array=[];
  idFacture:number;
  detailFacture:any;
  produit:any;
  total2:number=0;
  total:number=0;
  prix:number;
  qteNew:number;
  myDate:string;
  todayString : string = new Date().toDateString();
  private etat: boolean=true;
  avoirClient:AvoirClient=new AvoirClient();
  newArray=[];
  utilisateur:any=null;

  constructor(public factureService:FactureService,public datePipe:DatePipe,public avoirClientService:AvoirClientService,public authService:AuthService) {
    //recuperer la date d ajourdhui
    this.myDate = this.datePipe.transform(this.todayString, 'yyyy-MM-dd');
  }

  ngOnInit(): void {
    this.row=new Row();
    this.array.push(this.row);
    this.utilisateur=this.authService.user.getValue().utilisateur;

  }
  addRow(){
    this.row=new Row();
    this.array.push(this.row);
  }
  removeRow(index){
    this.array.splice(index);
    //Calculer le total instantané
    this.total2=0;
    for (let i in this.array) {
      this.total2 = this.total2 + this.array[i].produit[1] * this.array[i].qte;
    }

  }



  chercher(){
    this.factureService.getProduitsFacture2(this.idFacture).subscribe(data=>{
      this.detailFacture=data;
      console.log(JSON.stringify(this.detailFacture));

    },err=>console.log(err))
  }

  onSaveAvoir(){

    //calculer le montant
    for (let i in this.array) {
      this.total = this.total + this.array[i].produit[1] * this.array[i].qte;
    }
    //verification de qte saisie
    this.etat=true;

    for (let i in this.array) {
      if (this.array[i].produit[2] < this.array[i].qte) {

        this.etat = false;
        this.total=0;
        alert("Vous avez saisi une quantité supérieur à celle de la facture !");
        break
      }
    }
    if(this.etat==true){
      //facture
      this.factureService.getFacture(this.idFacture).subscribe(data=>{

       this.avoirClient.utilisateur=this.utilisateur;

        this.avoirClient.facture=data;

        //date
        this.avoirClient.dateAvoirClient=this.myDate;


        //montant total:
        this.avoirClient.montantAvoirCl=this.total;

        //DetailAvoir
        for (let i in this.array) {
          this.newArray.push({"qte":this.array[i].qte,"prix":this.array[i].produit[1],"produit":this.array[i].produit[0]})
        }
        this.avoirClient.detailsAvoirClient=this.newArray;
        console.log(JSON.stringify(this.avoirClient));
        this.avoirClientService.saveAvoirClient(this.avoirClient).subscribe(data=>{

          alert("l'avoir est bien ajouté !");
          window.location.reload();

        },error => console.log("Une erreur est survenue lors de l'ajout !"))


      },error=>console.log(error))

    }

  }



  onQte(qte){
    //calculer le total instantané
    this.qteNew=qte;
    if(this.qteNew!=null) {
      this.total2=0;
      for (let i in this.array) {
        this.total2 = this.total2 + this.array[i].produit[1] * this.array[i].qte;
      }
    }
  }


  onchange(d){
    this.produit=d;
    console.log(JSON.stringify(this.produit));
  }
  equals(o1 , o2) {

    return o1.idProduit === o2.idProduit;
  }

}
