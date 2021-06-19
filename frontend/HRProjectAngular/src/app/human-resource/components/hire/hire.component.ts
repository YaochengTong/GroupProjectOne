import { Component, OnInit } from '@angular/core';
import { HTTPReq } from 'src/app/service/HTTPReq/HTTPReq.service';
import {FormControl, FormGroupDirective, NgForm, Validators} from '@angular/forms';
import {ErrorStateMatcher} from '@angular/material/core';
import {MatSnackBar} from '@angular/material/snack-bar';
import {MatDialog} from '@angular/material/dialog';
import { DetailsDialogComponent } from './details-dialog/details-dialog.component';

import { DatePipe } from '@angular/common';

/** Error when invalid control is dirty, touched, or submitted. */
export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    //const isSubmitted = form && form.submitted;
    //return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
    return !!(control && control.invalid && (control.dirty ||control.touched));
  }
}

@Component({
  selector: 'app-hire',
  templateUrl: './hire.component.html',
  styleUrls: ['./hire.component.scss']
})

export class HireComponent implements OnInit{

  displayedColumns: string[] = [
    'itemIndex', 'firstName', 'lastName', 'visaType', 'visaStartDate', 'visaEndDate', 'getdetails'
  ];
  
  dataSource: any = [];

  constructor(private httpRequestService: HTTPReq, private _snackBar: MatSnackBar, public dialog: MatDialog) {}

  currentHRUserId: number = -1;

  emailFormControl = new FormControl('', [
    Validators.required,
    Validators.email,
  ]);

  matcher = new MyErrorStateMatcher();

  ngOnInit(): void {
    let retrievedObject: any = localStorage.getItem('user');
    let user = JSON.parse(retrievedObject);
    //console.log(user)
    this.currentHRUserId = user.id;
    //this.username = user.username;

    this.httpRequestService.getData('/hire/getOnboardApplications', {}).subscribe(
      (data: any) => {
        console.log(data)
        let datePipe: DatePipe = new DatePipe('en-US');
        this.dataSource = data.OngoingApplications;
        this.dataSource.forEach( item => {
          if(item.DateOfBirth){
            item.DateOfBirth = datePipe.transform(item.DateOfBirth, 'MM/dd/yyyy');
          }

          if(item.DriverLicenseExpireDate){
            item.DriverLicenseExpireDate = datePipe.transform(item.DriverLicenseExpireDate, 'MM/dd/yyyy');
          }

          if(item.visaEndDate){
            item.visaEndDate = datePipe.transform(item.visaEndDate, 'MM/dd/yyyy');
          }

          if(item.visaStartDate){
            item.visaStartDate = datePipe.transform(item.visaStartDate, 'MM/dd/yyyy');
          }
        });
      }
    );

  }

  getRecord(name: any): void {
    //DetailsDialogComponent
    const dialogRef = this.dialog.open(DetailsDialogComponent);

    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });
  }

  generateToken(): void {
  
    if (confirm('Are you sure you want to generate a token?')) {
      let params = {
        user_id: this.currentHRUserId,
        email: this.emailFormControl.value
      }
    
      if(this.currentHRUserId == -1 || this.emailFormControl.status == 'INVALID'){
        this._snackBar.open('Invalid input!', 'Retry', {
          horizontalPosition: 'center',
          verticalPosition: 'top',
          duration: 2000,
        });
        return;
      }

      this.httpRequestService.postData('/hire/generateToken', params).subscribe(
        (data: any) => {
          console.log(data);
          if(data.result == 'success'){
            this._snackBar.open('Email containing token has been sent out.', 'Got it!', {
              horizontalPosition: 'center',
              verticalPosition: 'top',
              duration: 5000,
            });
            this.emailFormControl.reset();
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
