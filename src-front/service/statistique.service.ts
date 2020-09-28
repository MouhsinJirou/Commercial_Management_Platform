import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";

@Injectable()
export class StatisticService{
  constructor(public http: HttpClient) {
  }

  venteJour(d1:Date,d2:Date){
    return this.http.get("http://localhost:8080/ventejour/"+d1+"/"+d2);
  }
  achatJour(d1:Date,d2:Date){
    return this.http.get("http://localhost:8080/achatjour/"+d1+"/"+d2);
  }
  chargeJour(d1:Date,d2:Date){
    return this.http.get("http://localhost:8080/chargejour/"+d1+"/"+d2);
  }

  venteMois(d1:String,d2:String){
    return this.http.get("http://localhost:8080/ventemois/"+d1+"/"+d2);
  }
  achatMois(d1:String,d2:String){
    return this.http.get("http://localhost:8080/achatmois/"+d1+"/"+d2);
  }
  chargeMois(d1:String,d2:String){
    return this.http.get("http://localhost:8080/chargemois/"+d1+"/"+d2);
  }
}
