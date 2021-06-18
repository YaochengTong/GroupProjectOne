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


    this.fileForm = new FormGroup({
      fileI983 :this.fileI983,
      fileOPTReceipt: this.fileOPTReceipt
    });

  }

  fileForm: FormGroup;

  submitting: boolean = false;

  ngOnInit(): void {
    this.email = localStorage.getItem('email');
    this.userId = localStorage.getItem('userId');
  }

  date = new FormControl(this.getMonthYearString(new Date()));
  isCitizen: boolean | undefined = true;
  hasDriverLicense: boolean | undefined = false;
  authorizationSelection: boolean | undefined;

  //template values
  emergencyContact: string = "Emergency Contact";
  referenceContact: string = "Reference Contact";
  showSecondEmergencyContact: boolean = false;
  email:any;
  userId: any;

  personalInfoFormValid: boolean = false;
  personalInfoFormStatus(valid: boolean): void{
      this.personalInfoFormValid = valid;
  }


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

  personalInfoFormValue: any = {
    company: '',
    firstName: '',
    lastName: '',
    middleName: '',
    ssn: '',
    gender: '',
    dateOfBirth: '',
    isCitizen: '',
    citizenType: '',
    authorizationType: '',
    otherAuthorizationType: '',
    authorizationStartDate: '',
    authorizationEndDate: '',
    hasDriverLicense: '',
    driverLicense: '',
    driverLicenseExp: '',
  };

  dataRefreshForPersonalInfo(data: any): void{
    this.personalInfoFormValue = {
      company: data.company,
      firstName: data.firstName,
      lastName: data.lastName,
      middleName: data.middleName,
      ssn: data.ssn,
      gender: data.gender,
      dateOfBirth: data.dateOfBirth,
      isCitizen: data.isCitizen,
      citizenType: data.citizenType,
      authorizationType: data.authorizationType,
      otherAuthorizationType: data.otherAuthorizationType,
      authorizationStartDate: data.authorizationStartDate,
      authorizationEndDate: data.authorizationEndDate,
      hasDriverLicense: data.hasDriverLicense,
      driverLicense: data.driverLicense,
      driverLicenseExp: data.driverLicenseExp
    };
  };

  referenceFormValue: any = {
    referenceContactFirstName: '',
    referenceContactLastName: '',
    referenceContactMiddleName: '',
    referenceContactPhone: '',
    referenceContactAddress: '',
    referenceContactAddress2: '',
    referenceContactEmail: '',
    referenceContactRelationship: '',
    referenceContactCity: '',
    referenceContactState: '',
    referenceContactPostalCode: '',
    referenceContactStateFullName: '',
  };
  dataRefreshForReference(data: any): void{
    this.referenceFormValue = {
        referenceContactFirstName: data.FirstName,
        referenceContactLastName: data.LastName,
        referenceContactMiddleName: data.MiddleName,
        referenceContactPhone: data.Phone,
        referenceContactAddress: data.Address,
        referenceContactAddress2: data.Address2,
        referenceContactEmail: data.Email,
        referenceContactRelationship: data.Relationship,
        referenceContactCity: data.City,
        referenceContactState: data.State,
        referenceContactPostalCode: data.PostalCode,
        referenceContactStateFullName: this.states.find((item) => item.abbreviation 
           == this.referenceFormValue.referenceContactState)?.name
    }
  };

  emergency1FormValue: any = {
      emergency1FirstName: '',
      emergency1LastName: '',
      emergency1MiddleName: '',
      emergency1Phone: '',
      emergency1Address: '',
      emergency1Address2: '',
      emergency1Email: '',
      emergency1Relationship: '',
      emergency1City: '',
      emergency1State: '',
      emergency1PostalCode: '',
      emergency1StateFullName: ''
  };
  dataRefreshForEmergency1(data: any): void{
    this.emergency1FormValue = {
      emergency1FirstName: data.FirstName,
      emergency1LastName: data.LastName,
      emergency1MiddleName: data.MiddleName,
      emergency1Phone: data.Phone,
      emergency1Address: data.Address,
      emergency1Address2: data.Address2,
      emergency1Email: data.Email,
      emergency1Relationship: data.Relationship,
      emergency1City: data.City,
      emergency1State: data.State,
      emergency1PostalCode: data.PostalCode,
      emergency1StateFullName: this.states.find((item) => item.abbreviation 
           == this.emergency1FormValue.emergency1State)?.name
    }
  }

  emergency2FormValue: any = {
      emergency2FirstName: '',
      emergency2LastName: '',
      emergency2MiddleName: '',
      emergency2Phone: '',
      emergency2Address: '',
      emergency2Address2: '',
      emergency2Email: '',
      emergency2Relationship: '',
      emergency2City: '',
      emergency2State: '',
      emergency2PostalCode: '',
      emergency2StateFullName: ''
  };
  dataRefreshForEmergency2(data: any): void{
    this.emergency2FormValue = {
      emergency2FirstName: data.FirstName,
      emergency2LastName: data.LastName,
      emergency2MiddleName: data.MiddleName,
      emergency2Phone: data.Phone,
      emergency2Address: data.Address,
      emergency2Address2: data.Address2,
      emergency2Email: data.Email,
      emergency2Relationship: data.Relationship,
      emergency2City: data.City,
      emergency2State: data.State,
      emergency2PostalCode: data.PostalCode,
      emergency2StateFullName: this.states.find((item) => item.abbreviation 
           == this.emergency2FormValue.emergency2State)?.name
    }
  }

  dataRefreshDriverLicense(data: any): void{
    this.fileDriverLicense = data;
  }

  dataRefreshWorkAuth(data: any): void{
    this.fileWorkAuth = data;
  }

  onSubmit(): void {
    
    //create a new formData
    let formData: FormData = new FormData();

    //put the files you want to upload into an array
    let arr: File[] = [
      this.fileI983.value, 
      this.fileOPTReceipt.value,
      this.fileDriverLicense,
      this.fileWorkAuth
    ]

    console.log(arr)

    let fileTitles: any[] = [
      'I983',
      'OPTReceipt',
      'DriverLicense',
      'WorkAuth'
    ]
      
    //combine the forms together
    let paramObj = {};
    Object.assign(paramObj, this.phoneAddressCarForm.value, this.personalInfoFormValue, 
          this.referenceFormValue, this.emergency1FormValue, this.emergency2FormValue);
    paramObj['email'] = this.email;
    paramObj['user_id'] = this.userId; //localStorage.getItem('userId');
    paramObj['stateFullName'] = this.states.find((item) => item.abbreviation 
          == this.phoneAddressCarForm.value.state)?.name

    let toTimestamp = this.personalInfoFormValue.dateOfBirth.getTime();
    paramObj['dateOfBirth'] = toTimestamp;

    if(this.personalInfoFormValue.driverLicenseExp){
      let toTimestamp2 = this.personalInfoFormValue.driverLicenseExp.getTime();
      paramObj['driverLicenseExp'] = toTimestamp2;
    }

    let toTimestamp3 = this.personalInfoFormValue.authorizationStartDate.getTime();
    paramObj['authorizationStartDate'] = toTimestamp3;

    let toTimestamp4 = this.personalInfoFormValue.authorizationEndDate.getTime();
    paramObj['authorizationEndDate'] = toTimestamp4;

    //append these files into the formData
    let index = 0;
    for(let i=0; i<arr.length; i++){
      if(arr[i] != null && arr[i] instanceof File){
        formData.append('file', arr[i]);
        paramObj["title" + index] = fileTitles[i];
        index++;
      }
    }

    this.submitting = true;

    //first argument: path
    //second argument formData: the form that contains your files
    //third argument obj: the object that contains the information you want to send to the backend
    //a.k.a the OnBoardingForm
    this.httpRequestService.fileUploadWithParams('/hire/submitOnboard', formData, paramObj).subscribe(
        (data: any) => {
            console.log(data);
            this.submitting = false;
        },
        err => {
            console.log(err);
            this.submitting = false;
        });

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

  // [disabled]="!personalInfoFormValid 
  // || !phoneAddressCarForm.valid
  // || !ReferenceContact.ContactForm.valid
  // || !EmergencyContact.ContactForm.valid
  // || !fileForm.valid"

}
