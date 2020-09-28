import { Component, OnInit } from '@angular/core';
import {ProduitService} from "../../service/produit.service";
import {AuthService} from "../../service/AuthService.service";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {
  nombre:any
  constructor(public produitService:ProduitService,public authService:AuthService) { }

  ngOnInit(): void {
    this.produitService.nombreAlerteProduit().subscribe(data=>{
      this.nombre=data;
    },error => {
      console.log(error);
    })

  }

  onLogout(){
    this.authService.logout();
  }
}
