import {Facture} from "./model.facture";

export class PaiementClient{
  idPaiementClient:any=null;
  montantVerse:number;
  date:Date;
  facture:Object=new Facture();
}
