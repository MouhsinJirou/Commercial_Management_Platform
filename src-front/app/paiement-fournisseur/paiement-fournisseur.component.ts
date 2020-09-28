import { Component, OnInit } from '@angular/core';
import {PaiementFournisseurService} from "../../service/paiementfournisseur.service";
import {HttpClient} from "@angular/common/http";
import {PaiementClient} from "../../model/model.paiementclient";
import {PaiementFournisseur} from "../../model/model.paiementFournisseur";

@Component({
  selector: 'app-paiement-fournisseur',
  templateUrl: './paiement-fournisseur.component.html',
  styleUrls: ['./paiement-fournisseur.component.scss']
})
export class PaiementFournisseurComponent implements OnInit {
  pagePaiementFournisseurs:any;
  nom:string="";
  size:number=5;
  currentPage:number=0;
  pages:Array<number>;
  totalpage:number;

  constructor(public paiementFournisseurService:PaiementFournisseurService,public http:HttpClient) { }

  ngOnInit(): void {
    this.doSearch();
  }

  chercher(){
    this.doSearch();
  }

  doSearch(){
    this.paiementFournisseurService.getPaiementFournisseurs(this.nom,this.currentPage,this.size).subscribe(data=>{
      this.pagePaiementFournisseurs=data;
      this.totalpage=this.pagePaiementFournisseurs.totalPages;
      this.pages=new Array<number>(this.pagePaiementFournisseurs.totalPages)
    },error => {
      console.log(error);
    })
  }

  gotoPage(i:number){
    if(i>=this.totalpage){
      this.currentPage=this.totalpage-1;
      this.doSearch();
    }
    else if(i<=0){
      this.currentPage=0;
      this.doSearch();
    }
    else{
      this.currentPage=i;
      this.doSearch();
    }}

  onDeletePaiementFournisseur(c:PaiementFournisseur){
    let confirm=window.confirm('Voulez-vous vraiment supprimer ce paiement ?');
    if(confirm==true){
      this.paiementFournisseurService.deletePaiementFournisseur(c.idPaiementFournisseur)
        .subscribe(data=>{
          this.pagePaiementFournisseurs.content.splice(
            this.pagePaiementFournisseurs.content.indexOf(c),1 );
          alert('paiement Supprimer');

        },error => {
          console.log(error);
          alert('Erreur Suppression');

        })
    }
  }

}

