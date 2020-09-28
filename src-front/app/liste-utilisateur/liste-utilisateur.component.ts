import { Component, OnInit } from '@angular/core';
import {ModalService} from "../../service/model.service";
import {UtilisateurService} from "../../service/utilisateur.service";
import {Utilisateur} from "../../model/model.utilisateur";
import {ModelUser} from "../../model/model.user";
import {BonAchatService} from "../../service/bonachat.service";
import {FactureService} from "../../service/facture.service";
import {ChargeService} from "../../service/charge.service";

@Component({
  selector: 'app-liste-utilisateur',
  templateUrl: './liste-utilisateur.component.html',
  styleUrls: ['./liste-utilisateur.component.scss']
})
export class ListeUtilisateurComponent implements OnInit {
  idUtilisateur:number;

  nomUtilisateur:string="";
  pageUtilisateurs:any;
  repassword:any=null;
  passwordIncorrect:boolean=false;
  utilisateur:ModelUser=new ModelUser();
  confirmation:boolean;
  trace:any;
  idChercher:any;
  etat:number;
  respoUtilisateur:any;
  operationFacture:any;
  operationBon:any;
  operationCharge:any;
  constructor(public modalservice:ModalService,public utilisateurService:UtilisateurService,
              public factureService:FactureService,public bonService:BonAchatService,public chargeService:ChargeService
  ) { }

  ngOnInit(): void {
    this.doSearch();
  }
  chercher(){
    this.doSearch();
  }

  doSearch(){
    this.utilisateurService.chercherUtilisateur(this.nomUtilisateur).subscribe(data=>{
      this.pageUtilisateurs=data;
    },error => {
      console.log(error);
    })
  }

  onUpdate(content,id){
    this.idUtilisateur=id;
    this.utilisateurService.getUtilisateur(id).subscribe(data=>{
      // @ts-ignore
      this.utilisateur=data;
    });
    this.modalservice.open(content);

  }
  updateUtilisateur(c){
    if(this.utilisateur.password!=this.repassword){
      alert("mot de passe incorrect")
    }
    else {
      this.utilisateurService.modifierUtilisateur(this.utilisateur).subscribe(data=>{
        this.confirmation=true;
        this.doSearch()
      },error => {
        this.confirmation=false;
        console.log(error)
      })

    }

  }
  onDelete(c:ModelUser){
    let confirm = window.confirm('Voulez-vous vraiment supprimer ce dépot ?');
    if (confirm == true) {
      this.utilisateurService.deleteUtilisateur(c.idUtilisateur).subscribe(data=>{
        this.pageUtilisateurs.splice(
          this.pageUtilisateurs.indexOf(c),1)
      },error => {
        console.log(error);
        alert("Il y a une erreur de la suppression !");
      });

    }
  }
  chercherTrace(){
    switch(this.trace){
      case 'Facture':
        this.etat=1;

        this.factureService.tracerFacture(this.idChercher).subscribe(data=>{
          this.respoUtilisateur=data;



        },error=>console.log(error))
        this.factureService.getFacture(this.idChercher).subscribe(data => {
          this.operationFacture = data;


        }, error => console.log(error))
        break;
      case 'Charge':
        this.etat=3;
        this.chargeService.tracerCharge(this.idChercher).subscribe(data=>{
          this.respoUtilisateur=data;
          this.chargeService.getCharge(this.idChercher).subscribe(data=>{
            this.operationCharge=data;
          },error=>console.log(error))
        },error=>console.log(error))
        break;
      case 'Achat':
        this.etat=2;
        this.bonService.tracerBon(this.idChercher).subscribe(data=>{
          this.respoUtilisateur=data;
          this.bonService.getBonAchat(this.idChercher).subscribe(data=>{
            this.operationBon=data;
          },error=>console.log(error))
        },error=>console.log(error))
        break;
    }
    console.log(this.etat + this.trace)
  }

  onSelect(a){
    console.log(this.trace)
  }
}
