
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppComponent } from './app.component';

import {MDBBootstrapModule, ModalModule} from 'angular-bootstrap-md';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {RouterModule, Routes} from "@angular/router";
import {ProduitComponent} from "./produit/produit.component";
import {NewProduitComponent} from "./produit/new-produit/new-produit.component";
import {ModalService} from "../service/model.service";
import { DepotComponent } from './depot/depot.component';
import {NewDepotComponent} from "./depot/new-depot/new-depot.component";
import { FournisseurComponent } from './fournisseur/fournisseur.component';
import {NewFournisseurComponent} from "./fournisseur/new-fournisseur/new-fournisseur.component";
import {ListbonAchatsComponent} from "./fournisseur/listbon-achats/listbon-achats.component";
import {ListAvoirComponent} from "./fournisseur/list-avoir/list-avoir.component";
import { ChargeComponent } from './charge/charge.component';
import {NewChargeComponent} from "./charge/new-charge/new-charge.component";
import {ClientComponent} from "./client/client.component";
import {AjouterClientComponent} from "./client/ajouter-client/ajouter-client.component";
import {ListeFactureComponent} from "./client/liste-facture/liste-facture.component";
import {ListeAvoirComponent} from "./client/liste-avoir/liste-avoir.component";
import { BonAchatComponent } from './bon-achat/bon-achat.component';
import {NewBonAchatComponent} from "./bon-achat/new-bon-achat/new-bon-achat.component";
import { PaiementFournisseurComponent } from './paiement-fournisseur/paiement-fournisseur.component';
import {VerserFournisseurComponent} from "./paiement-fournisseur/verser-fournisseur/verser-fournisseur.component";
import { PaiementClientComponent } from './paiement-client/paiement-client.component';
import {VerserClientComponent} from "./paiement-client/verser-client/verser-client.component";
import { AlertComponent } from './alert/alert.component';
import { StatistiqueComponent } from './statistique/statistique.component';
import {VenteQuotidienneComponent} from "./statistique/vente-quotidienne/vente-quotidienne.component";
import {AchatQuotidienneComponent} from "./statistique/achat-quotidienne/achat-quotidienne.component";
import {ChargeQuotidienneComponent} from "./statistique/charge-quotidienne/charge-quotidienne.component";
import {VenteMensuelleComponent} from "./statistique/vente-mensuelle/vente-mensuelle.component";
import {AchatMensuelleComponent} from "./statistique/achat-mensuelle/achat-mensuelle.component";
import {ChargeMensuelleComponent} from "./statistique/charge-mensuelle/charge-mensuelle.component";
import {NewAvoirClientComponent} from "./avoir-client/new-avoir-client/new-avoir-client.component";
import {AvoirClientComponent} from "./avoir-client/avoir-client.component";
import {AvoirFournisseurComponent} from "./avoir-fournisseur/avoir-fournisseur.component";
import {NewAvoirFournisseurComponent} from "./avoir-fournisseur/new-avoir-fournisseur/new-avoir-fournisseur.component";
import {DevisComponent} from "./devis/devis.component";
import {AjouterDevisComponent} from "./devis/ajouter-devis/ajouter-devis.component";
import { AccueilComponent } from './accueil/accueil.component';
import { FactureComponent } from './facture/facture.component';
import {NewFactureComponent} from "./facture/new-facture/new-facture.component";
import {ProduitService} from "../service/produit.service";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {FournisseurService} from "../service/fournisseur.service";
import {DepotService} from "../service/depot.service";
import { UtilisateurComponent } from './utilisateur/utilisateur.component';
import {BonAchatService} from "../service/bonachat.service";
import {FactureService} from "../service/facture.service";
import {DevisService} from "../service/devis.service";
import {PaiementFournisseurService} from "../service/paiementfournisseur.service";
import {PaiementClientService} from "../service/paiementclient.service";
import {AvoirFournisseurService} from "../service/avoirfournisseur.service";
import {AvoirClientService} from "../service/avoirclient.service";
import {ClientService} from "../service/client.service";
import {ChargeService} from "../service/charge.service";
import {StatisticService} from "../service/statistique.service";
import {DatePipe} from "@angular/common";
import {ToastrModule} from "ngx-toastr";
import { LoginComponent } from './login/login.component';
import {AuthService} from "../service/AuthService.service";
import {AuthInterceptorService} from "../service/auth-interceptor.service";
import { SidebarComponent } from './sidebar/sidebar.component';
import { NavbarComponent } from './navbar/navbar.component';
import { FooterComponent } from './footer/footer.component';
import {AuthGuard} from "../service/auth.guard";
import { ListeUtilisateurComponent } from './liste-utilisateur/liste-utilisateur.component';
import { NewUtilisateurComponent } from './new-utilisateur/new-utilisateur.component';
import {UtilisateurService} from "../service/utilisateur.service";
import {RoleGuard} from "../service/role.guard.service";
import {LoginGuard} from "../service/login.guard";
import { StatistiqueUtilisateurComponent } from './statistique-utilisateur/statistique-utilisateur.component';


const routes:Routes=[
  {path:'produit',component:ProduitComponent,canActivate:[AuthGuard]},
  {path:'new-produit',component:NewProduitComponent,canActivate:[AuthGuard]},
  {path:'depot',component:DepotComponent,canActivate:[AuthGuard]},
  {path:'new-depot',component:NewDepotComponent,canActivate:[AuthGuard]},
  {path:'fournisseur',component:FournisseurComponent,canActivate:[AuthGuard]},
  {path:'new-fournisseur',component:NewFournisseurComponent,canActivate:[AuthGuard]},
  {path:'listbonAchatsfournisseur/:id',component:ListbonAchatsComponent,canActivate:[AuthGuard]},
  {path:'listavoirfournisseur/:id',component:ListAvoirComponent,canActivate:[AuthGuard]},
  {path:'charge',component:ChargeComponent,canActivate:[AuthGuard]},
  {path:'new-charge',component:NewChargeComponent,canActivate:[AuthGuard]},
  {path:'client',component:ClientComponent,canActivate:[AuthGuard]},
  {path:'new-client',component:AjouterClientComponent,canActivate:[AuthGuard]},
  {path:'liste-facture/:id',component:ListeFactureComponent,canActivate:[AuthGuard]},
  {path:'liste-avoir/:id',component:ListeAvoirComponent,canActivate:[AuthGuard]},
  {path:'bon-achat',component:BonAchatComponent,canActivate:[AuthGuard]},
  {path:'new-bon-achat',component:NewBonAchatComponent,canActivate:[AuthGuard]},
  {path:'paiement-fournisseur',component:PaiementFournisseurComponent,canActivate:[AuthGuard]},
  {path:'verser-fournisseur/:id',component:VerserFournisseurComponent,canActivate:[AuthGuard]},
  {path:'paiement-client',component:PaiementClientComponent,canActivate:[AuthGuard]},
  {path:'verser-client/:id',component:VerserClientComponent,canActivate:[AuthGuard]},
  {path:'alert',component:AlertComponent,canActivate:[AuthGuard]},
  {path:'statistique',component:StatistiqueComponent,canActivate:[AuthGuard]},
  {path:'vente-jour',component:VenteQuotidienneComponent,canActivate:[AuthGuard]},
  {path:'achat-jour',component:AchatQuotidienneComponent,canActivate:[AuthGuard]},
  {path:'charge-jour',component:ChargeQuotidienneComponent,canActivate:[AuthGuard]},
  {path:'vente-mois',component:VenteMensuelleComponent,canActivate:[AuthGuard]},
  {path:'achat-mois',component:AchatMensuelleComponent,canActivate:[AuthGuard]},
  {path:'charge-mois',component:ChargeMensuelleComponent,canActivate:[AuthGuard]},
  {path:'avoir-client',component:AvoirClientComponent,canActivate:[AuthGuard]},
  {path:'new-avoir-client',component:NewAvoirClientComponent,canActivate:[AuthGuard]},
  {path:'avoir-fournisseur',component:AvoirFournisseurComponent,canActivate:[AuthGuard]},
  {path:'new-avoir-fournisseur',component:NewAvoirFournisseurComponent,canActivate:[AuthGuard]},
  {path:'devis',component:DevisComponent,canActivate:[AuthGuard]},
  {path:'new-devis',component:AjouterDevisComponent,canActivate:[AuthGuard]},
  {path:'accueil',component:AccueilComponent,canActivate:[AuthGuard]},
  {path:'facture',component:FactureComponent,canActivate:[AuthGuard]},
  {path:'new-facture',component:NewFactureComponent,canActivate:[AuthGuard]},
  {path:'login',component:LoginComponent,canActivate:[LoginGuard]},
  {path:'',redirectTo:'login',pathMatch:'full'},
  {path:'liste-utilisateur',component:ListeUtilisateurComponent,canActivate:[RoleGuard]},
  {path:'new-utilisateur',component:NewUtilisateurComponent,canActivate:[RoleGuard]},
  {path:'statistique-utilisateur',component:StatistiqueUtilisateurComponent,canActivate:[RoleGuard]},

  {path:'**',redirectTo:'accueil',pathMatch:'full'}

];

@NgModule({
  declarations: [
    AppComponent,
    ProduitComponent,
    NewProduitComponent,
    DepotComponent,
    NewDepotComponent,
    FournisseurComponent,
    NewFournisseurComponent,
    ListbonAchatsComponent,
    ListAvoirComponent,
    ChargeComponent,
    NewChargeComponent,
    ClientComponent,
    AjouterClientComponent,
    ListeFactureComponent,
    ListeAvoirComponent,
    BonAchatComponent,
    NewBonAchatComponent,
    PaiementFournisseurComponent,
    VerserFournisseurComponent,
    PaiementClientComponent,
    VerserClientComponent,
    AlertComponent,
    StatistiqueComponent,
    VenteQuotidienneComponent,
    AchatQuotidienneComponent,
    ChargeQuotidienneComponent,
    VenteMensuelleComponent,
    AchatMensuelleComponent,
    ChargeMensuelleComponent,
    AvoirClientComponent,
    NewAvoirClientComponent,
    AvoirFournisseurComponent,
    NewAvoirFournisseurComponent,
    DevisComponent,
    AjouterDevisComponent,
    AccueilComponent,
    FactureComponent,
    NewFactureComponent,
    UtilisateurComponent,
    LoginComponent,
    SidebarComponent,
    NavbarComponent,
    FooterComponent,
    ListeUtilisateurComponent,
    NewUtilisateurComponent,
    StatistiqueUtilisateurComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MDBBootstrapModule.forRoot(),
    FormsModule,
    ReactiveFormsModule,
    RouterModule.forRoot(routes),
    ModalModule,
    HttpClientModule,
    ToastrModule.forRoot({
      progressBar: true,
      progressAnimation: "increasing",
      preventDuplicates: true,
      positionClass: "toast-bottom-right"
    })
  ],
  providers: [ModalService,ProduitService,FournisseurService,DepotService,BonAchatService,FactureService,DevisService,PaiementFournisseurService,PaiementClientService,AvoirFournisseurService,AvoirClientService,ClientService,ChargeService,StatisticService,DatePipe,AuthService,UtilisateurService,{provide:HTTP_INTERCEPTORS,useClass:AuthInterceptorService,multi:true}],
  bootstrap: [AppComponent]
})
export class AppModule { }
