import { Component, OnInit } from '@angular/core';
import {ModalService} from "../../../service/model.service";
import {ActivatedRoute, Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {FournisseurService} from "../../../service/fournisseur.service";
import {BonAchatService} from "../../../service/bonachat.service";
import {PaiementFournisseurService} from "../../../service/paiementfournisseur.service";
import {BonAchat} from "../../../model/model.bonachat";

@Component({
  selector: 'app-listbon-achats',
  templateUrl: './listbon-achats.component.html',
  styleUrls: ['./listbon-achats.component.scss']
})
export class ListbonAchatsComponent implements OnInit {

  idFournisseur:number;
  bonsAchat:any;
  bonsAchatDate:any
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
  pageBonsDetail:any;
  idBon:number;
  fournisseur:any;
  totalBonAchatsFournisseur:any;
  totalPayementsFournisseur:any;
  listPayementsBon:any;
  totalPaiementsBon:any;
  credit:any;
  verserStatut:boolean;
  idBonAchat:number;
  constructor(public modalservice:ModalService,public activatedroute:ActivatedRoute,public http:HttpClient,public fournisseurService:FournisseurService,public bonAchatService:BonAchatService,public paiementFournisseurService:PaiementFournisseurService,public router:Router) {
    this.idFournisseur = activatedroute.snapshot.params['id'];
  }

  ngOnInit(): void {
    this.doSearch();
    this.getFournisseurInformation();
  }

  doSearch(){
    this.fournisseurService.bonParFournisseur(this.idFournisseur,this.currentPage,this.size).subscribe(
      data=>{
        this.bonsAchat=data;
        this.totalpage=this.bonsAchat.totalPages;
        this.pages=new Array<number>(this.bonsAchat.totalPages)
        console.log(data);
      },error => console.log(error)
    )
  }
  getFournisseurInformation(){
    this.fournisseurService.getFournisseur(this.idFournisseur).subscribe(data=>{
      this.fournisseur=data;
    },error => {
      console.log(error)
    })
    this.fournisseurService.totalBonAchatsFournisseur(this.idFournisseur).subscribe(data=>{
      this.totalBonAchatsFournisseur=data;
    },error => {
      console.log(error);
    })
    this.fournisseurService.totalPayementsFournisseur(this.idFournisseur).subscribe(data=>{
      this.totalPayementsFournisseur=data;
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

  onDetailBonAchat(content,id){
    this.idBon=id; //on recupere l'id du bon selectionnée afin d'afficher ses produits
    this.doSearchDetail();
    this.modalservice.open(content);
  }
  doSearchDetail(){
    this.bonAchatService.getProduitsBonAchat(this.idBon).subscribe(
      data=>{
        this.pageBonsDetail=data;
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
    })

  }
  onVerser(){
    this.router.navigate(['verser-fournisseur',this.idBonAchat]); //passer l'id dans le lien

  }


  //chercher selon les deux dates entrées
  chercherDate() {


    if (this.d1 != null && this.d2!=null) {
      this.statut=true;
      this.fournisseurService.bonFournisseurDate(this.idFournisseur, this.d1, this.d2, this.currentPageDate, this.size).subscribe(
        data => {
          this.bonsAchatDate = data;
          this.totalpageDate = this.bonsAchatDate.totalPages;
          this.pagesDate = new Array<number>(this.bonsAchatDate.totalPages)
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
