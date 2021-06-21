import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { states } from 'src/app/shared/constants';
import { MessageService } from 'src/app/service/Message/message.service';

@Component({
  selector: 'app-contact-template',
  templateUrl: './contact-template.component.html',
  styleUrls: ['./contact-template.component.scss'],
})
export class ContactTemplateComponent implements OnInit {

  @Input('componentTitle') title = '';
  @Input('rejected') rejected = 'false';
  @Input('validationIf') validate = true;
  @Output() updateForm: EventEmitter<any> = new EventEmitter<any>();

  messageSub: any;
  application:any = {};

  constructor(private fb: FormBuilder, private messageService: MessageService) {

    this.messageSub = this.messageService.messageObj$.subscribe(value => {
      console.log(value)
      if(value.application && this.rejected == "true"){
        this.application = value.application;
        console.log(this.application)

        if(this.title == 'Reference Contact'){
          this.ContactForm.setValue({
            FirstName: this.application.referenceContact.ReferenceContactFirstName,
            LastName: this.application.referenceContact.ReferenceContactLastName,
            MiddleName: this.application.referenceContact.ReferenceContactMiddleName,
            Phone: this.application.referenceContact.ReferenceContactPhone,
            Address: this.application.referenceContact.ReferenceContactAddress,
            Address2: this.application.referenceContact.ReferenceContactAddress2,
            Email: this.application.referenceContact.ReferenceContactEmail,
            Relationship: this.application.referenceContact.ReferenceContactRelationShip,
            City: this.application.referenceContact.ReferenceContactCity,
            State: this.application.referenceContact.ReferenceContactStateAbbr,
            PostalCode: this.application.referenceContact.ReferenceContactStatePostalCode
          });
        }
        else if(this.title == 'Emergency Contact'){
          this.ContactForm.setValue({
            FirstName: this.application.emergencyContactList[0].EmergencyContactFirstName,
            LastName: this.application.emergencyContactList[0].EmergencyContactLastName,
            MiddleName: this.application.emergencyContactList[0].EmergencyContactMiddleName,
            Phone: this.application.emergencyContactList[0].EmergencyContactPhone,
            Address: this.application.emergencyContactList[0].EmergencyContactAddress,
            Address2: this.application.emergencyContactList[0].EmergencyContactAddress2,
            Email: this.application.emergencyContactList[0].EmergencyContactEmail,
            Relationship: this.application.emergencyContactList[0].EmergencyContactRelationShip,
            City: this.application.emergencyContactList[0].EmergencyContactCity,
            State: this.application.emergencyContactList[0].EmergencyContactStateAbbr,
            PostalCode: this.application.emergencyContactList[0].EmergencyContactPostalCode
          });
        }

        else if(this.title == 'Emergency Contact 2'){
          this.ContactForm.setValue({
            FirstName: this.application.emergencyContactList[1].EmergencyContactFirstName,
            LastName: this.application.emergencyContactList[1].EmergencyContactLastName,
            MiddleName: this.application.emergencyContactList[1].EmergencyContactMiddleName,
            Phone: this.application.emergencyContactList[1].EmergencyContactPhone,
            Address: this.application.emergencyContactList[1].EmergencyContactAddress,
            Address2: this.application.emergencyContactList[1].EmergencyContactAddress2,
            Email: this.application.emergencyContactList[1].EmergencyContactEmail,
            Relationship: this.application.emergencyContactList[1].EmergencyContactRelationShip,
            City: this.application.emergencyContactList[1].EmergencyContactCity,
            State: this.application.emergencyContactList[1].EmergencyContactStateAbbr,
            PostalCode: this.application.emergencyContactList[1].EmergencyContactStatePostalCode
          });
        }
        
      

      }
    });

    this.ContactForm = this.fb.group({
      FirstName: ['', Validators.required],
      LastName: ['', Validators.required],
      MiddleName: '',
      Phone: ['', Validators.required],
      Address: ['', Validators.required],
      Address2: '',
      Email: '',
      Relationship: '',
  
      City: ['', Validators.required],
      State: ['', Validators.required],
      PostalCode: [
        '',
        Validators.compose([
          Validators.required,
          Validators.minLength(5),
          Validators.maxLength(5),
        ]),
      ],
    })

    // console.log(this.validate)
    // if(!this.validate)
    //   this.ContactForm.clearValidators();
  }

  ContactForm: FormGroup;

  states: any[] = states;

  ngOnInit(): void {
    this.ContactForm.valueChanges.subscribe(val => {
      this.dataRefresh();
    });
  }

  ngOnChanges(changes: any): void{
    // console.log(123)
    // if(!this.validate)
    //    this.ContactForm.clearValidators();
  }

  dataRefresh(): void{
    this.updateForm.emit(this.ContactForm.value);
  }


}
