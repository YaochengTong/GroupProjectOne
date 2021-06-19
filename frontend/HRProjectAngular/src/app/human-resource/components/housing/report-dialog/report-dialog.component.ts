import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Facility } from '../domain/facility';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-report-dialog',
  templateUrl: './report-dialog.component.html',
  styleUrls: ['./report-dialog.component.scss'],
})
export class ReportDialogComponent {
  facility: Facility;
  reports: undefined;

  constructor(
    public dialogRef: MatDialogRef<ReportDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Facility,
    private httpClient: HttpClient
  ) {
    this.facility = data;
  }

  onCloseClick(): void {
    this.dialogRef.close();
  }

  getReportsByFacilityId(id: number) {
    this.reports = undefined;
  }
}
