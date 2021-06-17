import { HttpClient } from '@angular/common/http';
import { HTTPReq } from 'src/app/service/HTTPReq/HTTPReq.service';
import { Component, ViewChild, AfterViewInit, OnInit} from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';

@Component({
  selector: 'app-check-employee',
  templateUrl: './check-employee.component.html',
  styleUrls: ['./check-employee.component.scss'],
})

export class CheckEmployeeComponent implements OnInit {

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
      this.dataSource = data.AllProfile;
    }
  )
}


}
