import { HTTPReq } from 'src/app/service/HTTPReq/HTTPReq.service';
import { Component, ViewChild, AfterViewInit, OnInit} from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatButtonModule } from '@angular/material/button';
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



export class CheckEmployeeComponent implements OnInit {

  public dataSource;
  public len!: number;
  isDataAvailable: boolean = false;
  displayedColumns: string[] = [
    'name',
    'ssn',
    'startingDate',
    'visaStatus',
    'position',
    'details'
  ];


  
  constructor(
    private httpRequestService: HTTPReq,
  ) {
    this.httpRequestService.getData('/profile/allSummary', null, 'http://localhost:8999').subscribe(
      (data:any) => {
        this.isDataAvailable = true;
        this.dataSource = new MatTableDataSource<PeriodicElement>(data.AllSummary);
        this.len = Object.keys(data.AllSummary).length;
        console.log(this.dataSource);
      }
    )
  }

  details(employeeId : number) {
    alert(employeeId);
  }


  // @ViewChild(MatPaginator) paginator! : MatPaginator;
  ngOnInit(): void {
    console.log(this.isDataAvailable);
  }

  
  // ngAfterViewInit() {
  //   this.dataSource.paginator = this.paginator;
  // }


  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
      }
  }
}


