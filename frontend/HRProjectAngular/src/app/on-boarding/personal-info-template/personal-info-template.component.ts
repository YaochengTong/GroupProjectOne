import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { FormBuilder, FormControl, Validators } from '@angular/forms';
import { ThemePalette } from '@angular/material/core';
import { MatDatepicker } from '@angular/material/datepicker';
import { MaxSizeValidator } from '@angular-material-components/file-input';

@Component({
  selector: 'app-personal-info-template',
  templateUrl: './personal-info-template.component.html',
  styleUrls: ['./personal-info-template.component.scss']
})
export class PersonalInfoTemplateComponent implements OnInit {

  //user email
  @Input('email') email = 'test@gmail.com';
  @Output() updateForm: EventEmitter<any> = new EventEmitter<any>();
  @Output() updateDriverLicense: EventEmitter<any> = new EventEmitter<any>();
  @Output() updateWorkAuth: EventEmitter<any> = new EventEmitter<any>();

  date = new FormControl(this.getMonthYearString(new Date()));

  constructor(private fb: FormBuilder) {
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
  }

  dataRefresh(): void{
    this.updateForm.emit(this.personalInfoForm.value);
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

  personalInfoForm = this.fb.group({
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
