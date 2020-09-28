import {Injectable} from "@angular/core";
import {Client} from "../model/model.client";
import {HttpClient} from "@angular/common/http";

@Injectable()
export class ClientService {
  constructor(public http: HttpClient) {
  }

  saveClient(client:Client){

    return this.http.post("http://localhost:8080/clients",client);
  }

  getClients(motCle:string,page:number,size:number){


    return this.http.get("http://localhost:8080/chercherclients?nom="+motCle+"&page="+page+"&size="+size);
  }

  getClient(id:number){

    return this.http.get("http://localhost:8080/clients/"+id);
  }

  updateClient(client:Client){

    return this.http.put("http://localhost:8080/clients/"+client.idClient,client);
  }
  deleteClient(id:number){
    return this.http.delete("http://localhost:8080/clients/"+id);
  }

  factureParClient(id:number){
    return this.http.get("http://localhost:8080/client/"+id+"/factures")
  }
//liste des factures pour un client selon son id
  factureClientId(id:number,page:number,size:number){
    return this.http.get("http://localhost:8080/client/"+id+"/facture?page="+page+"&size="+size)
  }
  factureClientDate(id:number,d1:Date,d2:Date,page:number,size:number){
    return this.http.get("http://localhost:8080/client/"+id+"/facture/"+d1+"/"+d2+"?page="+page+"&size="+size) //d1:date debut
  }
  //liste des avoirs

  avoirClientId(id:number,page:number,size:number){
    return this.http.get("http://localhost:8080/client/"+id+"/avoir?page="+page+"&size="+size)
  }
  avoirClientDate(id:number,d1:Date,d2:Date,page:number,size:number){
    return this.http.get("http://localhost:8080/client/"+id+"/avoir/"+d1+"/"+d2+"?page="+page+"&size="+size) //d1:date debut
  }

  //Total Montant factures d'un client
  totalFacturesClient(id:number){
    return this.http.get("http://localhost:8080/totalfacturesclient/"+id)
  }
  //Total Montant versé d'un client
  totalPayementsClient(id:number){
    return this.http.get("http://localhost:8080/totalpayementsclient/"+id)
  }
  clientDuMois(d1:any,d2:any){
    return this.http.get("http://localhost:8080/clientdumois/"+d1+"/"+d2+"?page=0&size=3") //d1:date debut
  }
  listClient(){
    return this.http.get("http://localhost:8080/clients");
  }
}
