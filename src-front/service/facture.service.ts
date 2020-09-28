import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {BonAchat} from "../model/model.bonachat";
import {Facture} from "../model/model.facture";

@Injectable()
export class FactureService {
  constructor(public http:HttpClient) {
  }

  saveFacture(facture:Facture){
    return this.http.post("http://localhost:8080/factures",facture);
  }

  getFactures(page:number,size:number){
    return this.http.get("http://localhost:8080/chercherfactures?page="+page+"&size="+size);
  }

  getFacture(id:number){
    return this.http.get("http://localhost:8080/factures/"+id);
  }
  updateFacture(facture:Facture){
    return this.http.put("http://localhost:8080/factures/"+facture.idFacture,facture);
  }
  deleteFacture(id:number){
    return this.http.delete("http://localhost:8080/factures/"+id);
  }
  //elle retourne les produits d'une facture
  getProduitsFacture(id:number){
    return this.http.get("http://localhost:8080/facture/"+id+"/produits");
  }

  //total des ventes
  totalVente(){
    return this.http.get("http://localhost:8080/totalvente");
  }
  totalVenteDate(d1:Date,d2:Date){
    return this.http.get("http://localhost:8080/totalvente/"+d1+"/"+d2);
  }

  nombreVenteDate(d1:any,d2:any){
    return this.http.get("http://localhost:8080/nombrevente/"+d1+"/"+d2);
  }
  totalVenteString(d1:any,d2:any){
    return this.http.get("http://localhost:8080/totalvente/"+d1+"/"+d2);
  }
  //statistique rapport vente
  rapportVente(d1:Date,d2:Date,page:number,size:number){
    return this.http.get("http://localhost:8080/rapportvente/"+d1+"/"+d2+"?page="+page+"&size="+size);
  }
  getProduitsFacture2(id:number){
    return this.http.get("http://localhost:8080/factures/"+id+"/produits");
  }
  //tracabilité
  tracerFacture(id:number){
    return this.http.get("http://localhost:8080/utilisateurs/tracerfacture/"+id);
  }
}
