import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Produit} from "../model/model.produit";
import {Fournisseur} from "../model/model.fournisseur";

@Injectable()
export class FournisseurService {
  constructor(public http:HttpClient) {
  }

  getFournisseurs(nom:string,size:number,page:number){
    return this.http.get("http://localhost:8080/chercherfournisseurs?nom="+nom+"&size="+size+"&page="+page);
  }
  deleteFournisseur(id:number){
    return this.http.delete("http://localhost:8080/fournisseurs/"+id);
  }
  listFournisseur(){
    return this.http.get("http://localhost:8080/fournisseurs");
  }

  getFournisseur(id:number){
    return this.http.get("http://localhost:8080/fournisseurs/"+id);
  }
  updateFournisseur(fournisseur:Fournisseur){
    return this.http.put("http://localhost:8080/fournisseurs/"+fournisseur.idFournisseur,fournisseur)
  }

  saveFournisseur(fournisseur:Fournisseur){
    return this.http.post("http://localhost:8080/fournisseurs",fournisseur)
  }
  //liste des bon selon le fournisseur
  bonParFournisseur(id:number,page:number,size:number){
    return this.http.get("http://localhost:8080/fournisseur/"+id+"/bonachat?page="+page+"&size="+size)
  }
  bonFournisseurDate(id:number,d1:Date,d2:Date,page:number,size:number){
    return this.http.get("http://localhost:8080/fournisseur/"+id+"/bonachat/"+d1+"/"+d2+"?page="+page+"&size="+size) //d1:date debut
  }
  //Liste des avoir
  avoirParFournisseur(id:number,page:number,size:number){
    return this.http.get("http://localhost:8080/fournisseur/"+id+"/avoir?page="+page+"&size="+size)
  }
  avoirFournisseurDate(id:number,d1:Date,d2:Date,page:number,size:number){
    return this.http.get("http://localhost:8080/fournisseur/"+id+"/avoir/"+d1+"/"+d2+"?page="+page+"&size="+size) //d1:date debut
  }

  //Total Montant bonachat d'un fournisseur
  totalBonAchatsFournisseur(id:number){
    return this.http.get("http://localhost:8080/totalbonachatsfournisseur/"+id)
  }
  //Total Montant versé d'un fournisseur
  totalPayementsFournisseur(id:number){
    return this.http.get("http://localhost:8080/totalpayementsfournisseur/"+id)
  }
  listFournisseurs(){
    return this.http.get("http://localhost:8080/fournisseurs");
  }
}
