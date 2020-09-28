import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Produit} from "../model/model.produit";

@Injectable()
export class ProduitService {
  constructor(public http:HttpClient) {
  }
  getProduits(des:string,size:number,page:number){
    return this.http.get("http://localhost:8080/chercherproduits?des="+des+"&size="+size+"&page="+page);
  }
  listProduits(){
    return this.http.get("http://localhost:8080/produits");
  }

  deleteProduit(id:number){
    return this.http.delete("http://localhost:8080/produits/"+id);
  }

  getProduit(id:number){
    return this.http.get("http://localhost:8080/produits/"+id);
  }
  updateProduit(produit:Produit){
    return this.http.put("http://localhost:8080/produits/"+produit.idProduit,produit)
  }

  saveProduit(produit:Produit){
    return this.http.post("http://localhost:8080/produits",produit)
  }

  alerterProduit(designation:String,page:number,size:number){
    return this.http.get("http://localhost:8080/alerterproduit?des="+designation+"&page="+page+"&size="+size)
  }

  nombreProduit(){
    return this.http.get("http://localhost:8080/nombreproduit")
  }
  nombreProduitRupture(){
    return this.http.get("http://localhost:8080/nombreproduitrupture")
  }
  nombreAlerteProduit(){
    return this.http.get("http://localhost:8080/nombrealerteproduit")
  }
  //retourne le prix d'achat moyenne
  getPrixAchat(id){
    return this.http.get("http://localhost:8080/produits/prixachat/"+id);
  }

  //prix achat total d'un produit
  prixAchatTotal(id){
    return this.http.get("http://localhost:8080/produits/prixachattotal/"+id);
  }
}
