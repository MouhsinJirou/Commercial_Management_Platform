import {HttpClient} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Charge} from "../model/model.charge";

@Injectable()
export class ChargeService{
  constructor(public http: HttpClient) {
  }

  saveCharge(charge:Charge){
    return this.http.post("http://localhost:8080/charges",charge);
  }
  getCharges(motCle:string,page:number,size:number){
    return this.http.get("http://localhost:8080/cherchercharges?libelle="+motCle+"&page="+page+"&size="+size);
  }
  getCharge(id:number){
    return this.http.get("http://localhost:8080/charges/"+id);
  }
  updateCharge(charge:Charge){

    return this.http.put("http://localhost:8080/charges/"+charge.idCharge,charge);
  }

  deleteCharge(id:number){
    return this.http.delete("http://localhost:8080/charges/"+id);
  }

  //total des charges
  totalCharge(){
    return this.http.get("http://localhost:8080/totalcharge");
  }
  totalChargeDate(d1:Date,d2:Date){
    return this.http.get("http://localhost:8080/totalcharge/"+d1+"/"+d2);

  }
  nombreChargeDate(d1:any,d2:any){
    return this.http.get("http://localhost:8080/nombrecharge/"+d1+"/"+d2);
  }
  listChargeParDate(d1:Date,d2:Date,page:number,size:number){
    return this.http.get("http://localhost:8080/chargepardate/"+d1+"/"+d2+"?page="+page+"&size="+size);
  }
  totalChargeString(d1:any,d2:any){
    return this.http.get("http://localhost:8080/totalcharge/"+d1+"/"+d2);

  }
  //tracabilité
  tracerCharge(id:number){
    return this.http.get("http://localhost:8080/utilisateurs/tracercharge/"+id);
  }
}
