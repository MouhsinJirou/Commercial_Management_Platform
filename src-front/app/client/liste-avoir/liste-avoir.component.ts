import { Component, OnInit } from '@angular/core';
import {ModalService} from "../../../service/model.service";
import {ActivatedRoute} from "@angular/router";
import {ClientService} from "../../../service/client.service";
import {HttpClient} from "@angular/common/http";
import {AvoirClientService} from "../../../service/avoirclient.service";
import {AvoirClient} from "../../../model/model.avoirclient";

@Component({
  selector: 'app-liste-avoir',
  templateUrl: './liste-avoir.component.html',
  styleUrls: ['./liste-avoir.component.scss']
})
export class ListeAvoirComponent implements OnInit {

  idClient:number;
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
  client:any;

  constructor(public modalservice:ModalService,public activatedroute:ActivatedRoute,public http:HttpClient,public clientService:ClientService,public avoirClientService:AvoirClientService) {
    this.idClient = activatedroute.snapshot.params['id'];
  }

  ngOnInit(): void {
    this.doSearch();
    this.getClient();
  }
  onDetailAvoir(content,c:AvoirClient){
    this.avoirClientService.getDetailAvoirClient(c.idAvoirClient).subscribe(data=>{
      this.pageDetailAvoir=data;
      console.log(data);
    },error => {
      console.log(error);
    });
    this.modalservice.open(content);
  }
  getClient(){
    this.clientService.getClient(this.idClient).subscribe(data=>{
      this.client=data;
    },error => {
      console.log(error);
    })
  }

  doSearch(){
    this.clientService.avoirClientId(this.idClient,this.currentPage,this.size).subscribe(
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
      this.clientService.avoirClientDate(this.idClient, this.d1, this.d2, this.currentPageDate, this.size).subscribe(
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
