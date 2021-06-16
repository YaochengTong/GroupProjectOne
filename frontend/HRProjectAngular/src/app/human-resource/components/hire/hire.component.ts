import { Component, OnInit } from '@angular/core';
import { HTTPReq } from 'src/app/service/HTTPReq/HTTPReq.service';
import {FormControl, FormGroupDirective, NgForm, Validators} from '@angular/forms';
import {ErrorStateMatcher} from '@angular/material/core';
import {
  MatSnackBar,
  MatSnackBarHorizontalPosition,
  MatSnackBarVerticalPosition,
} from '@angular/material/snack-bar';

export interface PeriodicElement {
  name: string;
  position: number;
  weight: number;
  symbol: string;
}

/** Error when invalid control is dirty, touched, or submitted. */
export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    //const isSubmitted = form && form.submitted;
    //return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
    return !!(control && control.invalid && (control.dirty ||control.touched));
  }
}

const ELEMENT_DATA: PeriodicElement[] = [
  {position: 1, name: 'Hydrogen', weight: 1.0079, symbol: 'H'},
  {position: 2, name: 'Helium', weight: 4.0026, symbol: 'He'},
  {position: 3, name: 'Lithium', weight: 6.941, symbol: 'Li'},
  {position: 4, name: 'Beryllium', weight: 9.0122, symbol: 'Be'},
  {position: 5, name: 'Boron', weight: 10.811, symbol: 'B'},
  {position: 6, name: 'Carbon', weight: 12.0107, symbol: 'C'},
  {position: 7, name: 'Nitrogen', weight: 14.0067, symbol: 'N'},
  {position: 8, name: 'Oxygen', weight: 15.9994, symbol: 'O'},
  {position: 9, name: 'Fluorine', weight: 18.9984, symbol: 'F'},
  {position: 10, name: 'Neon', weight: 20.1797, symbol: 'Ne'},
];


@Component({
  selector: 'app-hire',
  templateUrl: './hire.component.html',
  styleUrls: ['./hire.component.scss']
})

export class HireComponent implements OnInit{

  displayedColumns: string[] = ['position', 'name', 'weight', 'symbol'];
  dataSource = ELEMENT_DATA;

  constructor(private httpRequestService: HTTPReq, private _snackBar: MatSnackBar) {}

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
