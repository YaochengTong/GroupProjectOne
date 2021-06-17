import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Facility } from '../domain/facility';

@Component({
  selector: 'app-report-dialog',
  templateUrl: './report-dialog.component.html',
  styleUrls: ['./report-dialog.component.scss'],
})
export class ReportDialogComponent {
  facility: Facility;
  reports: any[];

  constructor(
    public dialogRef: MatDialogRef<ReportDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Facility
  ) {
    this.facility = data;
    this.reports = this.facility.reports;
    console.log(this.facility);
    console.log(this.facility.reports);
  }

  onCloseClick(): void {
    this.dialogRef.close();
  }
}
