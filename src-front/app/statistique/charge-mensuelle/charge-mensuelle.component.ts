import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {StatisticService} from "../../../service/statistique.service";

@Component({
  selector: 'app-charge-mensuelle',
  templateUrl: './charge-mensuelle.component.html',
  styleUrls: ['./charge-mensuelle.component.scss']
})
export class ChargeMensuelleComponent implements OnInit {
  d1: String;
  d2: String;
  somme: any;
  charges: any;
  constructor(public http: HttpClient, public statisticService: StatisticService) { }

  ngOnInit(): void {
  }
  chercherDate() {

    if (this.d1 != null && this.d2 != null) {
      var d1Full:String = this.d1 + "-01";
      var d2Full:String = this.d2 + "-01";
      this.statisticService.chargeMois(d1Full, d2Full).subscribe(
        data => {
          this.charges = data;

          for (let c of this.charges) {
            //la 3eme colonne des charges contient la somme des charges
            this.somme = c[3];

          }
        }, error => console.log(error)
      )


    }
  }
}
