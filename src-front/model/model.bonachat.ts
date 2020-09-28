import {Fournisseur} from "./model.fournisseur";

export class BonAchat{
  idBonAchat:any=null;
  date:Date;
  montantTotal:any;
  modePaiement:String;
  statu:String;
  fournisseur:Fournisseur;
  achats=[];
  utilisateur:any;

}
