import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {PaiementClient} from "../model/model.paiementclient";

@Injectable()
export class PaiementClientService{
  constructor(public http:HttpClient) {
  }

  savePaiementClient(paiementClient:PaiementClient){
    return this.http.post("http://localhost:8080/paiementclients",paiementClient);
  }

  getPaiementClients(nom:string,page:number,size:number){
    return this.http.get("http://localhost:8080/chercherpaiementclients?nom="+nom+"&page="+page+"&size="+size);
  }

  getPaiementClient(id:number){
    return this.http.get("http://localhost:8080/paiementclients/"+id);
  }
  updatePaiementClient(paiementClient:PaiementClient){
    return this.http.put("http://localhost:8080/paiementclients/"+paiementClient.idPaiementClient,paiementClient);
  }
  deletePaiementClient(id:number){
    return this.http.delete("http://localhost:8080/paiementclients/"+id);
  }
  getPaiementFacture(id:number){
    return this.http.get("http://localhost:8080/chercherpaiementfacture/"+id);
  }
  calculerCredit(id:number){
    return this.http.get("http://localhost:8080/calculercredit/"+id);
  }
  caisse(d1:any,d2:any){
    return this.http.get("http://localhost:8080/caisse/"+d1+"/"+d2);

  }
  profit(d1:any,d2:any){
    return this.http.get("http://localhost:8080/profit/"+d1+"/"+d2);

  }
}
