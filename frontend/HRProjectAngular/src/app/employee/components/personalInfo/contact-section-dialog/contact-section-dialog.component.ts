import { Component, Inject, OnInit } from '@angular/core';
import { EmailValidator } from '@angular/forms';
import {MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';

export interface ContactSectionData {
  contactSection : {
    personalEmail: string;
    workEmail: string;
    ceilphone: string;
    workPhone: string
  }
}
@Component({
  selector: 'app-contact-section-dialog',
  templateUrl: './contact-section-dialog.component.html',
  styleUrls: ['./contact-section-dialog.component.scss']
})
export class ContactSectionDialogComponent implements OnInit {

  constructor(
    public dialogRef: MatDialogRef<ContactSectionDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data:ContactSectionData
  ) { }

  ngOnInit(): void {
  }

  onCancelClick(): void {
    this.dialogRef.close();
  }

}
