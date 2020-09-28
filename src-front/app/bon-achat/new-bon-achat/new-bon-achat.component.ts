import { Component, OnInit } from '@angular/core';
import {Row} from "../../../model/row";
import {Fournisseur} from "../../../model/model.fournisseur";
import {FournisseurService} from "../../../service/fournisseur.service";
import {ProduitService} from "../../../service/produit.service";
import {BonAchatService} from "../../../service/bonachat.service";
import {Router} from "@angular/router";
import {PaiementFournisseurService} from "../../../service/paiementfournisseur.service";
import {HttpClient} from "@angular/common/http";
import {PaiementFournisseur} from "../../../model/model.paiementFournisseur";
import {BonAchat} from "../../../model/model.bonachat";
import {Produit} from "../../../model/model.produit";
import {PaiementClientService} from "../../../service/paiementclient.service";
import {AuthService} from "../../../service/AuthService.service";

@Component({
  selector: 'app-new-bon-achat',
  templateUrl: './new-bon-achat.component.html',
  styleUrls: ['./new-bon-achat.component.scss']
})
export class NewBonAchatComponent implements OnInit {
  listFournisseurs:any;
  listProduits:any;
  fournisseur:Fournisseur;
  produit:Produit;
  bonAchatss:BonAchat=new BonAchat();
  bons:Object=new BonAchat(); //pour le  data recupere du POST
  date:Date;
  modePaiement:String;
  row:Row=new Row();
  array=[];
  isSelected:boolean=false;
  select:string;
  total:number=0;
  montantVerse:number=0;
  paiementFournisseur:PaiementFournisseur=new PaiementFournisseur();

  prix:number;
  qte:number;
  total2:number=0;
  utilisateur:any=null;

  constructor(public fournisseurService:FournisseurService,public produitService:ProduitService,public http: HttpClient
    ,public bonService:BonAchatService,public router:Router,public paiementFournisseurService:PaiementFournisseurService,public authService:AuthService) {
  }



  ngOnInit(): void {
    this.searchListFournisseur();
    this.searchListProduit();
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
      this.total2 = this.total2 + this.array[i].prixAchat * this.array[i].qte_achat;
    }
  }

  onSaveAchat(dataForm){
  }
  onSelect(event){
    let selected = event.target.value;
    this.modePaiement=selected;
    if (selected == "Crédit") {
      this.isSelected = true;
      this.select="Non payé";
    } else {
      this.isSelected = false;
      this.select="Payé";
    }

  }

  searchListProduit(){
    this.produitService.listProduits().subscribe(data=>{
      this.listProduits=data;
      //console.log("list depots",this.listProduits);

    },error => {
      console.log(error);
    })
  }
  searchListFournisseur(){
    this.fournisseurService.listFournisseurs().subscribe(data=>{
      this.listFournisseurs=data;


    },error => {
      console.log(error);
    })
  }
  onSave(){

    //calculer les montant total
    for (let i in this.array){
      this.total=this.total+this.array[i].prixAchat*this.array[i].qte_achat;
    }
    console.log(this.total);

    this.bonAchatss.utilisateur=this.utilisateur;
    this.bonAchatss.date=this.date;
    this.bonAchatss.achats=this.array;
    this.bonAchatss.fournisseur=this.fournisseur;
    this.bonAchatss.modePaiement=this.modePaiement;
    this.bonAchatss.statu=this.select;
    this.bonAchatss.montantTotal=this.total;
    console.log(JSON.stringify(this.bonAchatss));

    this.bonService.saveBonAchat(this.bonAchatss)
      .subscribe(data=>{
          this.bons=data;
          console.log(this.bons);

          //remplir le versement (paiement)
          this.paiementFournisseur.date=this.date;
          if (this.select=="Non payé")  {this.paiementFournisseur.montantVerse=this.montantVerse;}
          else if (this.select=="Payé") {this.paiementFournisseur.montantVerse=this.total;}
          this.paiementFournisseur.bonAchat=this.bons;

          //Verser le payement
          this.paiementFournisseurService.savePaiementFournisseur(this.paiementFournisseur).subscribe(data=>{
              console.log(data);
              alert("L'achat a été bien ajouté !");
              window.location.reload();
            },
            err => {
              console.log(err);

              alert("Une erreur est produite lors du paiement");
            } )


        },
        err => {
          console.log(err);
          console.log(JSON.parse(err._body).message);
          alert("Une erreur est produite lors de l'jout de l'achat !");
        });




  }

  onPrix(prix){
    //calculer le total instantané
    this.prix=prix;
    if(this.prix!=null && this.qte!=null) {
      this.total2=0;
      for (let i in this.array) {
        this.total2 = this.total2 + this.array[i].prixAchat * this.array[i].qte_achat;
      }
    }
  }
  onQte(qte) {
    //calculer le total instantané
    this.qte = qte;
    if (this.prix != null && this.qte != null) {
      this.total2 = 0;
      for (let i in this.array) {
        this.total2 = this.total2 + this.array[i].prixAchat * this.array[i].qte_achat;
      }
    }
  }

  equals(o1 , o2) {

    return o1.idProduit === o2.idProduit;
 }
  equalsFournisseur(o1,o2){
    return o1.idFournisseur === o2.idFournisseur;
  }
}
