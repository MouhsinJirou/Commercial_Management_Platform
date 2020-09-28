import { Injectable } from '@angular/core';

@Injectable({ providedIn: 'root' })
export class ModalService {

  open(content){
    content.show();
  }

}
