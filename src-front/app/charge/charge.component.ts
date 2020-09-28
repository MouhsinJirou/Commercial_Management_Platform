import { Component, OnInit } from '@angular/core';
import {ModalService} from "../../service/model.service";
import {Charge} from "../../model/model.charge";
import {ChargeService} from "../../service/charge.service";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-charge',
  templateUrl: './charge.component.html',
  styleUrls: ['./charge.component.scss']
})
export class ChargeComponent implements OnInit {

  charges:any;
  charge:Charge=new Charge();
  libelle:string="";
  currentPage:number=0;
  size:number=5;
  pages:Array<number>;
  statut:boolean=false;
  totalpage:number;
  confirmation:boolean;
  constructor(public modalservice:ModalService,public http:HttpClient,public chargeService:ChargeService) { }

  ngOnInit(): void {
    this.doSearch()
  }
  doSearch(){
    this.chargeService.getCharges(this.libelle,this.currentPage,this.size).subscribe(
      data=>{
        this.charges=data;

        this.totalpage=this.charges.totalPages;
        this.pages=new Array<number>(this.charges.totalPages)


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
  chercherCharge(){
    this.doSearch()
  }
  onDeleteCharge(c:Charge){
    let confirm=window.confirm('Voulez-vous vraiment supprimer ?');
    if(confirm==true){
      this.chargeService.deleteCharge(c.idCharge)
        .subscribe(data=>{
          this.charges.content.splice(
            this.charges.content.indexOf(c),1 );
          alert('La charge est supprimé !');

        },error => {
          console.log(error);
          alert('Erreur de suppression');

        })

    }

  }
  onEditCharge(content,c:Charge){
    this.chargeService.getCharge(c.idCharge).subscribe(data=>{
        // @ts-ignore
        this.charge=data;

      },error => {
        console.log(error);
      }
    )
    this.modalservice.open(content);
  }
  updateCharge(dataForm){
    this.chargeService.updateCharge(this.charge).subscribe(data=>{

      this.confirmation=true;
      this.doSearch();
    },error => {
      console.log(error);
      this.confirmation = false;
    })

  }
}
