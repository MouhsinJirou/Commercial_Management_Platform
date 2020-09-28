import { Component, OnInit } from '@angular/core';
import {ProduitService} from "../../service/produit.service";
import {HttpClient} from "@angular/common/http";
import {DatePipe} from "@angular/common";
import {ChargeService} from "../../service/charge.service";
import {FactureService} from "../../service/facture.service";
import {BonAchatService} from "../../service/bonachat.service";
import {ClientService} from "../../service/client.service";
import {ToastrService} from "ngx-toastr";
//import { Chart } from 'chart.js';
import * as Chart from 'chart.js'
import {PaiementClientService} from "../../service/paiementclient.service";
@Component({
  selector: 'app-accueil',
  templateUrl: './accueil.component.html',
  styleUrls: ['./accueil.component.scss'],
  providers: [DatePipe]

})
export class AccueilComponent implements OnInit {
  nombreProduit:any;
  totalChargeDate:any=0;
  totalVenteDate:any=0;
  totalAchatDate:any=0;
  todayString : string = new Date().toDateString();
  yesterday:Date=new Date();
  hier:String=new Date().toDateString();
  myDateToday:string;
  myDateYesterday:string;
  test:Date=new Date();
  firstDay:string=new Date(this.test.getFullYear(),this.test.getMonth(),1).toDateString();
  firtDayYear:string=new Date(this.test.getFullYear(),0,1).toDateString();
  firstDayOfMonth:string;
  firstDayOfYear:string;
  year:any=this.test.getFullYear();
  nombreVenteMonth:any=0;
  totalVenteMonth:any=0;
  totalVenteYear:any=0;
  nombreAchatMonth:any=0;
  totalAchatMonth:any=0;
  totalAchatYear:any=0;
  nombreChargetMonth:any=0;
  totalChargeMonth:any=0;
  nombreProduitRupture:any=0;
  listClientDuMois:any;
  number:any;
  chart:any;
  chart2:any;
  totalVenteMonthChart:any=0;
  totalAchatMonthChart:any=0;
  totalChargeMonthChart:any=0;
  currentMonth:any;
  array=[];
  previousMonthEnd:Date=new Date();
  lastMonthEnd:String;
  previousMonthStart:String;
  lastMonthStart:String;
  previousMonth:String;
  totalVentePreviousMonthChart:any=0;
  totalAchatPreviousMonthChart:any=0;
  totalChargePreviousMonthChart:any=0;
  totalVentePrevious2MonthChart:any=0;
  totalAchatPrevious2MonthChart:any=0;
  totalChargePrevious2MonthChart:any=0;
  last2MonthEnd:String;
  previous2MonthStart:String;
  last2MonthStart:String;
  previous2Month:String;
  last3MonthEnd:String;
  previous3MonthStart:String;
  last3MonthStart:String;
  previous3Month:String;
  last4MonthEnd:String;
  previous4MonthStart:String;
  last4MonthStart:String;
  previous4Month:String;
  last5MonthEnd:String;
  previous5MonthStart:String;
  last5MonthStart:String;
  previous5Month:String;
  caisse:any;
  caisse1:any;
  caisse2:any;
  caisse3:any;
  caisse4:any;
  caisse5:any;
  taux : number;
  increase:boolean;
  constructor(public produitService:ProduitService,public http:HttpClient, public datePipe:DatePipe,public chargeService:ChargeService,public factureService:FactureService,public bonAchatService:BonAchatService,public clientService:ClientService,public toastr:ToastrService,public paiementClient:PaiementClientService) {
    this.hier=new Date(this.yesterday.setDate(this.yesterday.getDate()-1)).toDateString();
    //date aujourdhui
    this.myDateToday = this.datePipe.transform(this.todayString, 'yyyy-MM-dd');
    // Mois courant sous la forme mois/année
    this.currentMonth=this.test.getMonth()+1+"/"+this.test.getFullYear();
    //date hier
    this.myDateYesterday = this.datePipe.transform(this.hier, 'yyyy-MM-dd');
    //premier jour du mois
    this.firstDayOfMonth=this.datePipe.transform(this.firstDay, 'yyyy-MM-dd');
    //premier jour de l'annéee
    this.firstDayOfYear=this.datePipe.transform(this.firtDayYear, 'yyyy-MM-dd');
    this.previousMonthEnd.setDate(0);
    //fin du mois precedent
    this.lastMonthEnd=this.datePipe.transform(this.previousMonthEnd, 'yyyy-MM-dd');
    this.previousMonthStart=new Date(this.previousMonthEnd.getFullYear(),this.previousMonthEnd.getMonth(),1).toDateString();
    //debut du mois precedent
    this.lastMonthStart=this.datePipe.transform(this.previousMonthStart, 'yyyy-MM-dd');
    // Mois precendent sous la forme mois/année
    this.previousMonth=this.previousMonthEnd.getMonth()+1+"/"+this.previousMonthEnd.getFullYear();
    this.previousMonthEnd.setDate(0);
    //fin du mois avant precedent
    this.last2MonthEnd=this.datePipe.transform(this.previousMonthEnd, 'yyyy-MM-dd');
    this.previous2MonthStart=new Date(this.previousMonthEnd.getFullYear(),this.previousMonthEnd.getMonth(),1).toDateString();
    //debut du mois avant precedent
    this.last2MonthStart=this.datePipe.transform(this.previous2MonthStart, 'yyyy-MM-dd');
    // Mois avant precendent sous la forme mois/année
    this.previous2Month=this.previousMonthEnd.getMonth()+1+"/"+this.previousMonthEnd.getFullYear();
    this.previousMonthEnd.setDate(0);
    //fin du mois3 precedent
    this.last3MonthEnd=this.datePipe.transform(this.previousMonthEnd, 'yyyy-MM-dd');
    this.previous3MonthStart=new Date(this.previousMonthEnd.getFullYear(),this.previousMonthEnd.getMonth(),1).toDateString();
    //debut du mois avant precedent
    this.last3MonthStart=this.datePipe.transform(this.previous3MonthStart, 'yyyy-MM-dd');
    // Mois 3 precendent sous la forme mois/année
    this.previous3Month=this.previousMonthEnd.getMonth()+1+"/"+this.previousMonthEnd.getFullYear();
    this.previousMonthEnd.setDate(0);
    //fin du mois4 precedent
    this.last4MonthEnd=this.datePipe.transform(this.previousMonthEnd, 'yyyy-MM-dd');
    this.previous4MonthStart=new Date(this.previousMonthEnd.getFullYear(),this.previousMonthEnd.getMonth(),1).toDateString();
    //debut du mois4 avant precedent
    this.last4MonthStart=this.datePipe.transform(this.previous4MonthStart, 'yyyy-MM-dd');
    // Mois 4 precendent sous la forme mois/année
    this.previous4Month=this.previousMonthEnd.getMonth()+1+"/"+this.previousMonthEnd.getFullYear();
    this.previousMonthEnd.setDate(0);
    //fin du mois 5 precedent
    this.last5MonthEnd=this.datePipe.transform(this.previousMonthEnd, 'yyyy-MM-dd');
    this.previous5MonthStart=new Date(this.previousMonthEnd.getFullYear(),this.previousMonthEnd.getMonth(),1).toDateString();
    //debut du mois 5avant precedent
    this.last5MonthStart=this.datePipe.transform(this.previous5MonthStart, 'yyyy-MM-dd');
    // Mois 5 precendent sous la forme mois/année
    this.previous5Month=this.previousMonthEnd.getMonth()+1+"/"+this.previousMonthEnd.getFullYear();

  }

  ngOnInit(): void {
    this.traitementHaut();
    this.traitementRapportMensuelle();
    this.traitementRapportAnnuelle();
    this.traitementClientDuMois();
    this.showToastr();
    this.graph1();
    this.graph2();

  }
  traitementHaut(){
    this.produitService.nombreProduit().subscribe(data=>{
      this.nombreProduit=data;
    },error => {
      console.log(error);
    });
    this.chargeService.totalChargeString(this.myDateYesterday,this.myDateToday).subscribe(data=>{
      this.totalChargeDate=data;
    },error => {
      console.log(error);
    });
    this.factureService.totalVenteString(this.myDateYesterday,this.myDateToday).subscribe(data=>{
      this.totalVenteDate=data;
    },error => {
      console.log(error);
    });
    this.bonAchatService.totalAchatString(this.myDateYesterday,this.myDateToday).subscribe(data=>{
      this.totalAchatDate=data;
    },error => {
      console.log(error);
    })
  }

  traitementRapportMensuelle(){
    this.factureService.nombreVenteDate(this.firstDayOfMonth,this.myDateToday).subscribe(data=>{
       this.nombreVenteMonth=data;
    },error => {
      console.log(error);
    });
    this.factureService.totalVenteString(this.firstDayOfMonth,this.myDateToday).subscribe(data=>{
      this.totalVenteMonth=data;
    },error => {
      console.log(error);
    });
    this.bonAchatService.nombreAchatDate(this.firstDayOfMonth,this.myDateToday).subscribe(data=>{
      this.nombreAchatMonth=data;
    },error => {
      console.log(error);
    });
    this.bonAchatService.totalAchatString(this.firstDayOfMonth,this.myDateToday).subscribe(data=>{
      this.totalAchatMonth=data
    },error => {
      console.log(error);
    });
    this.produitService.nombreAlerteProduit().subscribe(data=>{
      this.nombreProduitRupture=data;
    },error => {
      console.log(error);
    })
    this.chargeService.totalChargeString(this.firstDayOfMonth,this.myDateToday).subscribe(data=>{
      this.totalChargeMonth=data;
    },error => {
      console.log(error);
    });
    this.chargeService.nombreChargeDate(this.firstDayOfMonth,this.myDateToday).subscribe(data=>{
      this.nombreChargetMonth=data;
    },error => {
      console.log(error);
    })
  }

  traitementRapportAnnuelle(){
    this.factureService.totalVenteString(this.firstDayOfYear,this.myDateToday).subscribe(data=>{
      this.totalVenteYear=data;
    },error => {
      console.log(error);
    });
    this.bonAchatService.totalAchatString(this.firstDayOfYear,this.myDateToday).subscribe(data=>{
      this.totalAchatYear=data;
    },error => {
      console.log(error);
    })
  }
  traitementClientDuMois(){
    this.clientService.clientDuMois(this.firstDayOfMonth,this.myDateToday).subscribe(data=>{
      this.listClientDuMois=data
    },error => {
      console.log(error);
    })
  }
  showToastr(){
    this.produitService.nombreAlerteProduit().subscribe(data=>{
      this.number=data;
      if(this.number!=0){
        this.toastr.error("","Alert Stock");
      }
    },error => {
      console.log(error);
    });
  }
  graph1(){
this.factureService.totalVenteString(this.firstDayOfMonth,this.myDateToday).subscribe(data=>{
  this.totalVenteMonthChart=data;

  this.bonAchatService.totalAchatString(this.firstDayOfMonth,this.myDateToday).subscribe(data=>{
    this.totalAchatMonthChart=data;

    this.chargeService.totalChargeString(this.firstDayOfMonth,this.myDateToday).subscribe(data=>{
      this.totalChargeMonthChart=data;

      this.factureService.totalVenteString(this.lastMonthStart,this.lastMonthEnd).subscribe(data=>{
        this.totalVentePreviousMonthChart=data;

        this.bonAchatService.totalAchatString(this.lastMonthStart,this.lastMonthEnd).subscribe(data=>{
          this.totalAchatPreviousMonthChart=data;

          this.chargeService.totalChargeString(this.lastMonthStart,this.lastMonthEnd).subscribe(data=>{
            this.totalChargePreviousMonthChart=data;

            this.factureService.totalVenteString(this.last2MonthStart,this.last2MonthEnd).subscribe(data=>{
              this.totalVentePrevious2MonthChart=data;

              this.bonAchatService.totalAchatString(this.last2MonthStart,this.last2MonthEnd).subscribe(data=>{
                this.totalAchatPrevious2MonthChart=data;

                this.chargeService.totalChargeString(this.last2MonthStart,this.last2MonthEnd).subscribe(data=>{
                  this.totalChargePrevious2MonthChart=data;

                  this.chart=new Chart('barchart',{
                    type: 'bar',
                    data: {
                      labels: [this.previous2Month, this.previousMonth, this.currentMonth],
                      datasets: [
                        {
                          label: "Vente",
                          backgroundColor: "#3e95cd",
                          data: [this.totalVentePrevious2MonthChart,this.totalVentePreviousMonthChart,this.totalVenteMonthChart]
                        }, {
                          label: "Achat",
                          backgroundColor: "#8e5ea2",
                          data: [this.totalAchatPrevious2MonthChart,this.totalAchatPreviousMonthChart,this.totalAchatMonthChart]
                        },
                        {
                          label: "Charge",
                          backgroundColor: "#c45850",
                          data: [this.totalChargePrevious2MonthChart,this.totalChargePreviousMonthChart,this.totalChargeMonthChart]
                        }
                      ]
                    },
                    options: {
                      scales: {
                        yAxes: [{
                          ticks: {
                            beginAtZero: true
                          }
                        }]
                      },
                      title: {
                        display: false,
                        text: 'Statistiques 3 dernier mois'
                      }
                    }
                  });
                },error => {
                  console.log(error);
                });
              },error => {
                console.log(error);
              })
            },error => {
              console.log(error);
            })

          },error => {
            console.log(error);
          })
        },error => {
          console.log(error);
        })

      },error => {
        console.log(error)
      });

    },error => {
      console.log(error);
    });
  },error => {
    console.log(error);
  });
},error => {
  console.log(error);
});

  }

  graph2(){
    this.paiementClient.profit(this.firstDayOfMonth,this.myDateToday).subscribe(data=>{
      this.caisse=data;
      this.paiementClient.profit(this.lastMonthStart,this.lastMonthEnd).subscribe(data=>{
        this.caisse1=data;
        this.paiementClient.profit(this.last2MonthStart,this.last2MonthEnd).subscribe(data=>{
          this.caisse2=data;
          this.paiementClient.profit(this.last3MonthStart,this.last3MonthEnd).subscribe(data=>{
            this.caisse3=data;
            this.paiementClient.profit(this.last4MonthStart,this.last4MonthEnd).subscribe(data=>{
              this.caisse4=data;
              this.paiementClient.profit(this.last5MonthStart,this.last5MonthEnd).subscribe(data=>{
                this.caisse5=data;
                this.taux=(this.caisse-this.caisse1)/this.caisse1;
                console.log(this.taux)
                if(this.taux>=0){
                  this.increase=true;
                }else {
                  this.increase=false
                }
                this.chart2=new Chart('line',{
                  type: 'line',
                  data: {
                    labels: [this.previous5Month, this.previous4Month, this.previous3Month, this.previous2Month, this.previousMonth, this.currentMonth],
                    datasets: [
                      {
                        label: "Profit",
                        backgroundColor: "#cd6abb",
                        borderColor: "#cd6abb",
                        data: [this.caisse5,this.caisse4,this.caisse3,this.caisse2,this.caisse1,this.caisse],

                        fill: false,
                        borderDash: [5, 5],
                        pointRadius: 7,
                        pointHoverRadius: 8,
                      }
                    ]
                  },
                  options: {
                    responsive: true,
                    legend: {
                      position: 'bottom',
                    },
                    hover: {
                      mode: 'index'
                    }
                  }
                });
              },error => {
                console.log(error);
              })
            },error =>{
              console.log(error);
            } )

          },error => {
            console.log(error);
          })
        },error => {
          console.log(error);
        })
      },error => {
        console.log(error);
      })
    },error => {
      console.log(error)
    })
  }
}

