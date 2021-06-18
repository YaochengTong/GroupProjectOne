import { HTTPReq } from 'src/app/service/HTTPReq/HTTPReq.service';
import { Component, ViewChild, AfterViewInit, OnInit} from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})


export class HomeComponent implements OnInit{
  public dataSource;
  displayedColumns: string[] = [
    'id',
    'name',
    'visaType',
    'expirationDate',
    'status',
    'action'
  ]

  constructor(
    private httpRequestService: HTTPReq
  ) {}

  ngOnInit(): void {
    this.httpRequestService.getData('/statusTable/all', null, 'http://localhost:8999').subscribe(
      (data:any) => {
        this.dataSource = new MatTableDataSource<PeriodicElement>(data.AllStatus);
      }
    )
  }

  sendEmail(email, nextStep, employeeId): void {
    let params = {
      email: email,
      nextStep: nextStep,
      employeeId: employeeId,
    }
    alert("Sent Email")
    this.httpRequestService.postData('/notification/nextStep', params).subscribe(
      (data: any) => {
        console.log(data);
      }
    )
  }
}


export interface PeriodicElement {
  id: number;
  name: string;
  visaType: string;
  expirationDate: string;
}
