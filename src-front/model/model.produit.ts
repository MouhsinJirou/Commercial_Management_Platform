import {Depot} from "./model.depot";

export class Produit {
  idProduit:any=null;
  categorie:string='';
  designation:string='';
  nivStock:number=0;
  prixAchatMoyenne:number=0;
  prixVenteMin:number=0;
  refProduit:string='';
  seuil:number=0;
  depot:Depot=new Depot();

}
