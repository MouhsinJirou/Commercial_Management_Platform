import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Devis} from "../model/model.devis";

@Injectable()
export class DevisService {
  constructor(public http:HttpClient) {
  }

  saveDevis(devis:Devis){
    return this.http.post("http://localhost:8080/devis",devis);
  }

  getDeviss(page:number,size:number){
    return this.http.get("http://localhost:8080/chercherdevis?page="+page+"&size="+size);
  }

  getDevis(id:number){
    return this.http.get("http://localhost:8080/devis/"+id);
  }
  updateDevis(devis:Devis){
    return this.http.put("http://localhost:8080/devis/"+devis.idDevis,devis);
  }
  deleteDevis(id:number){
    return this.http.delete("http://localhost:8080/devis/"+id);
  }
  //elle retourne les produits d'un devis
  getProduitsDevis(id:number){
    return this.http.get("http://localhost:8080/devis/"+id+"/produits");
  }
}
