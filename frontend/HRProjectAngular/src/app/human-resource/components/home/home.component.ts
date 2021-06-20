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

  isDataAvailable: boolean = false;
  public dataSource;
  displayedColumns: string[] = [
    'id',
    'name',
    'visaType',
    'expirationDate',
    'dayLeft',
    'status',
    'action'
  ]

  constructor(
    private httpRequestService: HTTPReq
  ) {}

  ngOnInit(): void {
    this.httpRequestService.getData('/statusTable/all', null, 'http://localhost:8999').subscribe(
      (data:any) => {
        this.isDataAvailable = true;
        this.dataSource = new MatTableDataSource<PeriodicElement>(data.AllStatus);
      }
    )
  }

  compare(data:any) {
    console.log(data);
    console.log(data != "I-983 NEED TO BE SIGNED");
  }

  sendEmail(nextStep, userId): void {
    let params = {
      message: nextStep,
      userId: userId,
    }
    // alert("Sent Email")
    this.httpRequestService.postData('/visa-status-management/send-notification', 
    params,
    'http://localhost:8999').subscribe(
      (data: any) => {
        // console.log(data);
        if (data.success == true) {
          alert("email sent");
        }
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
