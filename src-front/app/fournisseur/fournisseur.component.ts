import { Component, OnInit } from '@angular/core';
import {ModalService} from "../../service/model.service";
import {Router} from "@angular/router";
import {Fournisseur} from "../../model/model.fournisseur";
import {FournisseurService} from "../../service/fournisseur.service";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-fournisseur',
  templateUrl: './fournisseur.component.html',
  styleUrls: ['./fournisseur.component.scss']
})
export class FournisseurComponent implements OnInit {
  confirmation:boolean;
  pageFournisseurs:any;
  nom:string="";
  size:number=5;
  currentPage:number=0;
  pages:Array<number>;
  totalpage:number;
  fournisseur:Fournisseur=new Fournisseur();


  constructor(public modalservice:ModalService,public router:Router,public fournisseurService:FournisseurService,public http:HttpClient) { }

  ngOnInit(): void {
    this.doSearch();
  }

  chercher(){
this.doSearch();
  }

  doSearch(){
    this.fournisseurService.getFournisseurs(this.nom,this.size,this.currentPage).subscribe(data=>{
      this.pageFournisseurs=data;
      this.totalpage=this.pageFournisseurs.totalPages;
      this.pages=new Array<number>(this.pageFournisseurs.totalPages)
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

  onDeleteFournisseur(c:Fournisseur){
    let confirm=window.confirm('Voulez-vous vraiment supprimer ce produit ?');
    if(confirm==true){
      this.fournisseurService.deleteFournisseur(c.idFournisseur)
        .subscribe(data=>{
          this.pageFournisseurs.content.splice(
            this.pageFournisseurs.content.indexOf(c),1 );
          alert('fournisseur Supprimer');

        },error => {
          console.log(error);
          alert('Erreur Suppression');

        })

    }
  }


  onEditFournisseur(content,c:Fournisseur){
    this.fournisseurService.getFournisseur(c.idFournisseur).subscribe(data=>{
      // @ts-ignore
      this.fournisseur=data;
    },error => {
      console.log(error);
    })
    this.modalservice.open(content);
  }


   updateFournisseur(dataForm){
this.fournisseurService.updateFournisseur(this.fournisseur).subscribe(data=>{
  console.log(data);
  this.confirmation=true;
  this.doSearch();
},error => {
  console.log(error);
  this.confirmation = false;
})
}

  listBonAchat(id){
    this.router.navigate(['listbonAchatsfournisseur',id]);
  }

  listAvoir(id){
    this.router.navigate(['listavoirfournisseur',id]);

  }
}
