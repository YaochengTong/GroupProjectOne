import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { House } from '../domain/house';
import { HousingService } from '../housing.service';
import { Facility } from '../domain/facility';
import { Employee } from '../domain/employee';
import { ReportDialogComponent } from '../report-dialog/report-dialog.component';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-housing-detail',
  templateUrl: './housing-detail.component.html',
  styleUrls: ['./housing-detail.component.scss'],
})
export class HousingDetailComponent implements OnInit {
  house: House | undefined;
  facility: Facility[] | undefined;
  employee: Employee[] | undefined;

  constructor(
    private route: ActivatedRoute,
    private housingService: HousingService,
    private location: Location,
    public dialog: MatDialog
  ) {}

  ngOnInit(): void {
    this.getHouse();
    // @ts-ignore
    this.facility = this.house.facility;
    // @ts-ignore
    this.employee = this.house.employee;
  }

  getHouse(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.housingService.getHouseById(id).subscribe((h) => (this.house = h));
  }

  goBack(): void {
    this.location.back();
  }

  openDialog(): void {
    const dialogRef = this.dialog.open(ReportDialogComponent, {
      width: '250px',
      data: { facility: this.facility },
    });

    dialogRef.afterClosed().subscribe(() => {
      console.log('The dialog was closed');
    });
  }

  onSubmitData() {
    alert('You have updated housing info successfully');
  }
}
