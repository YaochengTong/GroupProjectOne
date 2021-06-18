import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
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

  isDataAvailable: boolean = true;
  isEdit: boolean = false;
  // visaStatusInfo: any = {};
  columnsToDisplay = ['Full Name', 'Work Authorization', 'Expiration Date', 'Day Left'];
  expandedElement!: VisaStatusElement | null;
  map = {
    "Full Name": "fullName",
    "Work Authorization": "workAuthorization",
    'Expiration Date': "authorizationEndDate" ,
    "Day Left": "authorizationDayLeft",
  }
  ;

  visaStatusInfo = [
    {
        "fullName": "David Luo",
        "workAuthorization": "F1(OPT/CPT)",
        "authorizationStartDate": "2020-12-02",
        "authorizationEndDate": "2022-09-23",
        "authorizationDayLeft": 462,
        "documentReceived": [
            "I-983.txt",
            "OPT_EAD.txt",
            "OPT_Receipt.txt"
        ],
        "nextStep": "I-20 after I-983 Submitted",
        "idx": 0,
        "userId": 89
    },
    {
        "fullName": "Admin Admin",
        "workAuthorization": "F1(OPT/CPT)",
        "authorizationStartDate": "2021-06-14",
        "authorizationEndDate": "2021-12-23",
        "authorizationDayLeft": 188,
        "documentReceived": [
            "I-20.txt",
            "I-983.txt",
            "OPT_EAD.txt",
            "OPT_Receipt.txt",
            "OPT_STEM_EAD.txt",
            "OPT_STEM_Receipt.txt"
        ],
        "nextStep": "No Further Action Needed",
        "idx": 1,
        "userId": 556
    },
    {
        "fullName": "Ricard  Huang",
        "workAuthorization": "F1(OPT/CPT)",
        "authorizationStartDate": "2021-06-14",
        "authorizationEndDate": "2021-09-23",
        "authorizationDayLeft": 97,
        "documentReceived": [
            "I-20.txt",
            "I-983.txt",
            "OPT_EAD.txt",
            "OPT_Receipt.txt"
        ],
        "nextStep": "OPT STEM Receipt",
        "idx": 2,
        "userId": 557
    },
    {
        "fullName": "Bailey Bai",
        "workAuthorization": "F1(OPT/CPT)",
        "authorizationStartDate": "2021-06-14",
        "authorizationEndDate": "2021-09-23",
        "authorizationDayLeft": 97,
        "documentReceived": [
            "OPT_EAD.txt",
            "OPT_Receipt.txt"
        ],
        "nextStep": "I-983 for OPT STEM",
        "idx": 3,
        "userId": 558
    }
]
  constructor(
    private httpRequestService: HTTPReq,
  ) {}

  ngOnInit(): void {
    // this.httpRequestService.getData('/visaStatusManagement/all', null, 'http://localhost:8999').subscribe(
    //   (data: any) => {
    //     this.isDataAvailable = true;
    //     this.visaStatusInfo = data.visaStatusInfoList;
    //   }
    // )
  }

  @ViewChild('fullName') fullName!: ElementRef;
  @ViewChild('type') type!: ElementRef;
  @ViewChild('endDate') endDate!: ElementRef;
  @ViewChild('startDate') startDate!: ElementRef;

  onSubmit() {
    this.isEdit = false;
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
  idx: number;
  userId: number
}
