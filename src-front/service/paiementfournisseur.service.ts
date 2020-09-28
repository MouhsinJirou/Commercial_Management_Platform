import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {PaiementFournisseur} from "../model/model.paiementFournisseur";

@Injectable()
export class PaiementFournisseurService{
  constructor(public http:HttpClient) {
  }

  savePaiementFournisseur(paiementFournisseur:PaiementFournisseur){
    return this.http.post("http://localhost:8080/paiementfournisseurs",paiementFournisseur);
  }

  getPaiementFournisseurs(nom:string,page:number,size:number){
    return this.http.get("http://localhost:8080/chercherpaiementfournisseurs?nom="+nom+"&page="+page+"&size="+size);
  }

  getPaiementFournisseur(id:number){
    return this.http.get("http://localhost:8080/paiementfournisseurs/"+id);
  }

  updatePaiementFournisseur(paiementFournisseur:PaiementFournisseur){
    return this.http.put("http://localhost:8080/paiementfournisseurs/"+paiementFournisseur.idPaiementFournisseur,paiementFournisseur);
  }

  deletePaiementFournisseur(id:number){
    return this.http.delete("http://localhost:8080/paiementfournisseurs/"+id);
  }

  chercherPaiementParBon(id:number){
    return this.http.get("http://localhost:8080/chercherpaiementsparbon/"+id);
  }

  totalPaiementsBon(id:number){
    return this.http.get("http://localhost:8080/totalpaiementsbon/"+id);
  }
}
