import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {StatisticService} from "../../../service/statistique.service";

@Component({
  selector: 'app-charge-quotidienne',
  templateUrl: './charge-quotidienne.component.html',
  styleUrls: ['./charge-quotidienne.component.scss']
})
export class ChargeQuotidienneComponent implements OnInit {
  selectedValue=null;
  d1:Date;
  d2:Date;

  somme:any;
  charges:any;
  constructor(public http:HttpClient,public statisticService:StatisticService) { }

  ngOnInit(): void {
  }
  chercherDate() {
    if (this.d1 != null && this.d2 != null) {
      this.statisticService.chargeJour(this.d1, this.d2).subscribe(
        data => {
          this.charges = data;

          for (let c of this.charges) {
            //la 3eme colonne des ventes contient la somme des ventes
            this.somme = c[2];

          }
        }, error => console.log(error)
      )


    }

  }
}
