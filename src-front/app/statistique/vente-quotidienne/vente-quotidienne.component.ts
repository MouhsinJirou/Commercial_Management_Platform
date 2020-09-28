import { Component, OnInit } from '@angular/core';
import {newArray} from "@angular/compiler/src/util";
import {HttpClient} from "@angular/common/http";
import {StatisticService} from "../../../service/statistique.service";


@Component({
  selector: 'app-vente-quotidienne',
  templateUrl: './vente-quotidienne.component.html',
  styleUrls: ['./vente-quotidienne.component.scss']
})
export class VenteQuotidienneComponent implements OnInit {

  selectedValue=null;
  d1:Date;
  d2:Date;

  somme:any;
  ventes:any;
  constructor(public http:HttpClient,public statisticService:StatisticService) { }

  ngOnInit(): void {
    this.chercherDate();
  }


  chercherDate(){
    if (this.d1 != null && this.d2!=null) {
      this.statisticService.venteJour(this.d1,this.d2).subscribe(
        data => {
          this.ventes=data;

          for (let c of this.ventes) {
            //la 3eme colonne des ventes contient la somme des ventes
            this.somme=c[2];

          }
        }, error => console.log(error)
      )


    }



  }




  /*
  onSelect(event) {
      //selected c la valeur selectionée dans le dropBox
    let selected = event.target.value;
    console.log(selected);
    console.log("####"+this.mois[0].name);

    switch (selected){
      case this.mois[0].name:{

      }
    }
      }

  */


}
