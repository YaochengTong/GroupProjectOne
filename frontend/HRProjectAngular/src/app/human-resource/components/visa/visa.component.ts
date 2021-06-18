import { Component, OnInit } from '@angular/core';
import { HTTPReq } from 'src/app/service/HTTPReq/HTTPReq.service';
import { animate, state, style, transition, trigger } from '@angular/animations';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';


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
  visaForm!: FormGroup;
  submitted = false;

  map = {
    "Full Name": "fullName",
    "Work Authorization": "workAuthorization",
    'Expiration Date': "authorizationEndDate" ,
    "Day Left": "authorizationDayLeft",
  };

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
    private formBuilder: FormBuilder
  ) {}

  ngOnInit(): void {
    // this.httpRequestService.getData('/visa-status-management/all', null, 'http://localhost:8999').subscribe(
    //   (data: any) => {
    //     this.isDataAvailable = true;
    //     console.log(data.visaStatusInfoList);
    //     this.visaStatusInfo = data.visaStatusInfoList;
    //   }
    // )

    this.visaForm = this.formBuilder.group({
      name: [Validators.required],
      type: [Validators.required],
      startDate: [Validators.required],
      endDate: [Validators.required],
    });
  
  }

  
  get f() { return this.visaForm.controls; }

  onSave(event: any) {
    this.submitted = true
    console.log(event.currentTarget.id);
    this.isEdit = false;

    if (this.visaForm.invalid) { return; }

    let params = {
      name: this.f.name.value,
      type: this.f.type.value,
      startDate: this.f.startDate.value,
      endDate: this.f.endDate.value,
    }
    console.log(params);
  }


  // onSubmit(event: any) {
  //   this.isEdit = false;
  //   let index = event.currentTarget.id;
  //   this.visaStatusInfo[index].authorizationDayLeft = 0;
  //   let params = {
  //     userId: this.visaStatusInfo[index].userId,
  //     fullName: this.visaStatusInfo[index].fullName,
  //     workAuthorization: this.visaStatusInfo[index].workAuthorization,
  //     authorizationStartDate: this.visaStatusInfo[index].authorizationStartDate,
  //     authorizationEndDate: this.visaStatusInfo[index].authorizationEndDate
  //   }
  //   this.httpRequestService.postData('/visa-status-management/update', 
  //   params,
  //   'http://localhost:8999').subscribe(
  //     (data: any) => {
  //       console.log(data);
  //     }
  //   )
  // }


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
