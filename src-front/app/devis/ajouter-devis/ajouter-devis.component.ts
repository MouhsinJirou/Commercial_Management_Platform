import { Component, OnInit } from '@angular/core';
import {Row} from "../../../model/row";
import {ClientService} from "../../../service/client.service";
import {ProduitService} from "../../../service/produit.service";
import {DevisService} from "../../../service/devis.service";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {Devis} from "../../../model/model.devis";
import {Client} from "../../../model/model.client";

@Component({
  selector: 'app-ajouter-devis',
  templateUrl: './ajouter-devis.component.html',
  styleUrls: ['./ajouter-devis.component.scss']
})
export class AjouterDevisComponent implements OnInit {
  row:Row=new Row();
  array=[];
  listClients:any;
  listProduits:any;
  date:Date;
  prix:number;
  qte:number;
  total2:number=0;
  total:number=0;
  devis:Devis=new Devis();
  client:Client;
  statutDrop:boolean=true;
  constructor(public clientService:ClientService,public produitService:ProduitService,public devisService:DevisService,public http: HttpClient,public router:Router) { }

  ngOnInit(): void {
    this.row=new Row();
    this.array.push(this.row);
    this.searchListClient();
    this.searchListProduit();
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
      this.total2 = this.total2 + this.array[i].prix * this.array[i].qte;
    }
  }

  searchListProduit(){
    this.produitService.listProduits().subscribe(data=>{
      this.listProduits=data;


    },error => {
      console.log(error);
    })
  }
  searchListClient(){
    this.clientService.listClient().subscribe(data=>{
      this.listClients=data;


    },error => {
      console.log(error);
    })
  }

  onSave() {

    //calculer les montant total
    for (let i in this.array) {
      this.total = this.total + this.array[i].prix * this.array[i].qte;
    }
    console.log(this.total);

    this.devis.dateDevis = this.date;
    this.devis.detailsDevis=this.array;
    this.devis.client=this.client;
    this.devis.montantDevis=this.total;

    this.devisService.saveDevis(this.devis)
      .subscribe(data=>{
          // this.statutDrop=false;
          console.log(data);
          alert("Le devis est bien ajouté !");
          window.location.reload();
        },
        err => {
          console.log(err);

          alert("Une erreur est produite lors de l'ajout du devis !");
        } )
  }


  onPrix(prix){
    //calculer le total instantané
    this.prix=prix;
    if(this.prix!=null && this.qte!=null) {
      this.total2=0;
      for (let i in this.array) {
        this.total2 = this.total2 + this.array[i].prix * this.array[i].qte;
      }
    }
  }
  onQte(qte) {
    //calculer le total instantané
    this.qte = qte;
    if (this.prix != null && this.qte != null) {
      this.total2 = 0;
      for (let i in this.array) {
        this.total2 = this.total2 + this.array[i].prix * this.array[i].qte;
      }
    }
  }


  equals(o1 , o2) {

    return o1.idProduit === o2.idProduit;

  }
  equalsClient(o1,o2){
    return o1.idClient === o2.idClient;
  }


}
