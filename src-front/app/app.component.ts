import {Component, OnDestroy, OnInit} from '@angular/core';
import {ProduitService} from "../service/produit.service";
import {AuthService} from "../service/AuthService.service";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit,OnDestroy{
  isAuthenticated=false;
  private userSub:Subscription;

  constructor(public produitService:ProduitService,public authService:AuthService) {
  }
  ngOnInit(): void {
    this.authService.autoLogin();
    this.userSub=this.authService.user.subscribe(user=>{
      this.isAuthenticated=!!user;
    });

  }

  ngOnDestroy(): void {
   this.userSub.unsubscribe();
   }


}
