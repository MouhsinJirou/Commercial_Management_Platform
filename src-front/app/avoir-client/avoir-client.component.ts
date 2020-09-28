import { Component, OnInit } from '@angular/core';
import {ModalService} from "../../service/model.service";
import {HttpClient} from "@angular/common/http";
import {AvoirClientService} from "../../service/avoirclient.service";
import {AvoirClient} from "../../model/model.avoirclient";

@Component({
  selector: 'app-avoir-client',
  templateUrl: './avoir-client.component.html',
  styleUrls: ['./avoir-client.component.scss']
})
export class AvoirClientComponent implements OnInit {
  pageAvoirs:any;
  nom:string="";
  currentPage:number=0;
  size:number=5;
  pages:Array<number>;
  totalpage:number;
  pageDetailAvoir:any;

  constructor(public modalservice:ModalService,public http:HttpClient,public avoirClientService:AvoirClientService) { }

  ngOnInit(): void {
    this.doSearch();
  }
  chercher(){
    this.doSearch();
  }
  doSearch(){
    this.avoirClientService.getAvoirsClient(this.nom,this.size,this.currentPage).subscribe(data=>{
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

  onDetailAvoir(content,c:AvoirClient){
    this.avoirClientService.getDetailAvoirClient(c.idAvoirClient).subscribe(data=>{
      this.pageDetailAvoir=data;
      console.log(data);
    },error => {
      console.log(error);
    });
    this.modalservice.open(content);
  }
  onDeleteAvoir(){}



}
