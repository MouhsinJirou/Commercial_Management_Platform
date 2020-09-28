import { Component, OnInit } from '@angular/core';
import {ModalService} from "../../service/model.service";
import {Depot} from "../../model/model.depot";
import {HttpClient} from "@angular/common/http";
import {DepotService} from "../../service/depot.service";

@Component({
  selector: 'app-depot',
  templateUrl: './depot.component.html',
  styleUrls: ['./depot.component.scss']
})
export class DepotComponent implements OnInit {
  idDepot:number;
  pageDepots:any;
  pageDepotsDetail:any;
  affichDetail:any;
  listProduits:any;
  emp:string="";
  currentPage:number=0;
  currentPageDetail:number=0;
  size:number=50;
  sizeDetail:number=5;
  pages:Array<number>;
  pagesDetail:Array<number>;
  totalpage:number;
  totalpageDetail:number
  statut:boolean=false;
  designation:string="";

  confirmation:boolean;
  depot:Depot=new Depot();
  constructor(public modalservice:ModalService,public depotService:DepotService,public http:HttpClient) { }


  ngOnInit(): void {
    //afficher tous les depots
    this.doSearch();

  }
  chercherDepot(){
    this.doSearch();
  }

  onEditDepot(content,id){

    this.idDepot=id;

    // Afficher les infos du depot une fois qu'on click sur le popUP afin de les modifier
    this.depotService.getDepot(id)
      .subscribe(data=>{
        // @ts-ignore
        this.depot=data;
      })

    this.modalservice.open(content);
  }
  onDetailDepot(content,id){
    this.idDepot=id;
    // this.depotService.getProduitsDepot(id,this.currentPageDetail,this.size)
    //   .subscribe(data=>{
    //     // @ts-ignore
    //     this.pageDepotsDetail=data;
    //
    //     this.totalpageDetail=this.pageDepotsDetail.totalPages;
    //     this.pages=new Array<number>(this.pageDepotsDetail.totalPages)
    //     console.log("##############################################");
    //     console.log(this.pageDepotsDetail);
    //   },error =>console.log(error) )
    this.doSearchDetail()

    this.modalservice.open(content);
  }
  onDeleteDepot(c:Depot) {
    let confirm = window.confirm('Voulez-vous vraiment supprimer ce dépot ?');
    if (confirm == true) {
      this.depotService.deleteDepot(c.idDepot)
        .subscribe(data => {
          this.pageDepots.content.splice(
            this.pageDepots.content.indexOf(c), 1);
        }, error => {
          console.log(error);
          alert("Il y a une erreur de la suppression !");
        })

    }
  }
  updateDepot(dataForm){

    this.depotService.updateDepot(this.depot)
      .subscribe(data=>{
        this.confirmation=true;
        this.doSearch();

      }, err=>{
        this.confirmation = false;
        console.log(err);
      })


  }

  chercherProduitDepot(){

    this.doSearchDetail()
  }
  doSearchDetail(){
    this.depotService.chercherProduitsDepot(this.idDepot,this.designation,this.currentPageDetail,this.sizeDetail)
      .subscribe(data=>{
          this.pageDepotsDetail=data;
          this.totalpageDetail=this.pageDepotsDetail.totalPages;
          this.pagesDetail=new Array<number>(this.pageDepotsDetail.totalPages)
        },error => {
          console.log(error);
        }
      )
  }
  gotoPage(i:number){
    if(i>=this.totalpageDetail){
      this.currentPageDetail=this.totalpageDetail-1;
      this.doSearchDetail();
    }
    else if(i<=0){
      this.currentPageDetail=0;
      this.doSearchDetail();
    }
    else{
      this.currentPageDetail=i;
      this.doSearchDetail();
    }}

  doSearch(){
    this.depotService.getDepots(this.emp,this.currentPage,this.size)
      .subscribe(data=>{
          this.pageDepots=data;
          this.pages=new Array<number>(this.pageDepots.totalPages)
        },error => {
          console.log(error);
        }
      )
  }


}
