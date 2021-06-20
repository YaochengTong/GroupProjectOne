import { Component, OnInit } from '@angular/core';
import { HTTPReq } from 'src/app/service/HTTPReq/HTTPReq.service';
import {MatSnackBar} from '@angular/material/snack-bar';
import { ThemePalette } from '@angular/material/core';
import { MaxSizeValidator } from '@angular-material-components/file-input';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';


@Component({
  selector: 'app-visa',
  templateUrl: './visa.component.html',
  styleUrls: ['./visa.component.scss'],
})
export class VisaComponent implements OnInit {

  public userId = localStorage.getItem("userId");
  public visaInfo: any = {};
  public documents: any = {};
  public userName: string = "";
  public email: string = "";
  public params:any = {};
  

  isDataAvailable: boolean = false;
  isShowMessage: boolean = false;
  isSubmitted: boolean = false;
  messageNum: number = 0;


   //File upload variables
   color: ThemePalette = 'primary';
   multiple: boolean = false;
   fileI983!: FormControl;
   fileOPTEAD!: FormControl;
   fileI20!: FormControl;
   fileOPTSTEMReceipt!: FormControl;
   fileOPTSTEMEAD!: FormControl;
   files: File | undefined;

  constructor(
    private httpRequestService: HTTPReq,
    private snackBar: MatSnackBar
  ) {
    this.fileI983 = new FormControl(this.files, [
      Validators.required,
      MaxSizeValidator(1024 * 1024),
    ]);

    this.fileOPTEAD = new FormControl(this.files, [
      Validators.required,
      MaxSizeValidator(1024 * 1024),
    ]);

    this.fileI20 = new FormControl(this.files, [
      Validators.required,
      MaxSizeValidator(1024 * 1024),
    ]);

    this.fileOPTSTEMReceipt = new FormControl(this.files, [
      Validators.required,
      MaxSizeValidator(1024 * 1024),
    ]);

    this.fileOPTSTEMEAD = new FormControl(this.files, [
      Validators.required,
      MaxSizeValidator(1024 * 1024),
    ]);


  }

  ngOnInit(): void {

    let retrievedObject: any = localStorage.getItem('user');
    let user = JSON.parse(retrievedObject);
    this.userName = user.username;
    this.email = user.email;
          

    if (this.userId != null) {
      this.httpRequestService.getData('/visa-status-management/' + this.userId, null, 'http://localhost:8999').subscribe(
        (data: any) => {
          console.log(data.visaStatusInfo);

          this.isDataAvailable = true;
          this.visaInfo = data.visaStatusInfo;
          this.documents = this.visaInfo.documentReceived;
          if (this.visaInfo.message != null) { this.messageNum = 1;}
        }
      )
    } else {
      console.log("user id is null");
    }
    
  }

  openSnackBar(message: string, action: string) {
    this.snackBar.open(message, action);
  }

  onSubmit(event: any): void {
    let idx:string = event.currentTarget.id;
    var formData: FormData = new FormData();
    if (idx == "1") {
      // uploaded OPT EAD
      let file: File = this.fileOPTEAD.value;
      formData.append('file', file);
      this.params = {
        step: "OPT EAD",
        title:"OPTEAD"
      }
      console.log(formData);

    }
    else if (idx == "2") {
      // uploaded I-983 filled
       // uploaded OPT EAD
       console.log("in");
       let file: File = this.fileI983.value;
       console.log(file);
       formData.append('file', this.fileI983.value);
       this.params = {
         step: "I-983 Filled",
         title:"I983"
       }
       console.log(JSON.stringify(formData));
       formData.forEach((value, key) => {
        console.log(key + " " + value)
      });
 
    }
    else if (idx == "4") {
      // uploaded i-20
    }
    else if (idx == "5") {
      // upload EAD STEM receipt
    }
    else if (idx == "6") {
      // upload EAD STEM EAD
    }
    console.log(formData);
    
    this.httpRequestService.fileUploadWithParams('/visa-status-management/' + this.userId + '/upload', formData, this.params).subscribe(
      (data: any) => {
        console.log(data);
        if (data.success == true) { this.isSubmitted = true; }
      },
      err => {
        console.log(err);
        this.isSubmitted = false;
      }
    );
  }

//   visaInfo = {
//     "fullName": "Bailey Bai",
//     "workAuthorization": "F1(OPT/CPT)",
//     "authorizationStartDate": "2021-06-14",
//     "authorizationEndDate": "2021-09-23",
//     "authorizationDayLeft": 95,
//     "documentReceived": [
//         {
//             "name": "OPT EAD_2021-06-19",
//             "date": "2021-06-19"
//         },
//         {
//             "name": "OPT Receipt_2021-06-19",
//             "date": "2021-06-19"
//         }
//     ],
//     "nextStep": "I-983 for OPT STEM",
//     "idx": 0,
//     "userId": 558,
//     "message": "Please download and fill your I-983 form",
//     "currStep": "2"
// }

}
