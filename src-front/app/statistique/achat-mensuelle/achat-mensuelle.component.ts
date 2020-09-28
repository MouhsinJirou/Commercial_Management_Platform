import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {StatisticService} from "../../../service/statistique.service";

@Component({
  selector: 'app-achat-mensuelle',
  templateUrl: './achat-mensuelle.component.html',
  styleUrls: ['./achat-mensuelle.component.scss']
})
export class AchatMensuelleComponent implements OnInit {
  d1: String;
  d2: String;
  somme: any;
  achats: any;
  constructor(public http: HttpClient, public statisticService: StatisticService) { }

  ngOnInit(): void {
  }
  chercherDate() {

    if (this.d1 != null && this.d2 != null) {
      var d1Full:String = this.d1 + "-01";
      var d2Full:String = this.d2 + "-01";
      this.statisticService.achatMois(d1Full, d2Full).subscribe(
        data => {
          this.achats = data;

          for (let c of this.achats) {
            //la 3eme colonne des achats contient la somme des achats
            this.somme = c[3];

          }
        }, error => console.log(error)
      )


    }
  }
}
