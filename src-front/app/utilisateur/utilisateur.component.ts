import { Component, OnInit } from '@angular/core';
import {Utilisateur} from "../../model/model.utilisateur";

@Component({
  selector: 'app-utilisateur',
  templateUrl: './utilisateur.component.html',
  styleUrls: ['./utilisateur.component.scss']
})
export class UtilisateurComponent implements OnInit {
utilisateur:Utilisateur=new Utilisateur();
  constructor() { }

  ngOnInit(): void {
  }
updateUtilisateur(dataForm){

}
}
