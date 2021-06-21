import { Component, Input, OnInit, Output } from '@angular/core';
import { HTTPReq } from 'src/app/service/HTTPReq/HTTPReq.service';
import { animate, state, style, transition, trigger } from '@angular/animations';
import { MatSelectChange } from '@angular/material/select';
import * as moment from 'moment';
import { MatDialog } from '@angular/material/dialog';
import { VisaNotificationComponent } from './visa-notification/visa-notification.component';
import {MatDatepickerInputEvent} from '@angular/material/datepicker';
import { DateAdapter } from '@angular/material/core';
import {FormControl} from '@angular/forms';

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
        console.log("Original data");
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




  formatHandler(oriDate:any): string {
    if (oriDate == null) {
      return "";
    }

    // console.log("Substring");
    console.log(oriDate.toString().substring(4, 15));
    var formattedDate;
    var month = oriDate.toString().substring(4, 15).split(" ")[0];
    switch(month) {
      case "JAN":
        month = "01";
        break;
      case "FEB":
        month = "02";
        break;
      case "MAR":
        month = "03";
        break;
      case "APR":
        month = "04";
        break;
      case "MAY":
        month = "05";
        break;
      case "JUN":
        month = "06";
        break;
      case "JUL":
        month = "07";
        break;
      case "AUG":
        month = "08";
        break;
      case "SEP":
        month = "09";
        break;
      case "OCT":
        month = "10";
        break;
      case "NOV":
        month = "11";
        break;
      default:
        month = "12";
        break;
    }

    
    formattedDate = oriDate.toString().substring(4, 15).split(" ")[2] + "-" + month + "-" + oriDate.toString().substring(4, 15).split(" ")[1];
    return formattedDate;
  }


  addEndDateEvent(index: number, event: MatDatepickerInputEvent<Date>) {
    this.visaStatusInfo[index].authorizationEndDate = this.formatHandler(event.value);
    // console.log("after formate")
    console.log(this.visaStatusInfo[index].authorizationEndDate);
    // console.log(this.visaStatusInfo[index].authorizationEndDate);
  }

  addStartDateEvent(index: number,event: MatDatepickerInputEvent<Date>) {
    this.visaStatusInfo[index].authorizationStartDate = this.formatHandler(event.value);
  }

  
  onSubmit(event: any) {
    this.isSubmitted = true;

    let index = event.currentTarget.id;
    this.visaStatusInfo[index].authorizationDayLeft = 0;

    var nameModifid = (<HTMLInputElement>document.getElementById("name-"+index)).value;
    // var startDateModifid = (<HTMLInputElement>document.getElementById("startDate-"+index)).value;
    // var endDateModifid = (<HTMLInputElement>document.getElementById("endDate-"+index)).value;

    var startDateModifid = this.visaStatusInfo[index].authorizationStartDate;
    var endDateModifid = this.visaStatusInfo[index].authorizationEndDate;

    console.log("hello");
    console.log(typeModifid);
    console.log(startDateModifid);
    console.log(endDateModifid);

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
      authorizationStartDate: startDateModifid,
      authorizationEndDate: endDateModifid,
    }

    console.log("update INFO")
    console.log(params);
    
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
  //       {
  //         "fullName": "ADMIN SUP",
  //         "workAuthorization": "H1-B",
  //         "authorizationStartDate": "2018-12-09",
  //         "authorizationEndDate": "2030-12-05",
  //         "authorizationDayLeft": 3455,
  //         "documentReceived": [
  //             {
  //                 "name": "I-20_2021-06-19",
  //                 "date": "2021-06-19"
  //             },
  //             {
  //                 "name": "I-983 Filled_2021-06-19",
  //                 "date": "2021-06-19"
  //             },
  //             {
  //                 "name": "I-983 Signed_2021-06-19",
  //                 "date": "2021-06-19"
  //             },
  //             {
  //                 "name": "OPT EAD_2021-06-19",
  //                 "date": "2021-06-19"
  //             },
  //             {
  //                 "name": "OPT Receipt_2021-06-19",
  //                 "date": "2021-06-19"
  //             },
  //             {
  //                 "name": "OPT STEM EAD_2021-06-19",
  //                 "date": "2021-06-19"
  //             },
  //             {
  //                 "name": "OPT STEM Receipt_2021-06-19",
  //                 "date": "2021-06-19"
  //             }
  //         ],
  //         "nextStep": "No Further Action Needed",
  //         "idx": 0,
  //         "userId": 556,
  //         "message": null
  //     },
  //     {
  //         "fullName": "Ricard  Huang",
  //         "workAuthorization": "F1(OPT/CPT)",
  //         "authorizationStartDate": "2021-06-14",
  //         "authorizationEndDate": "2021-09-23",
  //         "authorizationDayLeft": 95,
  //         "documentReceived": [
  //             {
  //                 "name": "I-983 Filled_2021-06-19",
  //                 "date": "2021-06-19"
  //             },
  //             {
  //                 "name": "OPT EAD_2021-06-19",
  //                 "date": "2021-06-19"
  //             },
  //             {
  //                 "name": "OPT Receipt_2021-06-19",
  //                 "date": "2021-06-19"
  //             }
  //         ],
  //         "nextStep": "OPT STEM Receipt",
  //         "idx": 1,
  //         "userId": 557,
  //         "message": "Please upload your OPT STEM Receipt"
  //     },
  //     {
  //         "fullName": "Bailey Bai",
  //         "workAuthorization": "F1(OPT/CPT)",
  //         "authorizationStartDate": "2021-06-14",
  //         "authorizationEndDate": "2021-09-23",
  //         "authorizationDayLeft": 95,
  //         "documentReceived": [
  //             {
  //                 "name": "OPT EAD_2021-06-19",
  //                 "date": "2021-06-19"
  //             },
  //             {
  //                 "name": "OPT Receipt_2021-06-19",
  //                 "date": "2021-06-19"
  //             }
  //         ],
  //         "nextStep": "I-983 for OPT STEM",
  //         "idx": 2,
  //         "userId": 558,
  //         "message": "Please download and fill your I-983 form"
  //     },
  //     {
  //         "fullName": "David Li",
  //         "workAuthorization": "F1(OPT/CPT)",
  //         "authorizationStartDate": "2020-12-02",
  //         "authorizationEndDate": "2022-09-23",
  //         "authorizationDayLeft": 460,
  //         "documentReceived": [
  //             {
  //                 "name": "I-983 Filled_2021-06-19",
  //                 "date": "2021-06-19"
  //             },
  //             {
  //                 "name": "I-983 Signed_2021-06-19",
  //                 "date": "2021-06-19"
  //             },
  //             {
  //                 "name": "OPT EAD_2021-06-19",
  //                 "date": "2021-06-19"
  //             },
  //             {
  //                 "name": "OPT Receipt_2021-06-19",
  //                 "date": "2021-06-19"
  //             }
  //         ],
  //         "nextStep": null,
  //         "idx": 3,
  //         "userId": 89,
  //         "message": null
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
