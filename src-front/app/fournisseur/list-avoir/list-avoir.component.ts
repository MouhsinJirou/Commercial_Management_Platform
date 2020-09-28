import { Component, OnInit } from '@angular/core';
import {ModalService} from "../../../service/model.service";
import {ActivatedRoute} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {FournisseurService} from "../../../service/fournisseur.service";
import {AvoirFournisseurService} from "../../../service/avoirfournisseur.service";
import {AvoirFournisseur} from "../../../model/model.avoirfournisseur";

@Component({
  selector: 'app-list-avoir',
  templateUrl: './list-avoir.component.html',
  styleUrls: ['./list-avoir.component.scss']
})
export class ListAvoirComponent implements OnInit {

  idFournisseur:number;
  avoir:any;
  avoirDate:any
  d1:Date;
  d2:Date;
  currentPage:number=0;
  size:number=5;
  pages:Array<number>;
  totalpage:number;
  statut:boolean=false //pour afficher le bloc de la recherche par dates
  currentPageDate:number=0;
  pagesDate:Array<number>;
  totalpageDate:number;
  pageDetailAvoir:any;
  fournisseur:any;

  constructor(public modalservice:ModalService,public activatedroute:ActivatedRoute,public http:HttpClient,public fournisseurService:FournisseurService,public avoirFournisseurService:AvoirFournisseurService) {
    this.idFournisseur = activatedroute.snapshot.params['id'];
  }

  ngOnInit(): void {
    this.doSearch();
    this.getFournisseur();
  }
  onDetailAvoir(content,c:AvoirFournisseur){
    this.avoirFournisseurService.getDetailAvoirFournisseur(c.idAvoirFr).subscribe(data=>{
      this.pageDetailAvoir=data;
      console.log(data);
    },error => {
      console.log(error);
    });
    this.modalservice.open(content);
  }
  getFournisseur(){
    this.fournisseurService.getFournisseur(this.idFournisseur).subscribe(data=>{
      this.fournisseur=data;
    },error => {
      console.log(error);
    })
  }

  doSearch(){
    this.fournisseurService.avoirParFournisseur(this.idFournisseur,this.currentPage,this.size).subscribe(
      data=>{
        this.avoir=data;
        this.totalpage=this.avoir.totalPages;
        this.pages=new Array<number>(this.avoir.totalPages)
        console.log(data);
      },error => console.log(error)
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

  chercherDate() {


    if (this.d1 != null && this.d2!=null) {
      this.statut=true;
      this.fournisseurService.avoirFournisseurDate(this.idFournisseur, this.d1, this.d2, this.currentPageDate, this.size).subscribe(
        data => {
          this.avoirDate = data;
          this.totalpageDate = this.avoirDate.totalPages;
          this.pagesDate = new Array<number>(this.avoirDate.totalPages)
          console.log(data);
        }, error => console.log(error)
      )
    }
    else this.statut=false;
  }
  gotoPageDate(i:number){
    if(i>=this.totalpageDate){
      this.currentPageDate=this.totalpageDate-1;
      this.chercherDate();
    }
    else if(i<=0){
      this.currentPageDate=0;
      this.chercherDate();
    }
    else{
      this.currentPageDate=i;
      this.chercherDate();
    }}
}
