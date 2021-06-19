import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MessageService {

  constructor() { }

  message: any = {};
  messageObj$ = new BehaviorSubject<any>(this.message);
  
  sendMessage(msg: any) {  
      this.message = msg;
      this.messageObj$.next(this.message);
  }
}