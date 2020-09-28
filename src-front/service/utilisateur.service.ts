import {Injectable} from "@angular/core";
import {RegisterUser} from "../model/model.register";
import {HttpClient} from "@angular/common/http";
import {ModelUser} from "../model/model.user";

@Injectable()
export class UtilisateurService {

  constructor(public http: HttpClient) {
  }
  regiterUtilisateur(register:RegisterUser){
    return this.http.post("http://localhost:8080/register",register);
  }

  chercherUtilisateur(nom:string){
    return this.http.get("http://localhost:8080/chercherutilisateur?nom="+nom)
  }

  deleteUtilisateur(id:number){
    return this.http.delete("http://localhost:8080/utilisateurs/"+id);
  }

  getUtilisateur(id:number){
    return this.http.get("http://localhost:8080/utilisateurs/"+id);
  }

  modifierUtilisateur(modelUser:ModelUser){
    return this.http.put("http://localhost:8080/utilisateurs/"+modelUser.idUtilisateur,modelUser)
  }

}
