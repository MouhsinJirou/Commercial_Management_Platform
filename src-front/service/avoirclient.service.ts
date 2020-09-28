import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {AvoirClient} from "../model/model.avoirclient";

@Injectable()
export class AvoirClientService {
  constructor(public http:HttpClient) {
  }
  getAvoirsClient(nom:string,size:number,page:number){
    return this.http.get("http://localhost:8080/chercheravoirclients?nom="+nom+"&size="+size+"&page="+page);
  }
  deleteAvoirClient(id:number){
    return this.http.delete("http://localhost:8080/avoirclients/"+id);
  }
  getAvoirClient(id:number){
    return this.http.get("http://localhost:8080/avoirclients/"+id);
  }
  getDetailAvoirClient(id:number){
    return this.http.get("http://localhost:8080/avoirclient/"+id+"/details");
  }
  saveAvoirClient(avoirClient:AvoirClient){
    return this.http.post("http://localhost:8080/avoirclients",avoirClient)
  }
}
