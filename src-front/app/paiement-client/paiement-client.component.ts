import { Component, OnInit } from '@angular/core';
import {ModalService} from "../../service/model.service";
import {PaiementClientService} from "../../service/paiementclient.service";
import {HttpClient} from "@angular/common/http";
import {PaiementClient} from "../../model/model.paiementclient";

@Component({
  selector: 'app-paiement-client',
  templateUrl: './paiement-client.component.html',
  styleUrls: ['./paiement-client.component.scss']
})
export class PaiementClientComponent implements OnInit {
  pagePaiementClients:any;
  nom:string="";
  size:number=5;
  currentPage:number=0;
  pages:Array<number>;
  totalpage:number;


  constructor(public paiementClientService:PaiementClientService,public http:HttpClient) { }

  ngOnInit(): void {
    this.doSearch();
  }
  chercher(){
    this.doSearch();
  }

  doSearch(){
    this.paiementClientService.getPaiementClients(this.nom,this.currentPage,this.size).subscribe(data=>{
      this.pagePaiementClients=data;
      this.totalpage=this.pagePaiementClients.totalPages;
      this.pages=new Array<number>(this.pagePaiementClients.totalPages)
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

  onDeletePaiementClient(c:PaiementClient){
    let confirm=window.confirm('Voulez-vous vraiment supprimer ce paiement ?');
    if(confirm==true){
      this.paiementClientService.deletePaiementClient(c.idPaiementClient)
        .subscribe(data=>{
          this.pagePaiementClients.content.splice(
            this.pagePaiementClients.content.indexOf(c),1 );
          alert('paiement Supprimer');

        },error => {
          console.log(error);
          alert('Erreur Suppression');

        })
    }
  }

}
