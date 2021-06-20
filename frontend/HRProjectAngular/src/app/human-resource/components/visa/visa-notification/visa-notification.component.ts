import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-visa-notification',
  templateUrl: './visa-notification.component.html',
  styleUrls: ['./visa-notification.component.scss']
})
export class VisaNotificationComponent implements OnInit {

  email:string = "";

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any
  ) { }

  ngOnInit(): void {
    this.email = this.data.email;
    console.log(this.data);
  }

}
