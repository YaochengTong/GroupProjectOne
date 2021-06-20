import { HTTPReq } from 'src/app/service/HTTPReq/HTTPReq.service';
import { Component, ViewChild, AfterViewInit, OnInit} from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';


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
    private httpRequestService: HTTPReq,
    private router:Router,
  ) {}

  ngOnInit(): void {
    this.httpRequestService.getData('/statusTable/all', null, 'http://localhost:8999').subscribe(
      (data:any) => {
        this.isDataAvailable = true;
        this.dataSource = new MatTableDataSource<PeriodicElement>(data.AllStatus);
      }
    )
  }

  downLoad(userId : any):void {
    // https://gp1storage.s3.us-east-2.amazonaws.com/template/i983.pdf"
    //                  target="_blank" download>I983 Instruction</a >
    // this.router.navigateByUrl("gp1storage.s3.us-east-2.amazonaws.com/template/i983.pdf")
    window.location.href = "https://gp1storage.s3.us-east-2.amazonaws.com/" + userId + "/I-983 Filled.txt"
  }


  preview(userId : any):void {
    // https://gp1storage.s3.us-east-2.amazonaws.com/template/i983.pdf"
    //                  target="_blank" download>I983 Instruction</a >
    // this.router.navigateByUrl("gp1storage.s3.us-east-2.amazonaws.com/template/i983.pdf")
    window.location.href = "https://gp1storage.s3.us-east-2.amazonaws.com/" + userId + "/I-983 Filled.txt"
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
