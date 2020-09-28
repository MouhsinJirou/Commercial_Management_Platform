import { Component, OnInit } from '@angular/core';
import {AuthService} from "../../service/AuthService.service";

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent implements OnInit {
  admin:boolean;

  constructor(public authService:AuthService) { }

  ngOnInit(): void {
   this.admin= this.authService.user.getValue().isAdmin
  }

}
