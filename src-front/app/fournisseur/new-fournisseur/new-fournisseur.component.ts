import { Component, OnInit } from '@angular/core';
import {FournisseurService} from "../../../service/fournisseur.service";
import {NgForm} from "@angular/forms";

@Component({
  selector: 'app-new-fournisseur',
  templateUrl: './new-fournisseur.component.html',
  styleUrls: ['./new-fournisseur.component.scss']
})
export class NewFournisseurComponent implements OnInit {
  confirmation:boolean;
  constructor(public fournisseurService:FournisseurService) { }

  ngOnInit(): void {
  }
  onSaveFournisseur(dataForm,f:NgForm){
    this.fournisseurService.saveFournisseur(dataForm).subscribe(data=>{
      console.log(data);
      this.confirmation=true;
      f.reset();
    },error => {
      console.log(JSON.parse(error._body).message);
      this.confirmation = false;
    })
  }
}
