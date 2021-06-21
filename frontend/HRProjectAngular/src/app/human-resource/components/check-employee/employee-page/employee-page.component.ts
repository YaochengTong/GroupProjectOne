import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { EmployeeService } from '../employee.service';
import { Employee } from '../domain/Employee';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';

@Component({
  selector: 'app-employee-page',
  templateUrl: './employee-page.component.html',
  styleUrls: ['./employee-page.component.scss'],
})
export class EmployeePageComponent implements OnInit, AfterViewInit {
  isDataAvailable: boolean = false;
  displayedColumns: string[] = [
    'name',
    'ssn',
    'startingDate',
    'visaStatus',
    'position',
    'details',
  ];
  employees: Employee[] = [];
  public dataSource;
  public len;
  //@ts-ignore
  // @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(private es: EmployeeService) {}

  ngOnInit(): void {
    this.es.getEmployeeList().subscribe((e) => {
      this.employees = e;
      this.isDataAvailable = true;
      this.dataSource = new MatTableDataSource<Employee>(this.employees);
      this.len = Object.keys(e).length;
      console.log(this.dataSource);
      console.log(this.len);
    });
  }

  details(employeeId: number) {
    alert(employeeId);
  }

  ngAfterViewInit() {
    // this.dataSource.paginator = this.paginator;
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }
}
