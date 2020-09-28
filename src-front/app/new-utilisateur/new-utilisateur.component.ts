import { Component, OnInit } from '@angular/core';
import {NgForm} from "@angular/forms";
import {UtilisateurService} from "../../service/utilisateur.service";

@Component({
  selector: 'app-new-utilisateur',
  templateUrl: './new-utilisateur.component.html',
  styleUrls: ['./new-utilisateur.component.scss']
})
export class NewUtilisateurComponent implements OnInit {

  confirmation:boolean;
  errorMessage:any;
  constructor(public utilisateurService:UtilisateurService) { }

  ngOnInit(): void {
    this.errorMessage=null
  }

  onSaveUtilisateur(dataForm,f:NgForm){
   this.utilisateurService.regiterUtilisateur(dataForm).subscribe(data=>{
     this.confirmation=true;
     f.reset();

   },error => {
     console.log(error.error.message);
     this.errorMessage=error.error.message;
     this.confirmation=false

   })
  }


}
