import { Component, OnInit } from '@angular/core';
import {ModalService} from "../../service/model.service";
import {Row} from "../../model/row";
import {BonAchatService} from "../../service/bonachat.service";
import {HttpClient} from "@angular/common/http";
import {BonAchat} from "../../model/model.bonachat";
import {ActivatedRoute, Router} from "@angular/router";
import {PaiementFournisseurService} from "../../service/paiementfournisseur.service";

@Component({
  selector: 'app-bon-achat',
  templateUrl: './bon-achat.component.html',
  styleUrls: ['./bon-achat.component.scss']
})
export class BonAchatComponent implements OnInit {
  row:Row=new Row();
  array=[];
  pageBons:any;
  pageBonsDetail:any;
  size:number=5;
  currentPage:number=0;
  pages:Array<number>;
  totalpage:number;
  id:number=null;
  idBon:number;
  statut:boolean=false;
  bonAchat:any;
  listPayementsBon:any;
  totalPaiementsBon:any;
  credit:any;
  verserStatut:boolean;
  idBonAchat:number;

  constructor(public modalservice:ModalService,public bonAchatService:BonAchatService,public http:HttpClient,public paiementFournisseurService:PaiementFournisseurService,public router:Router) {
  }

  ngOnInit(): void {
    this.row=new Row();
    this.array.push(this.row);
    this.doSearch();
    this.id=null;
    this.statut=false;
  }

  //chercher le bon par id
  chercher(){
    if(this.id!=null){
      this.statut=true;
      this.bonAchatService.getBonAchat(this.id).subscribe(data=>{
        this.bonAchat=data;
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
    this.bonAchatService.getBonsAchat(this.currentPage, this.size)
      .subscribe(data=>{
        this.pageBons=data;
        this.totalpage=this.pageBons.totalPages;
        this.pages=new Array<number>(this.pageBons.totalPages);
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


  addRow(){
    this.row=new Row();
    this.array.push(this.row);
  }

  removeRow(index){
    this.array.splice(index);
  }

  onDeleteBon(c:BonAchat){
    let confirm=window.confirm('Voulez-vous vraiment supprimer ce bon ?');
    if(confirm==true){
      this.bonAchatService.deleteBonAchat(c.idBonAchat)
        .subscribe(data=>{
          this.pageBons.content.splice(
            this.pageBons.content.indexOf(c),1 );
          alert('Bon Supprimer');

        },error => {
          console.log(error);
          alert('Erreur Suppression');

        })

    }
  }



  onDetailBon(content,id){
    this.idBon=id; //on recupere l'id du bon selectionnée afin d'afficher ses produits
    this.doSearchDetail();
    this.modalservice.open(content);
  }

  doSearchDetail(){
    this.bonAchatService.getProduitsBonAchat(this.idBon).subscribe(
      data=>{
        this.pageBonsDetail=data;
        console.log(data);

      },error => {
        console.log(error);
      }
    )
  }



  onPayementHistory(content,c:BonAchat){
    this.modalservice.open(content);
    // la liste des payements d'un bon d'achat
    this.paiementFournisseurService.chercherPaiementParBon(c.idBonAchat).subscribe(data=>{
      this.listPayementsBon=data;

    },error => {
      console.log(error);
    });
    this.paiementFournisseurService.totalPaiementsBon(c.idBonAchat).subscribe(data=>{
      this.totalPaiementsBon=data;
      this.credit=c.montantTotal-this.totalPaiementsBon;
      this.idBonAchat=c.idBonAchat;
      if(this.credit==0){
        this.verserStatut=false;
      }
      else{
        this.verserStatut=true;
      }
    },error => {
      console.log(error);
    })  }

  onVerser(){
    this.router.navigate(['verser-fournisseur',this.idBonAchat]); //passer l'id dans le lien
  }

  onSaveModif(dataForm){}
}
