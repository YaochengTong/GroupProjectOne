import { Component, OnInit } from '@angular/core';
import { HTTPReq } from 'src/app/service/HTTPReq/HTTPReq.service';
import {animate, state, style, transition, trigger} from '@angular/animations';

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
  // visaStatusInfo: any = {};
  columnsToDisplay = ['Full Name', 'Work Authorization', 'Expiration Date', 'Day Left'];
  expandedElement!: VisaStatusElement | null;
  map = {
    "Full Name": "fullName",
    "Work Authorization": "workAuthorization",
    'Expiration Date': "authorizationEndDate" ,
    "Day Left": "authorizationDayLeft",
  }

  visaStatusInfo = [
    {
        "fullName": "David Luo",
        "workAuthorization": "F1(OPT/CPT)",
        "authorizationStartDate": "2020-12-02T21:00:00.000+00:00",
        "authorizationEndDate": "2022-09-23T16:50:31.000+00:00",
        "authorizationDayLeft": 463,
        "documentReceived": [
            "I-983.txt",
            "OPT_EAD.txt",
            "OPT_Receipt.txt"
        ],
        "nextStep": "I-20 after I-983 Submitted"
    },
    {
        "fullName": "Admin Admin",
        "workAuthorization": "F1(OPT/CPT)",
        "authorizationStartDate": "2021-06-14T16:50:29.000+00:00",
        "authorizationEndDate": "2021-12-23T17:50:31.000+00:00",
        "authorizationDayLeft": 189,
        "documentReceived": [
            "I-20.txt",
            "I-983.txt",
            "OPT_EAD.txt",
            "OPT_Receipt.txt",
            "OPT_STEM_EAD.txt",
            "OPT_STEM_Receipt.txt"
        ],
        "nextStep": "No Further Action Needed"
    },
    {
        "fullName": "Ricard  Huang",
        "workAuthorization": "F1(OPT/CPT)",
        "authorizationStartDate": "2021-06-14T16:50:29.000+00:00",
        "authorizationEndDate": "2021-09-23T16:50:31.000+00:00",
        "authorizationDayLeft": 98,
        "documentReceived": [
            "I-20.txt",
            "I-983.txt",
            "OPT_EAD.txt",
            "OPT_Receipt.txt"
        ],
        "nextStep": "OPT STEM Receipt"
    },
    {
        "fullName": "Bailey Bai",
        "workAuthorization": "F1(OPT/CPT)",
        "authorizationStartDate": "2021-06-14T16:50:29.000+00:00",
        "authorizationEndDate": "2021-09-23T16:50:31.000+00:00",
        "authorizationDayLeft": 98,
        "documentReceived": [
            "OPT_EAD.txt",
            "OPT_Receipt.txt"
        ],
        "nextStep": "I-983 for OPT STEM"
    }
]

  constructor(
    private httpRequestService: HTTPReq
  ) {}

  ngOnInit(): void {
    this.httpRequestService.getData('/visaStatusManagement/all', null, 'http://localhost:8999').subscribe(
      (data: any) => {
        this.isDataAvailable = true;
        this.visaStatusInfo = data.visaStatusInfoList;
        console.log(this.visaStatusInfo.length);
      }
    )
    if (this.isDataAvailable) console.log(this.visaStatusInfo);
    console.log(1);
  }


}

export interface VisaStatusElement {
  fullName: string;
  workAuthorization: string;
  authorizationStartDate: string;
  authorizationEndDate: string;
  authorizationDayLeft: number;
  documentReceived: string[];
  nextStep: string;
}
