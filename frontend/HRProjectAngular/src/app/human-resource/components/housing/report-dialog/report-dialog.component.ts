import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { FacilityReport } from '../domain/facility-report';

@Component({
  selector: 'app-report-dialog',
  templateUrl: './report-dialog.component.html',
  styleUrls: ['./report-dialog.component.scss'],
})
export class ReportDialogComponent {
  reports: FacilityReport[] | undefined;

  constructor(
    public dialogRef: MatDialogRef<ReportDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: FacilityReport[]
  ) {
    this.reports = data['reports'];
    console.log(this.reports);
  }

  onCloseClick(): void {
    this.dialogRef.close();
  }
}
