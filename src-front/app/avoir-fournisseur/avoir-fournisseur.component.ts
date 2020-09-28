import { Component, OnInit } from '@angular/core';
import {ModalService} from "../../service/model.service";
import {DepotService} from "../../service/depot.service";
import {HttpClient} from "@angular/common/http";
import {AvoirFournisseurService} from "../../service/avoirfournisseur.service";
import {AvoirFournisseur} from "../../model/model.avoirfournisseur";

@Component({
  selector: 'app-avoir-fournisseur',
  templateUrl: './avoir-fournisseur.component.html',
  styleUrls: ['./avoir-fournisseur.component.scss']
})
export class AvoirFournisseurComponent implements OnInit {
  pageAvoirs:any;
  nom:string="";
  currentPage:number=0;
  size:number=5;
  pages:Array<number>;
  totalpage:number;

  pageDetailAvoir:any;

  constructor(public modalservice:ModalService,public http:HttpClient,public avoirFournisseurService:AvoirFournisseurService) { }

  ngOnInit(): void {
    this.doSearch();
  }
  chercher(){
    this.doSearch();
  }
  doSearch(){
    this.avoirFournisseurService.getAvoirsFournisseur(this.nom,this.size,this.currentPage).subscribe(data=>{
      this.pageAvoirs=data;
      this.totalpage=this.pageAvoirs.totalPages;
      this.pages=new Array<number>(this.pageAvoirs.totalPages)
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


  onDetailAvoir(content,c:AvoirFournisseur){
    this.avoirFournisseurService.getDetailAvoirFournisseur(c.idAvoirFr).subscribe(data=>{
      this.pageDetailAvoir=data;
      console.log(data);
    },error => {
      console.log(error);
    });
    this.modalservice.open(content);
  }
  onDeleteAvoir(){}
}
