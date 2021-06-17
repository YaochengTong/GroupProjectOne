import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { states } from 'src/app/shared/constants';


@Component({
  selector: 'app-contact-template',
  templateUrl: './contact-template.component.html',
  styleUrls: ['./contact-template.component.scss'],
})
export class ContactTemplateComponent implements OnInit {

  @Input('componentTitle') title = '';
  @Output() updateForm: EventEmitter<any> = new EventEmitter<any>();

  constructor(private fb: FormBuilder) { 
    this.ContactForm = this.fb.group({
      FirstName: [null, Validators.required],
      LastName: [null, Validators.required],
      MiddleName: null,
      Phone: [null, Validators.required],
      Address: [null, Validators.required],
      Address2: null,
      Email: null,
      Relationship: null,
  
      City: [null, Validators.required],
      State: [null, Validators.required],
      PostalCode: [
        null,
        Validators.compose([
          Validators.required,
          Validators.minLength(5),
          Validators.maxLength(5),
        ]),
      ],
    })
  }

  ContactForm: FormGroup;

  states: any[] = states;

  ngOnInit(): void {
    this.ContactForm.valueChanges.subscribe(val => {
      this.dataRefresh();
    });
  }

  dataRefresh(): void{
    this.updateForm.emit(this.ContactForm.value);
  }


}
