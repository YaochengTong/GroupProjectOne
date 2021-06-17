import { Component } from '@angular/core';
import { FormBuilder, FormControl, Validators } from '@angular/forms';
import { MatDatepicker } from '@angular/material/datepicker';
import { ThemePalette } from '@angular/material/core';
import { MaxSizeValidator } from '@angular-material-components/file-input';
import { HTTPReq } from 'src/app/service/HTTPReq/HTTPReq.service';
import { states } from '../shared/constants';

@Component({
  selector: 'app-on-boarding',
  templateUrl: './on-boarding.component.html',
  styleUrls: ['./on-boarding.component.scss'],
})
export class OnBoardingComponent {
  date = new FormControl(this.getMonthYearString(new Date()));
  isCitizen: boolean | undefined = true;
  hasDriverLicense: boolean | undefined = false;
  authorizationSelection: boolean | undefined;

  //templates
  emergencyContact: string = "Emergency Contact";
  referenceContact: string = "Reference Contact";

  //File upload variables
  color: ThemePalette = 'primary';
  accept: string | undefined;
  multiple: boolean = false;
  fileWorkAuth: FormControl;
  fileDriverLicense: FormControl;
  fileI983: FormControl;
  files: File | undefined;
  hasUnitNumber = false;

  //user email
  email:string = 'test@gmail.com';

  OnBoardingForm = this.fb.group({
    // Personal Info
    company: null,
    firstName: [null, Validators.required],
    lastName: [null, Validators.required],
    middleName: null,
    ssn: [null, Validators.required],
    gender: [null, Validators.required],
    dateOfBirth: [null, Validators.required],

    isCitizen: [null, Validators.required],
    citizenType: null,
    authorizationType: null,
    otherAuthorizationType: null,
    authorizationStartDate: null,
    authorizationEndDate: null,

    hasDriverLicense: [false, Validators.required],
    driverLicense: null,
    driverLicenseExp: null,
    
    // Contact Info
    address: [null, Validators.required],
    address2: null,
    city: [null, Validators.required],
    state: [null, Validators.required],
    postalCode: [
      null,
      Validators.compose([
        Validators.required,
        Validators.minLength(5),
        Validators.maxLength(5),
      ]),
    ],

    // Contact Info 2
    cellPhone: [null, Validators.required],
    workPhone: null,

    // Reference Info
    reference: null,

    // Car Info
    carMaker: null,
    carModel: null,
    carColor: null,

  });

  states: any[] = states;

  constructor(private fb: FormBuilder, private httpRequestService: HTTPReq) {
    this.fileWorkAuth = new FormControl(this.files, [
      Validators.required,
      MaxSizeValidator(1024 * 1024),
    ]);

    this.fileDriverLicense = new FormControl(this.files, [
      Validators.required,
      MaxSizeValidator(1024 * 1024),
    ]);

    this.fileI983 = new FormControl(this.files, [
      Validators.required,
      MaxSizeValidator(1024 * 1024),
    ]);
  }

  //
  // selectCitizenshipOnChange(){
  //

  onSubmit(): void {
    console.log(this.OnBoardingForm.value)

    //create a new formData
    let formData: FormData = new FormData();

    //put the files you want to upload into an array
    let arr: File[] = [
      this.fileI983.value, 
      this.fileDriverLicense.value,
      this.fileWorkAuth.value
    ]

    //append these files into the formData
    for(let i=0; i<arr.length; i++)
      formData.append('file', arr[i]);

    let paramObj = this.OnBoardingForm.value;
    paramObj['email'] = this.email;
    paramObj['user_id'] = 571;
    paramObj['stateFullName'] = this.states.find((item) => item.abbreviation 
          == this.OnBoardingForm.value.state)?.name

    let toTimestamp = this.OnBoardingForm.value.dateOfBirth.getTime();
    paramObj['dateOfBirth'] = toTimestamp;

    //first argument: path
    //second argument formData: the form that contains your files
    //third argument obj: the object that contains the information you want to send to the backend
    //a.k.a the OnBoardingForm
    // this.httpRequestService.fileUploadWithParams('/test/fileUploadWithForm', formData, paramObj).subscribe(
    //     (data: any) => {
    //         console.log(data);
    //     },
    //     err => {
    //         console.log(err);
    // });
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
