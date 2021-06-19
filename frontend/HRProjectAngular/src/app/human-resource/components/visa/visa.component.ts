import { Component, Input, OnInit, Output } from '@angular/core';
import { HTTPReq } from 'src/app/service/HTTPReq/HTTPReq.service';
import { animate, state, style, transition, trigger } from '@angular/animations';
import { MatSelectChange } from '@angular/material/select';
import * as moment from 'moment';
import { MatDialog } from '@angular/material/dialog';
import { VisaNotificationComponent } from './visa-notification/visa-notification.component';

@Component({
  selector: 'app-visa',
  templateUrl: './visa.component.html',
  styleUrls: ['./visa.component.scss'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({height: '0px', minHeight: '0'})),
      state('expanded', style({height: '*'})),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ],
})

export class VisaComponent implements OnInit {

  isDataAvailable: boolean = false;
  isEdit: boolean = false;
  isNameValid: boolean = true;
  isTypeValid: boolean = true;
  isStartDateValid: boolean = true;
  isEndDateValid: boolean = true;
  isSubmitted = false;

  visaStatusInfo: any = {};
  expandedElement!: VisaStatusElement | null;
  
  selectedData: any = null;
  tempData: any = {};
  message: string = "";
  employeeEmail: string = "";

  columnsToDisplay = ['Full Name', 'Work Authorization', 'Expiration Date', 'Day Left'];

  
  constructor(
    private httpRequestService: HTTPReq,
    public dialog: MatDialog
  ) {}

  ngOnInit(): void {
    this.httpRequestService.getData('/visa-status-management/all', null, 'http://localhost:8999').subscribe(
      (data: any) => {
        this.isDataAvailable = true;
        console.log(data.visaStatusInfoList);
        this.visaStatusInfo = data.visaStatusInfoList;
      }
    )

  
  }

  onEdit(event: any) {
    this.isEdit = true;
    let index = event.currentTarget.id;
    this.tempData = this.visaStatusInfo[index];
    console.log(this.tempData);
  }

  onCancel(event: any) {
    this.isEdit = false;
    this.isSubmitted = false;
    let index = event.currentTarget.id;
    this.visaStatusInfo[index] = this.tempData;
    console.log(index);
  }

  select(event: MatSelectChange) {
    this.selectedData = {
      value: event.value,
      text: event.source.triggerValue
    };
  }

  sendNotification(event: any) {
    
    let index = event.currentTarget.id;
    this.message = this.visaStatusInfo[index].nextStep;
    let params = {
      userId: this.visaStatusInfo[index].userId,
      message: this.message
    }
    this.httpRequestService.postData('/visa-status-management/send-notification', 
    params,
    'http://localhost:8999').subscribe(
      (data: any) => {
        console.log(data);
        if (data.success == true) {
          this.employeeEmail = data.email;
          this.dialog.open(VisaNotificationComponent, {
            data: {
              email: this.employeeEmail
            }
          });
        }
      }
    )

  }

  onSubmit(event: any) {
    this.isSubmitted = true;

    let index = event.currentTarget.id;
    this.visaStatusInfo[index].authorizationDayLeft = 0;

    var nameModifid = (<HTMLInputElement>document.getElementById("name-"+index)).value;
    var startDateModifid = (<HTMLInputElement>document.getElementById("startDate-"+index)).value;
    var endDateModifid = (<HTMLInputElement>document.getElementById("endDate-"+index)).value;


    if (nameModifid == "" || this.selectedData == null || startDateModifid == "" || endDateModifid == "") {
      if (nameModifid == "") { this.isNameValid = false;  console.log("name null")}
      if (this.selectedData == null) { this.isTypeValid = false; }
      if (startDateModifid == "" || !moment(startDateModifid.split("-").toString())) {this.isStartDateValid = false; }
      if (endDateModifid == "" || !moment(endDateModifid.split("-").toString())) { this.isEndDateValid = false; }
      return;
    }

    this.isNameValid = true;
    this.isTypeValid = true;
    this.isStartDateValid = true;
    this.isEndDateValid = true;
    this.isSubmitted = false;

    var typeModifid = this.selectedData.value;

    console.log(typeModifid);
    console.log(startDateModifid);
    console.log(endDateModifid);

    this.isEdit = false;

    // update page data
    this.visaStatusInfo[index].fullName = nameModifid;
    this.visaStatusInfo[index].workAuthorization = typeModifid;
    this.visaStatusInfo[index].authorizationStartDate = startDateModifid;
    this.visaStatusInfo[index].authorizationEndDate = endDateModifid;
    
    // parse to backend
    let params = {
      userId: this.visaStatusInfo[index].userId,
      fullName: nameModifid,
      workAuthorization: typeModifid,
      authorizationStartDate: startDateModifid+"T00:00:00.000+00:00",
      authorizationEndDate: endDateModifid+"T00:00:00.000+00:00",
    }
    this.httpRequestService.postData('/visa-status-management/update', 
    params,
    'http://localhost:8999').subscribe(
      (data: any) => {
        console.log(data);
      }
    )
  }

  map = {
    "Full Name": "fullName",
    "Work Authorization": "workAuthorization",
    'Expiration Date': "authorizationEndDate" ,
    "Day Left": "authorizationDayLeft",
  };

//   visaStatusInfo = [
//     {
//         "fullName": "David Luo",
//         "workAuthorization": "F1(OPT/CPT)",
//         "authorizationStartDate": "2020-12-02",
//         "authorizationEndDate": "2022-09-23",
//         "authorizationDayLeft": 462,
//         "documentReceived": [
//             "I-983.txt",
//             "OPT_EAD.txt",
//             "OPT_Receipt.txt"
//         ],
//         "nextStep": "I-20 after I-983 isSubmitted",
//         "idx": 0,
//         "userId": 89
//     },
//     {
//         "fullName": "Admin Admin",
//         "workAuthorization": "F1(OPT/CPT)",
//         "authorizationStartDate": "2021-06-14",
//         "authorizationEndDate": "2021-12-23",
//         "authorizationDayLeft": 188,
//         "documentReceived": [
//             "I-20.txt",
//             "I-983.txt",
//             "OPT_EAD.txt",
//             "OPT_Receipt.txt",
//             "OPT_STEM_EAD.txt",
//             "OPT_STEM_Receipt.txt"
//         ],
//         "nextStep": "No Further Action Needed",
//         "idx": 1,
//         "userId": 556
//     },
//     {
//         "fullName": "Ricard  Huang",
//         "workAuthorization": "F1(OPT/CPT)",
//         "authorizationStartDate": "2021-06-14",
//         "authorizationEndDate": "2021-09-23",
//         "authorizationDayLeft": 97,
//         "documentReceived": [
//             "I-20.txt",
//             "I-983.txt",
//             "OPT_EAD.txt",
//             "OPT_Receipt.txt"
//         ],
//         "nextStep": "OPT STEM Receipt",
//         "idx": 2,
//         "userId": 557
//     },
//     {
//         "fullName": "Bailey Bai",
//         "workAuthorization": "F1(OPT/CPT)",
//         "authorizationStartDate": "2021-06-14",
//         "authorizationEndDate": "2021-09-23",
//         "authorizationDayLeft": 97,
//         "documentReceived": [
//             "OPT_EAD.txt",
//             "OPT_Receipt.txt"
//         ],
//         "nextStep": "I-983 for OPT STEM",
//         "idx": 3,
//         "userId": 558
//     }
// ]

}

export interface VisaStatusElement {
  fullName: string;
  workAuthorization: string;
  authorizationStartDate: string;
  authorizationEndDate: string;
  authorizationDayLeft: number;
  documentReceived: string[];
  nextStep: string;
  idx: number;
  userId: number
}
