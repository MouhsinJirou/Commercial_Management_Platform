import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {AvoirFournisseur} from "../model/model.avoirfournisseur";

@Injectable()
export class AvoirFournisseurService {
  constructor(public http:HttpClient) {
  }
  getAvoirsFournisseur(nom:string,size:number,page:number){
    return this.http.get("http://localhost:8080/chercheravoirfournisseurs?nom="+nom+"&size="+size+"&page="+page);
  }
  deleteAvoirFournisseur(id:number){
    return this.http.delete("http://localhost:8080/avoirfournisseurs/"+id);
  }

  getAvoirFournisseur(id:number){
    return this.http.get("http://localhost:8080/avoirfournisseurs/"+id);
  }
  getDetailAvoirFournisseur(id:number){
    return this.http.get("http://localhost:8080/avoirfournisseur/"+id+"/details");
  }
  saveAvoirFournisseur(avoirFournisseur:AvoirFournisseur){
    return this.http.post("http://localhost:8080/avoirfournisseurs",avoirFournisseur)
  }
}
