import { Component, OnInit } from '@angular/core';
import {ModalService} from "../../service/model.service";
import {ProduitService} from "../../service/produit.service";
import {HttpClient} from "@angular/common/http";
import {Produit} from "../../model/model.produit";
import {Router} from "@angular/router";

@Component({
  selector: 'app-produit',
  templateUrl: './produit.component.html',
  styleUrls: ['./produit.component.scss']
})
export class ProduitComponent implements OnInit {
  pageProduits:any;
  produit:Produit=new Produit();
  des:string="";
  currentPage:number=0;
  size:number=10;
  pages:Array<number>;
  statut:boolean=false;
  totalpage:number;
  confirmation:boolean;

  idProduit:number;
  array=[];

  constructor(public modalservice:ModalService,public produitService:ProduitService,public http:HttpClient,public router:Router) { }

  ngOnInit() {
    this.doSearch();

  }
  doSearch(){
    this.produitService.getProduits(this.des,this.size,this.currentPage).subscribe(
      data=>{
        this.pageProduits=data;
        this.totalpage=this.pageProduits.totalPages;
        this.pages=new Array<number>(this.pageProduits.totalPages)

        //prixAchatMoyenne
        for(let i in this.pageProduits?.content){
          console.log(this.pageProduits?.content[i].idProduit);
          this.produitService.getPrixAchat(this.pageProduits.content[i].idProduit)
            .subscribe(data=>{
              this.array.push(data);
            })
        }
      },error => {
        console.log(error);
      }
    )

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

  chercher(){
    this.doSearch();
  }

  onDeleteProduit(c:Produit){
    let confirm=window.confirm('Voulez-vous vraiment supprimer ce produit ?');
    if(confirm==true){
      this.produitService.deleteProduit(c.idProduit)
        .subscribe(data=>{
          this.pageProduits.content.splice(
            this.pageProduits.content.indexOf(c),1 );
          alert('produit Supprimer');

        },error => {
          console.log(error);
          alert('Erreur Suppression');

        })

    }
  }

  onEditProduit(content,c:Produit) {
    this.produitService.getProduit(c.idProduit).subscribe(data=>{
        // @ts-ignore
        this.produit=data;
      },error => {
        console.log(error);
      }
    )
    this.modalservice.open(content);
  }

  updateProduit(dataForm){
    this.produitService.updateProduit(this.produit).subscribe(data=>{
      console.log(data);
      this.confirmation=true;
      this.doSearch();
    },error => {
      console.log(error);
      this.confirmation = false;
    })
  }

  onDetailProduit(content){
    this.modalservice.open(content);

  }

  onIdProduit(id){
    //for(let i in )
    console.log(id);
  }


}
