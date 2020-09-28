import { Component, OnInit } from '@angular/core';
import {ProduitService} from "../../../service/produit.service";
import {NgForm} from "@angular/forms";
import {DepotService} from "../../../service/depot.service";
import {Depot} from "../../../model/model.depot";

@Component({
  selector: 'app-new-produit',
  templateUrl: './new-produit.component.html',
  styleUrls: ['./new-produit.component.scss']
})
export class NewProduitComponent implements OnInit {

listDepots:any;
depot:Depot;
  size:number=50;
contenu:any;

  constructor(public produitService:ProduitService,public depotService:DepotService) { }
  confirmation:boolean;

  ngOnInit(): void {
    this.searchListDepot();
  }
  onSaveProduit(dataForm,f:NgForm){
    console.log(dataForm);
  this.produitService.saveProduit(dataForm).subscribe(data=>{
    console.log(data);
  this.confirmation=true;
  f.reset();
},error => {
  console.log(JSON.parse(error._body).message);
    this.confirmation = false;

})
}


  searchListDepot(){
    this.depotService.listDepot().subscribe(data=>{
     this.listDepots=data;
    },error => {
      console.log(error);
    })
  }

}
