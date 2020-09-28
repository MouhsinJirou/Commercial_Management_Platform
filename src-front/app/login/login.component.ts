import { Component, OnInit } from '@angular/core';
import {AuthService} from "../../service/AuthService.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  mode:number=0;

  constructor(private authService:AuthService,public router:Router) { }

  ngOnInit(): void {
  }
  onLogin(dataForm){
    this.authService.login(dataForm).subscribe(data=>{
      let jwt=data.headers.get('Authorization');
      this.authService.saveToken(jwt);
      this.router.navigateByUrl("/accueil");
    },error => {
      console.log(error);
      this.mode=1
    })
  }
}
