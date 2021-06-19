import { Component, OnInit, Inject } from '@angular/core';
import { FormBuilder, FormControl, Validators } from '@angular/forms';
import {MAT_DIALOG_DATA} from '@angular/material/dialog';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-details-dialog',
  templateUrl: './details-dialog.component.html',
  styleUrls: ['./details-dialog.component.scss']
})
export class DetailsDialogComponent implements OnInit {

  applicationObj: any = {};

  email: string = "test@gmail.com";

  constructor(private fb: FormBuilder, @Inject(MAT_DIALOG_DATA) public data: {application: any}) { 
    this.applicationObj = data.application;
    let datePipe: DatePipe = new DatePipe('en-US');
    this.applicationObj.documents.forEach( item => {
      if(item.createDate){
        item.createDate = datePipe.transform(item.createDate, 'MM/dd/yyyy');
      }
    });
  }


  ngOnInit(): void {
  }

  personalInfoForm = this.fb.group({
    
  })

  contactInfoForm = this.fb.group({
    
  })

  fileForm = this.fb.group({
    
  })

}
