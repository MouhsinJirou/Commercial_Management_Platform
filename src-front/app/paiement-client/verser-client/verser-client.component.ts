import { Component, OnInit } from '@angular/core';
import {PaiementClient} from "../../../model/model.paiementclient";
import {ActivatedRoute, Router} from "@angular/router";
import {DatePipe} from "@angular/common";
import {PaiementClientService} from "../../../service/paiementclient.service";
import {FactureService} from "../../../service/facture.service";

@Component({
  selector: 'app-verser-client',
  templateUrl: './verser-client.component.html',
  styleUrls: ['./verser-client.component.scss'],
  providers: [DatePipe]

})
export class VerserClientComponent implements OnInit {
  idFacture:number;
  credits:any;
  factures:any;
  myDate:string;
  todayString : string = new Date().toDateString();
  confirmation:boolean;
  idClient:any;

  paiementClient:PaiementClient=new PaiementClient();
  constructor(public activatedroute:ActivatedRoute,public datePipe:DatePipe,public paiementClientService:PaiementClientService,public factureService:FactureService,public router:Router) {
    this.idFacture = activatedroute.snapshot.params['id'];
    //recuperer la date d ajourdhui
    this.myDate = this.datePipe.transform(this.todayString, 'yyyy-MM-dd');
  }

  ngOnInit(): void {
    //calculer le credit restant pour la facture specifiée
    this.paiementClientService.calculerCredit(this.idFacture).subscribe(data=>
    {this.credits=data},error => {
      console.log(error);
    });

    //recupere la facture dont l id specifié
    this.factureService.getFacture(this.idFacture).subscribe(data=>{
      this.factures=data;
      this.idClient=this.factures.client.idClient
      console.log(this.idClient);
    },error => console.log(error));

  }
  verser(dataForm){
    //verifier si le montant versé entré dans le forum n"est pas sup au credit restant
    if (dataForm.montantVerse<=this.credits){
      //remplir le model de verserclient
      this.paiementClient.date=dataForm.date;
      this.paiementClient.montantVerse=dataForm.montantVerse;
      this.paiementClient.facture=this.factures;

      this.paiementClientService.savePaiementClient(this.paiementClient).subscribe(
        data => {
          console.log(data);
          alert("Le montant est versé avec succée !");
          this.router.navigate(['liste-facture',this.idClient]); //passer l'id dans le lien

        },error =>  {
          console.log(error);
          this.confirmation=false;
        }
      )
    }
    else
      alert("Le montant versé est supérieur au crédit restant !");
  }
}
