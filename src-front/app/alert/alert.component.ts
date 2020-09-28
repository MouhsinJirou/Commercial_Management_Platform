import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ProduitService} from "../../service/produit.service";

@Component({
  selector: 'app-alert',
  templateUrl: './alert.component.html',
  styleUrls: ['./alert.component.scss']
})
export class AlertComponent implements OnInit {
  designation:String="";
  alertP:any;
  currentPage:number=0;
  size:number=5;
  pages:Array<number>;
  totalpage:number;
  constructor(public http:HttpClient,public produitService:ProduitService) { }

  ngOnInit(): void {
    this.doSearch()
  }

  doSearch(){
    this.produitService.alerterProduit(this.designation,this.currentPage,this.size).subscribe(
      data=>{
        this.alertP=data;
        this.totalpage=this.alertP.totalPages;
        this.pages=new Array<number>(this.alertP.totalPages)
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

  chercherProduit(){
    console.log(this.designation)
    this.doSearch()
  }

}
