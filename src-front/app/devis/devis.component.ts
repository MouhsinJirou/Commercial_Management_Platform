import { Component, OnInit } from '@angular/core';
import {ModalService} from "../../service/model.service";
import {DevisService} from "../../service/devis.service";
import {HttpClient} from "@angular/common/http";
import {Devis} from "../../model/model.devis";

@Component({
  selector: 'app-devis',
  templateUrl: './devis.component.html',
  styleUrls: ['./devis.component.scss']
})
export class DevisComponent implements OnInit {
  pageDevis:any;
  pageDevisDetail:any;
  size:number=5;
  currentPage:number=0;
  pages:Array<number>;
  totalpage:number;
  id:number=null;
  idDevis:number;
  statut:boolean=false;
  devis:any;
  constructor(public modalservice:ModalService,public devisService:DevisService,public http:HttpClient) { }

  ngOnInit(): void {
    this.doSearch();
    this.id=null;
    this.statut=false;
  }

  //chercher la facture par id
  chercher(){
    if(this.id!=null){
      this.statut=true;
      this.devisService.getDevis(this.id).subscribe(data=>{
        this.devis=data;
      },error => {
        console.log(error);

      })
    }
    else{
      this.statut=false;
    }
  }
  // la liste des bon d'achat
  doSearch() {
    this.devisService.getDeviss(this.currentPage, this.size)
      .subscribe(data=>{
        this.pageDevis=data;
        this.totalpage=this.pageDevis.totalPages;
        this.pages=new Array<number>(this.pageDevis.totalPages);
      },error => {
        console.log(error);
      })
  }
  onDeleteDevis(c:Devis){
    let confirm=window.confirm('Voulez-vous vraiment supprimer ce devis ?');
    if(confirm==true){
      this.devisService.deleteDevis(c.idDevis)
        .subscribe(data=>{
          this.pageDevis.content.splice(
            this.pageDevis.content.indexOf(c),1 );
          alert('Devis Supprimer');

        },error => {
          console.log(error);
          alert('Erreur Suppression');

        })

    }
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

  onDetail(content,id){
    this.idDevis=id; //on recupere l'id de la facture selectionnée afin d'afficher ses produits
    this.doSearchDetail()
    this.modalservice.open(content);
  }
  doSearchDetail(){
    this.devisService.getProduitsDevis(this.idDevis).subscribe(
      data=>{
        this.pageDevisDetail=data;
        console.log(data);

      },error => {
        console.log(error);
      }
    )

  }
}
