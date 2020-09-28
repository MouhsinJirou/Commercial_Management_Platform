import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Depot} from "../model/model.depot";


@Injectable()
export class DepotService {
  constructor(public http: HttpClient) {
  }

  saveDepot(depot:Depot){
    return this.http.post("http://localhost:8080/depots",depot);
  }
  listDepot(){
    return this.http.get("http://localhost:8080/depots");
  }

  getDepots(motCle:string,page:number,size:number){


    return this.http.get("http://localhost:8080/chercherdepots?emp="+motCle+"&page="+page+"&size="+size);
  }

  getDepot(id:number){

    return this.http.get("http://localhost:8080/depots/"+id);
  }

  updateDepot(depot:Depot){

    return this.http.put("http://localhost:8080/depots/"+depot.idDepot,depot);
  }
  deleteDepot(id:number){
    return this.http.delete("http://localhost:8080/depots/"+id);
  }

  chercherProduitsDepot(id:number,des:string,page:number,size:number){
    return this.http.get("http://localhost:8080/depots/"+id+"/produits?designation="+des+"&size="+size+"&page="+page);
  }


}
