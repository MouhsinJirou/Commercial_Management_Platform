import {Client} from "./model.client";

export class Facture {
  idFacture:any=null;
  dateFacture:Date;
  modePaiement:String;
  montantFacture:any;
  statu:string;
  client:Client;
  detailFactures=[];
  utilisateur:any;

}
