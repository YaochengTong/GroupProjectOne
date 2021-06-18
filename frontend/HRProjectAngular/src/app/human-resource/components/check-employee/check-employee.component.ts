import { HttpClient } from '@angular/common/http';
import { HTTPReq } from 'src/app/service/HTTPReq/HTTPReq.service';
import { Component, ViewChild, AfterViewInit, OnInit} from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';


export interface PeriodicElement {
  name: string;
  ssn: number;
  startingDate: string;
  visaStatus: string;
}


@Component({
  selector: 'app-check-employee',
  templateUrl: './check-employee.component.html',
  styleUrls: ['./check-employee.component.scss'],
})

export class CheckEmployeeComponent implements OnInit, AfterViewInit {

  public dataSource;
  public len!: number;
  displayedColumns: string[] = [
    'name',
    'ssn',
    'startingDate',
    'visaStatus',
    // 'order',
    // 'len'
  ];

  constructor(
    private httpRequestService: HTTPReq,
  ) {
    this.httpRequestService.getData('/profile/all', null, 'http://localhost:8999').subscribe(
      (data:any) => {
        this.dataSource = new MatTableDataSource<PeriodicElement>(data.AllProfile);
        this.len = Object.keys(data.AllProfile).length;
      }
    )
  }

  ngOnInit(): void {
    // this.httpRequestService.getData('/profile/all', null, 'http://localhost:8999').subscribe(
    //   (data:any) => {
    //     this.dataSource = new MatTableDataSource<PeriodicElement>(data.AllProfile);
    //     this.len = Object.keys(data.AllProfile).length;
    //   }
    // )
    this.dataSource.paginator = this.paginator;
  }


  @ViewChild(MatPaginator)
  paginator!: MatPaginator;
  ngAfterViewInit() {
  this.dataSource.paginator = this.paginator;
  }


  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
      }
  }
}


