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

export interface PElement {
  name: string;
  position: number;
  weight: number;
  symbol: string;
}

const ELEMENT_DATA: PElement[] = [
  {position: 1, name: 'Hydrogen', weight: 1.0079, symbol: 'H'},
  {position: 2, name: 'Helium', weight: 4.0026, symbol: 'He'},
  {position: 3, name: 'Lithium', weight: 6.941, symbol: 'Li'},
  {position: 4, name: 'Beryllium', weight: 9.0122, symbol: 'Be'},
  {position: 5, name: 'Boron', weight: 10.811, symbol: 'B'},
  {position: 6, name: 'Carbon', weight: 12.0107, symbol: 'C'},
  {position: 7, name: 'Nitrogen', weight: 14.0067, symbol: 'N'},
  {position: 8, name: 'Oxygen', weight: 15.9994, symbol: 'O'},
  {position: 9, name: 'Fluorine', weight: 18.9984, symbol: 'F'},
  {position: 10, name: 'Neon', weight: 20.1797, symbol: 'Ne'},
];



@Component({
  selector: 'app-check-employee',
  templateUrl: './check-employee.component.html',
  styleUrls: ['./check-employee.component.scss'],
})



export class CheckEmployeeComponent implements OnInit, AfterViewInit {

  public dataSource;
  public len!: number;
  isDataAvailable: boolean = false;
  displayedColumns: string[] = [
    'name',
    'ssn',
    'startingDate',
    'visaStatus',
    'position',
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



  ngOnInit(): void {
    // this.httpRequestService.getData('/profile/all', null, 'http://localhost:8999').subscribe(
    //   (data:any) => {
    //     this.isDataAvailable = true;
    //     this.dataSource = new MatTableDataSource<PeriodicElement>(data.AllProfile);
    //     this.len = Object.keys(data.AllProfile).length;
    //   }
    // )
    // this.dataSource.paginator = this.paginator;
  }

  @ViewChild(MatPaginator) paginator! : MatPaginator;
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


