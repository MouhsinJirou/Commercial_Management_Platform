import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {BonAchat} from "../model/model.bonachat";
import {Depot} from "../model/model.depot";

@Injectable()
export class BonAchatService{
constructor(public http:HttpClient) {
}

saveBonAchat(bonAchat:BonAchat){
   return this.http.post("http://localhost:8080/bonachats",bonAchat);
}

getBonsAchat(page:number,size:number){
   return this.http.get("http://localhost:8080/chercherbonachats?page="+page+"&size="+size);
}

getBonAchat(id:number){
   return this.http.get("http://localhost:8080/bonachats/"+id);
}
updateBonAchat(bonAchat:BonAchat){
    return this.http.put("http://localhost:8080/bonachats/"+bonAchat.idBonAchat,bonAchat);
  }
  deleteBonAchat(id:number){
    return this.http.delete("http://localhost:8080/bonachats/"+id);
  }
  //elle retourne les produits d'un bon achat
  getProduitsBonAchat(id:number){
    return this.http.get("http://localhost:8080/bonachat/"+id+"/produits");
  }

  //total des achats
  totalAchat(){
    return this.http.get("http://localhost:8080/totalachat");
  }
  nombreAchatDate(d1:any,d2:any){
    return this.http.get("http://localhost:8080/nombreachat/"+d1+"/"+d2);
  }
  totalAchatDate(d1:Date,d2:Date){
    return this.http.get("http://localhost:8080/totalachat/"+d1+"/"+d2);
  }
  totalAchatString(d1:any,d2:any){
    return this.http.get("http://localhost:8080/totalachat/"+d1+"/"+d2);
  }
  //les produits d'un bon et retourne un produit
  getProduitsBonAchat2(id:number){
    return this.http.get("http://localhost:8080/bonachats/"+id+"/produits");
  }
  //tracabilité
  tracerBon(id:number){
    return this.http.get("http://localhost:8080/utilisateurs/tracerbon/"+id);
  }

}
