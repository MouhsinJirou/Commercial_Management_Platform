import { Component, OnInit } from '@angular/core';
import {Row} from "../../../model/row";
import {AvoirFournisseur} from "../../../model/model.avoirfournisseur";
import {AvoirFournisseurService} from "../../../service/avoirfournisseur.service";
import {BonAchatService} from "../../../service/bonachat.service";
import {DatePipe} from "@angular/common";
import {AuthService} from "../../../service/AuthService.service";

@Component({
  selector: 'app-new-avoir-fournisseur',
  templateUrl: './new-avoir-fournisseur.component.html',
  styleUrls: ['./new-avoir-fournisseur.component.scss'],
  providers: [DatePipe]

})
export class NewAvoirFournisseurComponent implements OnInit {

  row:Row=new Row();
  array=[];
  idBon:number;
  detailBon:any;
  produit:any;
  total2:number=0;
  total:number=0;
  prix:number;
  qteNew:number;
  myDate:string;
  todayString : string = new Date().toDateString();
  private etat: boolean=true;
  avoirFournisseur:AvoirFournisseur=new AvoirFournisseur();
  newArray=[];
  utilisateur:any=null;

  constructor(public datePipe:DatePipe,public avoirFournisseurService:AvoirFournisseurService,public bonService:BonAchatService,public authService:AuthService) {
    //recuperer la date d ajourdhui
    this.myDate = this.datePipe.transform(this.todayString, 'yyyy-MM-dd');
    this.utilisateur=this.authService.user.getValue().utilisateur;

  }

  ngOnInit(): void {
    this.row=new Row();
    this.array.push(this.row);
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
    this.bonService.getProduitsBonAchat2(this.idBon).subscribe(data=>{
      this.detailBon=data;
      //console.log(JSON.stringify(this.detailBon));

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
        alert("Vous avez saisi une quantité supérieur à celle du bon d'achat !");
        break
      }
    }
    if(this.etat==true){
      //bon
      this.bonService.getBonAchat(this.idBon).subscribe(data=>{

        this.avoirFournisseur.utilisateur=this.utilisateur;
        this.avoirFournisseur.bonAchat=data;

        //date
        this.avoirFournisseur.dateAvoirFr=this.myDate;


        //montant total:
        this.avoirFournisseur.montantAvoirFr=this.total;

        //DetailAvoir
        for (let i in this.array) {
          this.newArray.push({"qte":this.array[i].qte,"prix":this.array[i].produit[1],"produit":this.array[i].produit[0]})
        }
        this.avoirFournisseur.detailAvoirFournisseur=this.newArray;
        console.log(JSON.stringify(this.avoirFournisseur));
        this.avoirFournisseurService.saveAvoirFournisseur(this.avoirFournisseur).subscribe(data=>{

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
