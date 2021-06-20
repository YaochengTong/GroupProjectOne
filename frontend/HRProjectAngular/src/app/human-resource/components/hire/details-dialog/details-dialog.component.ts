import { Component, OnInit, Inject, OnDestroy } from '@angular/core';
import {MAT_DIALOG_DATA} from '@angular/material/dialog';
import { DatePipe } from '@angular/common';
import { HTTPReq } from 'src/app/service/HTTPReq/HTTPReq.service';
import {MatSnackBar} from '@angular/material/snack-bar';
import { MessageService } from 'src/app/service/Message/message.service';


@Component({
  selector: 'app-details-dialog',
  templateUrl: './details-dialog.component.html',
  styleUrls: ['./details-dialog.component.scss']
})
export class DetailsDialogComponent implements OnInit, OnDestroy {

  applicationObj: any = {};

  email: string = "test@gmail.com";

  constructor(@Inject(MAT_DIALOG_DATA) public data: {application: any, commentObj: any}, 
        private httpRequestService: HTTPReq, private _snackBar: MatSnackBar, private messageService: MessageService) { 
    this.applicationObj = data.application;
    //console.log(data.commentObj)
    //this.commentsObj = data.commentObj;
    let datePipe: DatePipe = new DatePipe('en-US');
    this.applicationObj.documents.forEach( item => {
      if(item.createDate){
        item.createDate = datePipe.transform(item.createDate, 'MM/dd/yyyy');
      }
      this.commentsObj.files.push({'comment': ''});
    });
    //console.log(this.commentsObj.EContacts[1])
  }

  ngOnDestroy(): void {
    //console.log(this.commentsObj)
    //this.messageSource.next(this.commentsObj);
  }

  commentsObj: any = {
    'FirstName': '',
    'LastName': '',
    'MiddleName': '',
    'SSN': '',
    'Gender': '',
    'DOB': '',
    'WorkAuth': '',
    'Citizen': '',
    'CitizenType': '',
    'AuthStart': '',
    'AuthEnd': '',
    'DriverLicense': '',
    'DriverLicenseExpireDate': '',
    'PrimaryPhone': '',
    'Car': '',
    'AlterPhone': '',
    'Address1': '',
    'Address2': '',
    'City': '',
    'State': '',
    'PostalCode': '',

    //reference contact
    'RFirstName': '',
    'RLastName': '',
    'RMiddleName': '',
    'RPhone': '',
    'RAddress1': '',
    'RAddress2': '',
    'RCity': '',
    'RState': '',
    'RPostalCode': '',
    'REmail': '',
    'RRelation': '',

    //emergency contacts
    'EContacts': [
      {
        'EFirstName': '',
        'ELastName': '',
        'EMiddleName': '',
        'EPhone': '',
        'EAddress1': '',
        'EAddress2': '',
        'ECity': '',
        'EState': '',
        'EPostalCode': '',
        'EEmail': '',
        'ERelation': '',
      },
      {
        'EFirstName': '',
        'ELastName': '',
        'EMiddleName': '',
        'EPhone': '',
        'EAddress1': '',
        'EAddress2': '',
        'ECity': '',
        'EState': '',
        'EPostalCode': '',
        'EEmail': '',
        'ERelation': '',
      }
    ],

    files: [

    ]

  }


  ngOnInit(): void {
  }

  //Approve
  onApprove(): void {
      if (confirm('Are you sure?')) {
        let comments = JSON.stringify(this.commentsObj)
        let params = {
          'comments': comments,
          'approve': 'yes',
          'id': this.applicationObj.applicationId,
          'user_id': this.applicationObj.userId,
          'email': this.applicationObj.email
        }
        this.httpRequestService.postData('/hire/auditOnboard', params).subscribe(
          (data: any) => {
            console.log(data);
            if(data.result == 'success'){
              this._snackBar.open("Application approved. Email has been sent to user's registration email address.", 
              'Got it!', {
                horizontalPosition: 'center',
                verticalPosition: 'top',
                duration: 5000,
              });
              this.messageService.sendMessage({"refreshNeeded": true});
            }
            else{
              this._snackBar.open('Ops, something went wrong here', 'Ok', {
                horizontalPosition: 'center',
                verticalPosition: 'top',
                duration: 5000,
              });
            }
          },(error) => {
            console.log(error);
        });
      } else {
        
      }
  }

  onReject(): void {
    //this.messageService.sendMessage(this.commentsObj);
    //this.messageService.sendMessage({"refreshNeeded": true});
    if (confirm('Are you sure?')) {
      let comments = JSON.stringify(this.commentsObj)
      let params = {
        'comments': comments,
        'approve': 'no',
        'id': this.applicationObj.applicationId,
        'email': this.applicationObj.email,
        'user_id': this.applicationObj.userId,
      }
      this.httpRequestService.postData('/hire/auditOnboard', params).subscribe(
        (data: any) => {
          console.log(data);
          if(data.result == 'success'){
            this._snackBar.open("Application rejected. Email has been sent to user's registration email address.", 
            'Got it!', {
              horizontalPosition: 'center',
              verticalPosition: 'top',
              duration: 5000,
            });
            this.messageService.sendMessage({"refreshNeeded": true});
          }
          else{
            this._snackBar.open('Ops, something went wrong here', 'Ok', {
              horizontalPosition: 'center',
              verticalPosition: 'top',
              duration: 5000,
            });
          }
        },(error) => {
          console.log(error);
      });
    } else {
      
    }
  }

}
