import { Component, Inject, OnInit } from '@angular/core';
import {MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';

export interface EmploymentSectionData {
  employmentSection: {
    workAuthorization: string;
    authorizationStartDate: string;
    authorizationEndDate: string;
    employmentStartDate: string;
    employmentEndDate: string;
    title: string;
  }
  
}

@Component({
  selector: 'app-employment-section-dialog',
  templateUrl: './employment-section-dialog.component.html',
  styleUrls: ['./employment-section-dialog.component.scss']
})
export class EmploymentSectionDialogComponent implements OnInit {

  constructor(
    public dialogRef: MatDialogRef<EmploymentSectionDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data:EmploymentSectionData
  ) { }

  ngOnInit(): void {
  }

  onCancelClick(): void {
    this.dialogRef.close();
  }

}
