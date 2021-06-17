import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDatepicker } from '@angular/material/datepicker';
import { ThemePalette } from '@angular/material/core';
import { MaxSizeValidator } from '@angular-material-components/file-input';
import { HTTPReq } from 'src/app/service/HTTPReq/HTTPReq.service';
import { states } from '../shared/constants';
import {STEPPER_GLOBAL_OPTIONS} from '@angular/cdk/stepper';

@Component({
  selector: 'app-on-boarding',
  templateUrl: './on-boarding.component.html',
  styleUrls: ['./on-boarding.component.scss'],
  providers: [{
    provide: STEPPER_GLOBAL_OPTIONS, useValue: {showError: true}
  }]
})
export class OnBoardingComponent implements OnInit{

  constructor(private fb: FormBuilder, private httpRequestService: HTTPReq) {

    this.fileI983 = new FormControl(this.files, [
      Validators.required,
      MaxSizeValidator(1024 * 1024),
    ]);

    this.fileOPTReceipt = new FormControl(this.files, [
      Validators.required,
      MaxSizeValidator(1024 * 1024),
    ]);

    this.fileWorkAuth = new FormControl(this.files, [
      Validators.required,
      MaxSizeValidator(1024 * 1024),
    ]);

    this.fileDriverLicense = new FormControl(this.files, [
      Validators.required,
      MaxSizeValidator(1024 * 1024),
    ]);
  }

  ngOnInit(): void {
    
  }

  date = new FormControl(this.getMonthYearString(new Date()));
  isCitizen: boolean | undefined = true;
  hasDriverLicense: boolean | undefined = false;
  authorizationSelection: boolean | undefined;

  //template values
  emergencyContact: string = "Emergency Contact";
  referenceContact: string = "Reference Contact";
  showSecondEmergencyContact: boolean = false;
  email:string = 'test@gmail.com';

  //File upload variables
  color: ThemePalette = 'primary';
  accept: string | undefined;
  multiple: boolean = false;
  fileI983: FormControl;
  fileOPTReceipt: FormControl;
  fileWorkAuth: FormControl;
  fileDriverLicense: FormControl;
  files: File | undefined;
  hasUnitNumber = false;


  phoneAddressCarForm = this.fb.group({
    address: ['', Validators.required],
    address2: '',
    city: ['', Validators.required],
    state: ['', Validators.required],
    postalCode: [
      '',
      Validators.compose([
        Validators.required,
        Validators.minLength(5),
        Validators.maxLength(5),
      ]),
    ],
    cellPhone: ['', Validators.required],
    workPhone: '',
    carMaker: '',
    carModel: '',
    carColor: ''
  });

  states: any[] = states;

  personalInfoFormValue: any = {};
  dataRefreshForPersonalInfo(data: any): void{
    this.personalInfoFormValue = data;
  };

  referenceFormValue: any = {};
  dataRefreshForReference(data: any): void{
    this.referenceFormValue = data;
  };

  emergency1FormValue: any = {};
  dataRefreshForEmergency1(data: any): void{
    this.emergency1FormValue = data;
  }

  emergency2FormValue: any = {};
  dataRefreshForEmergency2(data: any): void{
    this.emergency2FormValue = data;
  }

  dataRefreshDriverLicense(data: any): void{
    this.fileDriverLicense = data;
  }

  dataRefreshWorkAuth(data: any): void{
    //console.log(data)
    this.fileWorkAuth = data;
    console.log(this.fileWorkAuth)
  }

  onSubmit(): void {
    //console.log(this.OnBoardingForm.value)

    //create a new formData
    let formData: FormData = new FormData();

    //put the files you want to upload into an array
    let arr: File[] = [
      this.fileI983.value, 
      this.fileOPTReceipt.value,
      this.fileDriverLicense,
      this.fileWorkAuth
    ]

    //append these files into the formData
    for(let i=0; i<arr.length; i++)
      formData.append('file', arr[i]);

    //console.log(arr)

    let paramObj = {};
    Object.assign(paramObj, this.phoneAddressCarForm.value, this.personalInfoFormValue, 
          this.referenceFormValue, this.emergency1FormValue, this.emergency2FormValue);

    // let paramObj = this.OnBoardingForm.value;
    paramObj['email'] = this.email;
    paramObj['user_id'] = 571;
    paramObj['stateFullName'] = this.states.find((item) => item.abbreviation 
          == this.phoneAddressCarForm.value.state)?.name

    //console.log(paramObj);

    let toTimestamp = this.personalInfoFormValue.dateOfBirth.getTime();
    paramObj['dateOfBirth'] = toTimestamp;

    //first argument: path
    //second argument formData: the form that contains your files
    //third argument obj: the object that contains the information you want to send to the backend
    //a.k.a the OnBoardingForm
    this.httpRequestService.fileUploadWithParams('/hire/submitOnboard', formData, paramObj).subscribe(
        (data: any) => {
            console.log(data);
        },
        err => {
            console.log(err);
    });
    //console.log(this.OnBoardingForm.value)

  }

  chosenYearHandler(normalizedYear: Date) {
    const ctrlValue = this.getDateFromString(this.date.value);
    ctrlValue.setFullYear(normalizedYear.getFullYear());
    this.date.setValue(this.getMonthYearString(ctrlValue));
  }

  chosenMonthHandler(normalizedMonth: Date, datepicker: MatDatepicker<Date>) {
    const ctrlValue = this.getDateFromString(this.date.value);
    ctrlValue.setMonth(normalizedMonth.getMonth());
    this.date.setValue(this.getMonthYearString(ctrlValue));
    datepicker.close();
  }

  getMonthYearString(date: Date): string {
    return `${date.getMonth() + 1}/${date.getFullYear()}`;
  }

  getDateFromString(s: string): Date {
    if ((s.match(/\//g) || []).length !== 1) {
      return new Date();
    }
    if (isNaN(+s.replace('/', ''))) {
      return new Date();
    }
    const [mm, yyyy] = s.split('/');
    return new Date(+yyyy, +mm + 1, 1);
  }

  selectionOnOther() {}

}
