import { Component, Inject, OnInit } from '@angular/core';
import {MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';

export interface AddressSectionData {
  addressSection: {
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

@Component({
  selector: 'app-address-section-dialog',
  templateUrl: './address-section-dialog.component.html',
  styleUrls: ['./address-section-dialog.component.scss']
})
export class AddressSectionDialogComponent implements OnInit {

  constructor(
    public dialogRef: MatDialogRef<AddressSectionDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data:AddressSectionData
  ) { }

  ngOnInit(): void {
  }

  onCancelClick(): void {
    this.dialogRef.close();
  }

}
