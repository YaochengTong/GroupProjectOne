import { HttpClient } from '@angular/common/http';
import { HTTPReq } from 'src/app/service/HTTPReq/HTTPReq.service';
import { Component, ViewChild, AfterViewInit, OnInit} from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';

@Component({
  selector: 'app-check-employee',
  templateUrl: './check-employee.component.html',
  styleUrls: ['./check-employee.component.scss'],
})

export class CheckEmployeeComponent implements OnInit, AfterViewInit {

  public dataSource;
  displayedColumns: string[] = [
    'name',
    'ssn',
    'startingDate',
    'visaStatus',
    // 'order',
  ];

  constructor(
    private httpRequestService: HTTPReq
  ) {}

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
    }


  ngOnInit(): void {
    this.httpRequestService.getData('/profile/all', null, 'http://localhost:8999').subscribe(
      (data:any) => {
        this.dataSource = new MatTableDataSource<PeriodicElement>(data.AllProfile);
      }
    )
  }

  @ViewChild(MatPaginator)
  paginator!: MatPaginator;
  ngAfterViewInit() {
  this.dataSource.paginator = this.paginator;
  }
}

export interface PeriodicElement {
    name: string;
    ssn: number;
    startingDate: string;
    visaStatus: string;
}
