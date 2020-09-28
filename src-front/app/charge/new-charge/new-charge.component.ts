import { Component, OnInit } from '@angular/core';
import {ChargeService} from "../../../service/charge.service";
import {NgForm} from "@angular/forms";
import {PaiementFournisseurService} from "../../../service/paiementfournisseur.service";
import {AuthService} from "../../../service/AuthService.service";
import {Charge} from "../../../model/model.charge";

@Component({
  selector: 'app-new-charge',
  templateUrl: './new-charge.component.html',
  styleUrls: ['./new-charge.component.scss']
})
export class NewChargeComponent implements OnInit {
  confirmation:boolean;
  utilisateur:any=null;
  libelle:String;
  type:String;
  montant:number;
  date:Date;
  charge:Charge=new Charge();

  constructor(public chargeService:ChargeService,public authService:AuthService) { }

  ngOnInit(): void {
    this.utilisateur=this.authService.user.getValue().utilisateur;

  }
  onSave(f:NgForm){
    this.charge.libelle=this.libelle;
    this.charge.date=this.date;
    this.charge.montant=this.montant;
    this.charge.type=this.type;
    this.charge.utilisateur=this.utilisateur;

    this.chargeService.saveCharge(this.charge)
      .subscribe(data=>{
          this.confirmation=true;
          f.reset();
        },
        err => {
          this.confirmation = false;
          console.log(err);
        });

  }
}
