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
