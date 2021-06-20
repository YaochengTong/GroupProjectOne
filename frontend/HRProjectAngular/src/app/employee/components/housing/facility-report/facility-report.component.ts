import { Component, OnInit } from '@angular/core';
import { House } from '../domain/house';
import { HouseService } from '../house.service';
import { FacilityReport } from '../domain/facility-report';
import { FormBuilder, Validators } from '@angular/forms';
import { Location } from '@angular/common';

@Component({
  selector: 'app-facility-report',
  templateUrl: './facility-report.component.html',
  styleUrls: ['./facility-report.component.scss'],
})
export class FacilityReportComponent implements OnInit {
  house?: House;
  facilityReports?: FacilityReport[];
  newFacilityReport: FacilityReport | undefined;
  userId = 0;

  reportForm = this.fb.group({
    houseFacilityReportTitle: [null, Validators.required],
    houseFacilityReportDate: null,
    houseFacilityReportDescription: [null, Validators.required],
    houseFacilityReportStatus: [null, Validators.required],
    houseFacilityReportId: [null, Validators.required],
    houseFacilityReportDetails: null,
  });

  constructor(
    private hs: HouseService,
    private fb: FormBuilder,
    private location: Location
  ) {}

  ngOnInit(): void {
    this.userId = Number(localStorage.getItem('userId'));
    this.hs.getHouseInfoByUserId(this.userId).subscribe((h) => {
      this.house = h;
      this.facilityReports = h.houseFacilityInfoList[0].houseFacilityReportInfo;
    });
  }

  goBack(): void {
    this.location.back();
  }

  onSubmit() {
    this.newFacilityReport = new FacilityReport(this.reportForm.value);
    console.log(this.newFacilityReport);
    this.hs
      .postFacilityReportByUserId(this.newFacilityReport, this.userId)
      .subscribe(() => this.goBack());
    alert('Thanks!');
  }
}
