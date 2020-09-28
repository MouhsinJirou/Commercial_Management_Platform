import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {StatisticService} from "../../../service/statistique.service";

@Component({
  selector: 'app-achat-quotidienne',
  templateUrl: './achat-quotidienne.component.html',
  styleUrls: ['./achat-quotidienne.component.scss']
})
export class AchatQuotidienneComponent implements OnInit {
  selectedValue = null;
  d1: Date;
  d2: Date;

  somme: any;
  achats: any;

  constructor(public http: HttpClient, public statisticService: StatisticService) {
  }

  ngOnInit(): void {
  }

  chercherDate() {
    if (this.d1 != null && this.d2 != null) {
      this.statisticService.achatJour(this.d1, this.d2).subscribe(
        data => {
          this.achats = data;

          for (let c of this.achats) {
            //la 3eme colonne des ventes contient la somme des ventes
            this.somme = c[2];

          }
        }, error => console.log(error)
      )


    }
  }
}
