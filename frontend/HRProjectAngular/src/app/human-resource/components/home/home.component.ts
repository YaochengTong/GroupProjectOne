import {HTTPReq} from 'src/app/service/HTTPReq/HTTPReq.service';
import {Component, OnInit} from '@angular/core';
import {MatTableDataSource} from '@angular/material/table';
import {Router} from '@angular/router';
import {HttpClient} from '@angular/common/http';
import {Location} from '@angular/common';
import {MatDialog} from '@angular/material/dialog';
import {PreviewTxtComponent} from "./preview-txt/preview-txt.component";

// const httpOptions = {
//   headers: new HttpHeaders({
//     'Access-Control-Allow-Origin': '*',
//   }),
//   requestType: 'text' as 'json',
// };

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit {
  isDataAvailable: boolean = false;
  public dataSource;
  displayedColumns: string[] = [
    'id',
    'name',
    'visaType',
    'expirationDate',
    'dayLeft',
    'status',
    'action',
  ];
  public fileString;

  constructor(
    private httpRequestService: HTTPReq,
    private router: Router,
    private http: HttpClient,
    private location: Location,
    public dialog: MatDialog
  ) {}

  ngOnInit(): void {
    this.httpRequestService
      .getData('/statusTable/all', null, 'http://localhost:8999')
      .subscribe((data: any) => {
        this.isDataAvailable = true;
        this.dataSource = new MatTableDataSource<PeriodicElement>(
          data.AllStatus
        );
      });
  }

  downLoad(userId: any): void {
    // https://gp1storage.s3.us-east-2.amazonaws.com/template/i983.pdf"
    //                  target="_blank" download>I983 Instruction</a >
    // this.router.navigateByUrl("gp1storage.s3.us-east-2.amazonaws.com/template/i983.pdf")
    window.location.href =
      'https://gp1storage.s3.us-east-2.amazonaws.com/' +
      userId +
      '/I-983 Filled.txt';
  }

  preview(userId: any): void {
    // https://gp1storage.s3.us-east-2.amazonaws.com/template/i983.pdf"
    //                  target="_blank" download>I983 Instruction</a >
    // this.router.navigateByUrl("gp1storage.s3.us-east-2.amazonaws.com/template/i983.pdf")

    const dialogRef = this.dialog.open(PreviewTxtComponent, {
      width: '500px',
      data: { data: userId },
    });

    dialogRef.afterClosed().subscribe(() => {
      console.log('The dialog was closed');
    });

    // var reader = new FileReader();
    // window.open(
    //   'https://gp1storage.s3.us-east-2.amazonaws.com/' +
    //     userId +
    //     '/I-983 Filled.txt'
    // );
    // this.http
    //   .get(
    //     'https://gp1storage.s3.us-east-2.amazonaws.com/' +
    //       userId +
    //       '/I-983 Filled.txt',
    //     httpOptions
    //   )
    //   .subscribe((data) => {
    //     console.log(data);
    //   });
  }

  goBack(): void {
    this.location.back();
  }

  sendEmail(nextStep, userId): void {
    let params = {
      message: nextStep,
      userId: userId,
    };
    // alert("Sent Email")
    this.httpRequestService
      .postData(
        '/visa-status-management/send-notification',
        params,
        'http://localhost:8999'
      )
      .subscribe((data: any) => {
        // console.log(data);
        if (data.success == true) {
          alert('email sent');
        }
      });
  }
}

export interface PeriodicElement {
  id: number;
  name: string;
  visaType: string;
  expirationDate: string;
}
