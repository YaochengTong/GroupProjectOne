import { Component, Inject, OnInit } from '@angular/core';
import {MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';


export interface EmergencyContacSectionData{
  emergencyContactList: {
    emergencyPerson1: {
      fullName:string;
      phone: string;
      address: {
        secondaryAddr: {
          Zip: string;
          AddressLine2: string;
          AddressLine1: string;
          State: string;
          City: string;
        };
        primaryAddr: {
          Zip: string;
          AddressLine2: string;
          AddressLine1: string;
          State: string;
          City: string;
        }
      }
    };
    emergencyPerson2: {
      fullName:string;
      phone: string;
      address: {
        secondaryAddr: {
          Zip: string;
          AddressLine2: string;
          AddressLine1: string;
          State: string;
          City: string;
        };
        primaryAddr: {
          Zip: string;
          AddressLine2: string;
          AddressLine1: string;
          State: string;
          City: string;
        }
      }
    }
  }
}

@Component({
  selector: 'app-emergency-contact-section-dialog',
  templateUrl: './emergency-contact-section-dialog.component.html',
  styleUrls: ['./emergency-contact-section-dialog.component.scss']
})
export class EmergencyContactSectionDialogComponent implements OnInit {

  constructor(
    public dialogRef: MatDialogRef<EmergencyContactSectionDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data:EmergencyContacSectionData
  ) { }

  ngOnInit(): void {
  }

  onCancelClick(): void {
    this.dialogRef.close();
  }

}
