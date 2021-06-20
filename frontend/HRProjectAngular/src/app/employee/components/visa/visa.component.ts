import { Component, OnInit } from '@angular/core';
import { HTTPReq } from 'src/app/service/HTTPReq/HTTPReq.service';
import {MatSnackBar} from '@angular/material/snack-bar';

@Component({
  selector: 'app-visa',
  templateUrl: './visa.component.html',
  styleUrls: ['./visa.component.scss'],
})
export class VisaComponent implements OnInit {

  private userId = localStorage.getItem("userId");
  // public visaInfo: any = {};
  // public documents: any = {};
  public userName: string = "";
  public email: string = "";
  

  isDataAvailable: boolean = true;
  isShowMessage: boolean = true;
  messageNum: number = 0;

  constructor(
    private httpRequestService: HTTPReq,
    private snackBar: MatSnackBar
  ) {}

  ngOnInit(): void {

    let retrievedObject: any = localStorage.getItem('user');
    let user = JSON.parse(retrievedObject);
    this.userName = user.username;
    this.email = user.email;
          

    // if (this.userId != null) {
    //   this.httpRequestService.getData('/visa-status-management/' + this.userId, null, 'http://localhost:8999').subscribe(
    //     (data: any) => {
    //       console.log(data.visaStatusInfo);

    //       this.isDataAvailable = true;
    //       this.visaInfo = data.visaStatusInfo;
    //       this.documents = data.documentReceived;
    //       if (this.visaInfo.message != null) { this.isShowMessage = true; this.messageNum = 1;}
    //     }
    //   )
    // } else {
    //   console.log("user id is null");
    // }
    
  }

  openSnackBar(message: string, action: string) {
    this.snackBar.open(message, action);
  }

  visaInfo = {
    "fullName": "Bailey Bai",
    "workAuthorization": "F1(OPT/CPT)",
    "authorizationStartDate": "2021-06-14",
    "authorizationEndDate": "2021-09-23",
    "authorizationDayLeft": 95,
    "documentReceived": [
        {
            "name": "OPT EAD_2021-06-19",
            "date": "2021-06-19"
        },
        {
            "name": "OPT Receipt_2021-06-19",
            "date": "2021-06-19"
        }
    ],
    "nextStep": "I-983 for OPT STEM",
    "idx": 0,
    "userId": 558,
    "message": "Please download and fill your I-983 form",
    "currStep": "2"
}
  documents = this.visaInfo.documentReceived ;

}
