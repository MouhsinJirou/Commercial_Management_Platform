import { Component, OnInit } from '@angular/core';
import {ModalService} from "../../../service/model.service";
import {ActivatedRoute, Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {ClientService} from "../../../service/client.service";
import {FactureService} from "../../../service/facture.service";
import {PaiementClientService} from "../../../service/paiementclient.service";


@Component({
  selector: 'app-liste-facture',
  templateUrl: './liste-facture.component.html',
  styleUrls: ['./liste-facture.component.scss']
})
export class ListeFactureComponent implements OnInit {

  idClient:number;
  factures:any;
  facturesDate:any
  d1:Date;
  d2:Date;
  currentPage:number=0;
  size:number=5;
  pages:Array<number>;
  totalpage:number;
  statut:boolean=false; //pour afficher le bloc de la recherche par dates
  currentPageDate:number=0;
  pagesDate:Array<number>;
  totalpageDate:number;
  idFacture:number;
  pageFacturesDetail:any;
  client:any;
  totalFacturesClient:any;
  totalPayementsClient:any;

  paiements:any;
  credits:any;
  constructor(public modalservice:ModalService,public activatedroute:ActivatedRoute
    ,public http:HttpClient,public clientService:ClientService
    ,public factureService:FactureService,public paiementClientService:PaiementClientService,public router:Router) {
    this.idClient = activatedroute.snapshot.params['id'];
  }

  ngOnInit(): void {
    this.doSearch();
    this.getClientInformation();
  }

  doSearch(){
    this.clientService.factureClientId(this.idClient,this.currentPage,this.size).subscribe(
      data=>{
        this.factures=data;
        this.totalpage=this.factures.totalPages;
        this.pages=new Array<number>(this.factures.totalPages)
        console.log(data);
      },error => console.log(error)
    )
  }
  getClientInformation(){
    this.clientService.getClient(this.idClient).subscribe(data=>{
      this.client=data;
    },error => {
      console.log(error);
    })
    this.clientService.totalFacturesClient(this.idClient).subscribe(data=>{
      this.totalFacturesClient=data;
    },error => {
      console.log(error);
    })
    this.clientService.totalPayementsClient(this.idClient).subscribe(data=>{
      this.totalPayementsClient=data;
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

  chercherDate(){
    console.log(this.d1 + " " + this.d2);
    if (this.d1 != null && this.d2!=null) {
      this.statut=true;
      this.clientService.factureClientDate(this.idClient, this.d1, this.d2, this.currentPageDate, this.size).subscribe(
        data => {
          this.facturesDate = data;
          this.totalpageDate = this.facturesDate.totalPages;
          this.pagesDate = new Array<number>(this.facturesDate.totalPages)
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

  onDetail(content,id){
    this.idFacture=id; //on recupere l'id de la facture selectionnée afin d'afficher ses produits
    this.doSearchDetail();
    this.modalservice.open(content);
  }

  doSearchDetail(){
    this.factureService.getProduitsFacture(this.idFacture).subscribe(
      data=>{
        this.pageFacturesDetail=data;

      },error => {
        console.log(error);
      }
    )
  }

  onPayment(content,id){
    this.idFacture=id;
    this.paiementClientService.getPaiementFacture(id).subscribe(
      data=>{
        this.paiements=data;
      },error => {
        console.log(error);
      }
    );
    this.paiementClientService.calculerCredit(id).subscribe(
      data=>{
        this.credits=data;
        console.log(typeof(this.credits) );
      },error => {
        console.log(error);
      }
    );

    this.modalservice.open(content);

  }
  onVerser(){
    this.router.navigate(['verser-client',this.idFacture]); //passer l'id dans le lien
    //Ce button ne doit etre activé que si le statut de la facture est "Non payé",sinn il faut qu'il soit desactivé
    //Après qu'on click sur ce button,on appelle au composant CREDIT_CLIENT qui contient qui doit contenir : montant à verser,montant
    //credit (qui va etre affiché automatiquement selon l'ancien credit.Apres avoir validé le versement,le payment doir etre ajouté
    //à la liste des payments.
  }
}
