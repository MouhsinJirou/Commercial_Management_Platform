import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {ModalService} from "../../service/model.service";
import {Client} from "../../model/model.client";
import {ClientService} from "../../service/client.service";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-client',
  templateUrl: './client.component.html',
  styleUrls: ['./client.component.scss']
})
export class ClientComponent implements OnInit {
  pageClients:any;
    client:Client=new Client();
  nom:string="";
  currentPage:number=0;
  size:number=5;
  pages:Array<number>;
  statut:boolean=false;
  totalpage:number;
  confirmation:boolean;

  constructor(public modalservice:ModalService,public router:Router,public clientService:ClientService,public http:HttpClient) { }

  ngOnInit(): void {
    this.doSearch();
  }

  doSearch(){
    this.clientService.getClients(this.nom,this.currentPage,this.size).subscribe(
      data=>{
        this.pageClients=data;
        this.totalpage=this.pageClients.totalPages;
        this.pages=new Array<number>(this.pageClients.totalPages)
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

  onDeleteClient(c:Client){
    let confirm=window.confirm('Voulez-vous vraiment supprimer ce client ?');
    if(confirm==true){
      this.clientService.deleteClient(c.idClient)
        .subscribe(data=>{
          this.pageClients.content.splice(
            this.pageClients.content.indexOf(c),1 );
          alert('Le client est supprimé !');

        },error => {
          console.log(error);
          alert('Erreur de suppression');

        })

    }
  }

  onUpdate(content,c:Client){
    this.clientService.getClient(c.idClient).subscribe(data=>{
        // @ts-ignore
        this.client=data;
      },error => {
        console.log(error);
      }
    )
    this.modalservice.open(content);
  }

  updateClient(dataForm){
    this.clientService.updateClient(this.client).subscribe(data=>{
      console.log(data);
      this.confirmation=true;
      this.doSearch();
    },error => {
      console.log(error);
      this.confirmation = false;
    })

  }
  onFacture(id){
    this.router.navigate(['liste-facture',id]); //passer l'id dans le lien
  }
  onAvoir(id){
    this.router.navigate(['liste-avoir',id]);
  }
}
