import { Component, Inject, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DOCUMENT, Location } from '@angular/common';
import { House } from '../domain/house';
import { HousingService } from '../housing.service';
import { Facility } from '../domain/facility';
import { Employee } from '../domain/employee';
import { ReportDialogComponent } from '../report-dialog/report-dialog.component';
import { MatDialog } from '@angular/material/dialog';
import { FacilityReport } from '../domain/facility-report';

@Component({
  selector: 'app-housing-detail',
  templateUrl: './housing-detail.component.html',
  styleUrls: ['./housing-detail.component.scss'],
})
export class HousingDetailComponent implements OnInit {
  house: House | undefined;
  facility: Facility[] | undefined;
  employee: Employee[] | undefined;
  facilityReport: FacilityReport[] | undefined;
  reports: FacilityReport[] | undefined;

  // reports: FacilityReport[] | undefined;

  constructor(
    private route: ActivatedRoute,
    private housingService: HousingService,
    private location: Location,
    public dialog: MatDialog,
    @Inject(DOCUMENT) private document: Document
  ) {}

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.housingService.getHouseById(id).subscribe((h) => {
      this.house = h;
      this.facility = h.houseFacilityInfoList;
      this.employee = h.houseEmployeeInfoList;
      this.facilityReport = h.houseFacilityInfoList[0].houseFacilityReportInfo;
    });
  }

  goBack(): void {
    this.location.back();
  }

  openDialog(): void {
    const dialogRef = this.dialog.open(ReportDialogComponent, {
      width: '500px',
      data: { reports: this.facilityReport },
    });

    dialogRef.afterClosed().subscribe(() => {
      console.log('The dialog was closed');
    });
  }

  onSubmitData() {
    console.log(this.house);
    if (this.house) {
      this.housingService
        .updateHouse(this.house)
        .subscribe(() => this.goBack());
    }
    alert('You have updated housing info successfully');
  }

  redirect(employeeId: number) {
    this.document.location.href =
      'human-resource/check-employee/detail/' + employeeId;
  }
}
