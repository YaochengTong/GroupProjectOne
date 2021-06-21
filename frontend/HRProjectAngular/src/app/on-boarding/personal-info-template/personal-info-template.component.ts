import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { FormBuilder, FormControl, Validators } from '@angular/forms';
import { ThemePalette } from '@angular/material/core';
import { MatDatepicker } from '@angular/material/datepicker';
import { MaxSizeValidator } from '@angular-material-components/file-input';
import { MessageService } from 'src/app/service/Message/message.service';

@Component({
  selector: 'app-personal-info-template',
  templateUrl: './personal-info-template.component.html',
  styleUrls: ['./personal-info-template.component.scss']
})
export class PersonalInfoTemplateComponent implements OnInit {

  //user email
  @Input('email') email = 'test@gmail.com';
  @Input('rejected') rejected = 'false';
  @Input('comments') comment: any = {
    'FirstName': '',
    'LastName': '',
    'MiddleName': '',
    'SSN': '',
    'Gender': '',
    'DOB': '',
    'WorkAuth': '',
    'Citizen': '',
    'CitizenType': '',
    'AuthStart': '',
    'AuthEnd': '',
    'DriverLicense': '',
    'DriverLicenseExpireDate': '',
    'PrimaryPhone': '',
    'Car': '',
    'AlterPhone': '',
    'Address1': '',
    'Address2': '',
    'City': '',
    'State': '',
    'PostalCode': '',

    //reference contact
    'RFirstName': '',
    'RLastName': '',
    'RMiddleName': '',
    'RPhone': '',
    'RAddress1': '',
    'RAddress2': '',
    'RCity': '',
    'RState': '',
    'RPostalCode': '',
    'REmail': '',
    'RRelation': '',

    //emergency contacts
    'EContacts': [
      {
        'EFirstName': '',
        'ELastName': '',
        'EMiddleName': '',
        'EPhone': '',
        'EAddress1': '',
        'EAddress2': '',
        'ECity': '',
        'EState': '',
        'EPostalCode': '',
        'EEmail': '',
        'ERelation': '',
      },
      {
        'EFirstName': '',
        'ELastName': '',
        'EMiddleName': '',
        'EPhone': '',
        'EAddress1': '',
        'EAddress2': '',
        'ECity': '',
        'EState': '',
        'EPostalCode': '',
        'EEmail': '',
        'ERelation': '',
      }
    ],

    files: [

    ]

  };

  commentStyle(item): any {
    if(item == ''){
      return {"color": "green"};
    }
    return {"color": "red"};
  }
  
  application:any = {};
  

  @Output() updateForm: EventEmitter<any> = new EventEmitter<any>();
  @Output() updateDriverLicense: EventEmitter<any> = new EventEmitter<any>();
  @Output() updateWorkAuth: EventEmitter<any> = new EventEmitter<any>();
  @Output() updateFormValid: EventEmitter<boolean> = new EventEmitter<boolean>();

  date = new FormControl(this.getMonthYearString(new Date()));
  messageSub: any;
  filePaths: any = {
    'driverLicense': '',
    'WorkAuth': '',
    'I983': '',
    'OPT': '',
  }

  constructor(private fb: FormBuilder, private messageService: MessageService) {
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
    this.personalInfoForm.valueChanges.subscribe(val => {
      this.dataRefresh();
    });
    this.fileDriverLicense.valueChanges.subscribe(val => {
      this.updateDriverLicense.emit(val);
    });
    this.fileWorkAuth.valueChanges.subscribe(val => {
      this.updateWorkAuth.emit(val);
    });

    this.messageSub = this.messageService.messageObj$.subscribe(value => {
      console.log(value)
      if(value.application && this.rejected == "true"){
        this.application = value.application;
        console.log(this.application)
        this.email = this.application.email;
        this.personalInfoForm.setValue({
          firstName: this.application.firstName,
          lastName: this.application.lastName,
          middleName: this.application.middleName,
          ssn: this.application.SSN,
          gender: this.application.Gender,
          dateOfBirth: this.application.DateOfBirth,
      
          isCitizen: this.application.visaType == 'green card' || this.application.visaType == 'citizen',
          citizenType: (this.application.visaType == 'green card' 
            || this.application.visaType == 'citizen')? this.application.visaType: 'green card',
          authorizationType: this.application.visaType,
          otherAuthorizationType: '',
          authorizationStartDate: this.application.visaStartDate,
          authorizationEndDate: this.application.visaEndDate,
      
          hasDriverLicense: this.application.DriverLicense?true:false,
          driverLicense: this.application.DriverLicense,
          driverLicenseExp: this.application.DriverLicenseExpireDate,
        });
        this.authorizationSelection = this.application.visaType;
        this.fileWorkAuth.setValidators([
          MaxSizeValidator(1024 * 1024),
        ])
        this.fileDriverLicense.setValidators([
          MaxSizeValidator(1024 * 1024),
        ]);

        this.filePaths = {
          'driverLicense': this.application.documents.find(item => item.title=='DriverLicense')?.path,
          'WorkAuth': this.application.documents.find(item => item.title=='WorkAuth')?.path,
          'I983': this.application.documents.find(item => item.title=='I983')?.path,
          'OPT': this.application.documents.find(item => item.title=='OPTReceipt')?.path,
        }

      }
    });
  }

  dataRefresh(): void{
    this.updateForm.emit(this.personalInfoForm.value);
    //console.log(this.personalInfoForm)
    this.updateFormValid.emit(this.personalInfoForm.valid);
  }

  //File upload variables
  files: File | undefined;
  color: ThemePalette = 'primary';
  accept: string | undefined;
  multiple: boolean = false;
  fileWorkAuth: FormControl;
  fileDriverLicense: FormControl;

  isCitizen: boolean | undefined = true;
  hasDriverLicense: boolean | undefined = false;
  authorizationSelection: boolean | undefined;
  citizenType: string | undefined = 'Green Card';

  personalInfoForm = this.fb.group({
    firstName: ['', Validators.required],
    lastName: ['', Validators.required],
    middleName: '',
    ssn: ['', Validators.required],
    gender: ['', Validators.required],
    dateOfBirth: ['', Validators.required],

    isCitizen: ['', Validators.required],
    citizenType: 'green card',
    authorizationType: '',
    otherAuthorizationType: '',
    authorizationStartDate: '',
    authorizationEndDate: '',

    hasDriverLicense: [false, Validators.required],
    driverLicense: '',
    driverLicenseExp: '',
  })

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

  selectionOnOther(): void {

  }

}
