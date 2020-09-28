import { Component, OnInit } from '@angular/core';
import {DepotService} from "../../../service/depot.service";
import {NgForm} from "@angular/forms";

@Component({
  selector: 'app-new-depot',
  templateUrl: './new-depot.component.html',
  styleUrls: ['./new-depot.component.scss']
})
export class NewDepotComponent implements OnInit {
  confirmation:boolean;
  constructor(public depotService:DepotService) { }

  ngOnInit(): void {
  }
  onSaveDepot(dataForm,f:NgForm){
// f:NgForm on le recupere pour initialiser les champs
    console.log(dataForm);
    this.depotService.saveDepot(dataForm)
      .subscribe(data=>{
        console.log(data);
          this.confirmation=true;
          f.reset();
        },
        err => {
          this.confirmation = false;
          console.log(err);
        });

  }

}
