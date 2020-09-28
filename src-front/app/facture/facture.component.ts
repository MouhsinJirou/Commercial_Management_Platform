import { Component, OnInit } from '@angular/core';
import {Row} from "../../model/row";
import {ModalService} from "../../service/model.service";
import {FactureService} from "../../service/facture.service";
import {HttpClient} from "@angular/common/http";
import {Facture} from "../../model/model.facture";
import {PaiementClientService} from "../../service/paiementclient.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-facture',
  templateUrl: './facture.component.html',
  styleUrls: ['./facture.component.scss']
})
export class FactureComponent implements OnInit {
  row:Row=new Row();
  array=[];

  pageFactures:any;
  pageFacturesDetail:any;
  currentPage:number=0;
  size:number=5;
  pages:Array<number>;
  totalpage:number;
  totalpageDetail:number;
  pagesDetail:Array<number>;
  idFacture:number;
  statut:boolean=false;
  facture:any;
  id:number;
  paiements:any;
  credits:any;
  constructor(public modalservice:ModalService,public factureService:FactureService,public paiementClientService:PaiementClientService,public router:Router) { }

  ngOnInit(): void {
    this.row=new Row();
    this.array.push(this.row);
    this.doSearch();
    this.id=null;
    this.statut=false;
  }

  addRow(){
    this.row=new Row();
    this.array.push(this.row);
  }
  removeRow(index){
    this.array.splice(index);
  }

  onSaveModif(dataForm){  // enregistrer les donnees modifiees
  }

  //chercher la facture par id
  chercher(){
    if(this.id!=null){
      this.statut=true;
      this.factureService.getFacture(this.id).subscribe(data=>{
        this.facture=data;
      },error => {
        console.log(error);

      })
    }
    else{
      this.statut=false;
    }
  }

  onDetailFacture(content,id){
    this.idFacture=id; //on recupere l'id de la facture selectionnée afin d'afficher ses produits
    this.doSearchDetail();
    this.modalservice.open(content);
  }

  onEditFacture(content){

    this.modalservice.open(content);

  }
  onPayementHistory(content,id){
    this.idFacture=id;
    this.paiementClientService.getPaiementFacture(id).subscribe(data=>{
      this.paiements=data;
    },error => {
      console.log(error);
    });

    this.paiementClientService.calculerCredit(id).subscribe(data=>{
      this.credits=data;
    },error => {
      console.log(error);
    });

    this.modalservice.open(content);
  }
  onVerser(){
    this.router.navigate(['verser-client',this.idFacture]); //passer l'id dans le lien

  }

  // la liste des bon d'achat
  doSearch() {
    this.factureService.getFactures(this.currentPage, this.size)
      .subscribe(data=>{
        this.pageFactures=data;
        this.totalpage=this.pageFactures.totalPages;
        this.pages=new Array<number>(this.pageFactures.totalPages);
      },error => {
        console.log(error);
      })
  }

  doSearchDetail(){
    this.factureService.getProduitsFacture(this.idFacture).subscribe(
      data=>{
        this.pageFacturesDetail=data;
        console.log(data);
        this.totalpageDetail=this.pageFacturesDetail.totalPages;
        this.pagesDetail=new Array<number>(this.pageFacturesDetail.totalPages)
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

  onDeleteFacture(c:Facture){
    let confirm=window.confirm('Voulez-vous vraiment supprimer cette facture ?');
    if(confirm==true){
      this.factureService.deleteFacture(c.idFacture)
        .subscribe(data=>{
          this.pageFactures.content.splice(
            this.pageFactures.content.indexOf(c),1 );
          alert('Facture Supprimer');

        },error => {
          console.log(error);
          alert('Erreur Suppression');

        })

    }
  }
}
