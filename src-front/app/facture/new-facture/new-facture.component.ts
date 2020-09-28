import { Component, OnInit } from '@angular/core';
import {Row} from "../../../model/row";
import {ClientService} from "../../../service/client.service";
import {ProduitService} from "../../../service/produit.service";
import {HttpClient} from "@angular/common/http";
import {FactureService} from "../../../service/facture.service";
import {PaiementClientService} from "../../../service/paiementclient.service";
import {PaiementClient} from "../../../model/model.paiementclient";
import {Facture} from "../../../model/model.facture";
import {Produit} from "../../../model/model.produit";
import {Client} from "../../../model/model.client";
import {Router} from "@angular/router";
import {AuthService} from "../../../service/AuthService.service";

@Component({
  selector: 'app-new-facture',
  templateUrl: './new-facture.component.html',
  styleUrls: ['./new-facture.component.scss']
})
export class NewFactureComponent implements OnInit {
  row:Row=new Row();
  array=[];
  listClients:any;
  listProduits:any;
  listProduitsFiltres:any;
  client:Client;
  produit:Produit=new Produit();
  isSelected:boolean=false;
  select:string;

  facturesss:Facture=new Facture();
  factures:Object=new Facture(); //pour le  data :  recupere du POST
  dateFacture:Date;
  modePaiement:String;
  total:number=0;
  montantVerse:number=0;
  paiementClient:PaiementClient=new PaiementClient();
  etat:boolean=true; // pour verifier le stock
  // prixMin:any;
  prix:number;
  qte:number;
  total2:number=0;
  utilisateur:any=null;

  constructor(public clientService:ClientService,public produitService:ProduitService,public http: HttpClient
    ,public factureService:FactureService,public router:Router,public paiementClientService:PaiementClientService,public authService:AuthService) {

  }

  ngOnInit(): void {
    this.searchListClient();
    this.searchListProduit();
    this.row=new Row();
    this.array.push(this.row);
    this.utilisateur=this.authService.user.getValue().utilisateur;
    console.log(this.utilisateur);
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

  searchListClient(){
    this.clientService.listClient().subscribe(data=>{
      this.listClients=data;
    },error => {
      console.log(error);
    })
  }
  searchListProduit(){
    this.produitService.listProduits().subscribe(data=>{
      this.listProduits=data;




    },error => {
      console.log(error);
    })
  }


  onSave(){

    //calculer les montant total
    for (let i in this.array){
      this.total=this.total+this.array[i].prix*this.array[i].qte;
    }

    //verifier le stock

    this.etat=true;

    for (let i in this.array) {
      if (this.array[i].produit.nivStock < this.array[i].qte) {

        this.etat = false;
        this.total=0;
        alert("Vous avez saisi une quantité supérieur au niveau de stock pour un produit !");
        break
      }

    }




    if(this.etat==true) {
      this.facturesss.utilisateur=this.utilisateur;
      this.facturesss.dateFacture = this.dateFacture;
      this.facturesss.detailFactures = this.array;
      this.facturesss.client = this.client;
      this.facturesss.modePaiement = this.modePaiement;
      this.facturesss.statu = this.select;
      this.facturesss.montantFacture = this.total;
      //console.log(JSON.stringify(this.bonAchatss));

      this.factureService.saveFacture(this.facturesss)

        .subscribe(data => {
            this.factures = data;
            console.log(this.factures);

            //remplir le versement (paiement)
            this.paiementClient.date = this.dateFacture;
            if (this.select == "Non payé") {
              this.paiementClient.montantVerse = this.montantVerse;
            } else if (this.select == "Payé") {
              this.paiementClient.montantVerse = this.total;
            }
            this.paiementClient.facture = this.factures;

            //Verser le payement
            this.paiementClientService.savePaiementClient(this.paiementClient).subscribe(data => {
                console.log(data);
                alert("La facture a été bien ajouté !");
                window.location.reload();
              },
              err => {
                console.log(err);

                alert("Une erreur est produite lors du paiement");
              })


          },
          err => {
            console.log(err);
            console.log(JSON.parse(err._body).message);
            alert("Une erreur est produite lors de l'jout de la facture !");
          });


    }
  }

  onProduit(event) {
    let selected = event.target.value
    // console.log(JSON.stringify(selected));
    //console.log(JSON.stringify(this.produit));
  }
  onchange(d){
    this.produit=d;
    console.log(JSON.stringify(this.produit));
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
  onQte(qte){
    //calculer le total instantané
    this.qte=qte;
    if(this.prix!=null && this.qte!=null) {
      this.total2=0;
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
