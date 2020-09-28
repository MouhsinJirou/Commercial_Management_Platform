import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {StatisticService} from "../../../service/statistique.service";

@Component({
  selector: 'app-vente-mensuelle',
  templateUrl: './vente-mensuelle.component.html',
  styleUrls: ['./vente-mensuelle.component.scss']
})
export class VenteMensuelleComponent implements OnInit {
  d1: String;
  d2: String;
  somme: any;
  ventes: any;

  constructor(public http: HttpClient, public statisticService: StatisticService) {
  }

  ngOnInit(): void {
  }

  chercherDate() {

    if (this.d1 != null && this.d2 != null) {
      var d1Full:String = this.d1 + "-01";
      var d2Full:String = this.d2 + "-01";
      this.statisticService.venteMois(d1Full, d2Full).subscribe(
        data => {
          this.ventes = data;

          for (let c of this.ventes) {
            //la 3eme colonne des ventes contient la somme des ventes
            this.somme = c[3];

          }
        }, error => console.log(error)
      )


    }
  }
}
