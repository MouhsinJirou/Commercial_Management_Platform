import { Component, OnInit } from '@angular/core';
import {NgForm} from "@angular/forms";
import {ClientService} from "../../../service/client.service";

@Component({
  selector: 'app-ajouter-client',
  templateUrl: './ajouter-client.component.html',
  styleUrls: ['./ajouter-client.component.scss']
})
export class AjouterClientComponent implements OnInit {

  confirmation:boolean;
  constructor(public clientService:ClientService) { }

  ngOnInit(): void {
  }
  onSaveClient(dataForm,f:NgForm){

    console.log(dataForm);
    this.clientService.saveClient(dataForm)
      .subscribe(data=>{
          this.confirmation=true;
          f.reset();
        },
        err => {
          this.confirmation = false;
          console.log(err);
        });

  }
}
