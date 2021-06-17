import { Component, OnInit, Input } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { states } from 'src/app/shared/constants';

@Component({
  selector: 'app-contact-template',
  templateUrl: './contact-template.component.html',
  styleUrls: ['./contact-template.component.scss']
})
export class ContactTemplateComponent implements OnInit {

  @Input('componentTitle') title = '';

  constructor(private fb: FormBuilder) { 

  }

  states: any[] = states;

  ContactForm = this.fb.group({
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

  ngOnInit(): void {

  }

}
