import { Component, OnInit } from '@angular/core';

export interface Info {
  name: string;
  ssn: number;
  startingDate: string;
  visaStatus: string;
  order: number;
  total: number;
}

const ELEMENT_DATA: Info[] = [
  {name: "One", ssn: 123456, startingDate: "2021-12-13 12:00:00", visaStatus: "OPT", order: 1, total: 100},
  {name: "Two", ssn: 222222, startingDate: "2021-12-15 12:00:00", visaStatus: "H1B", order: 2, total: 100},
  {name: "Three", ssn: 166666, startingDate: "2021-12-16 12:00:00", visaStatus: "OPT Extension", order: 1, total: 100},
  {name: "Four", ssn: 1677777, startingDate: "2020-02-13 12:00:00", visaStatus: "F1", order: 3, total: 100},
  {name: "Five", ssn: 888888, startingDate: "2019-10-13 12:00:00", visaStatus: "OPT", order: 4, total: 100},
  {name: "Six", ssn: 1654421, startingDate: "2021-12-24 12:00:00", visaStatus: "H1B", order: 5, total: 100},
  {name: "Seven", ssn: 19876123, startingDate: "2021-12-05 12:00:00", visaStatus: "OPT", order: 6, total: 100},
  {name: "Eight", ssn: 12355421, startingDate: "2021-08-13 12:00:00", visaStatus: "OPT", order: 7, total: 100},

]

@Component({
  selector: 'app-check-employee',
  templateUrl: './check-employee.component.html',
  styleUrls: ['./check-employee.component.scss'],
})
export class CheckEmployeeComponent implements OnInit {
  
  
  
  constructor() {}

   displayedColumns: string[] = ['name', 'ssn', 'startingDate', 'visaStatus', 'order', 'total'];
  dataSource = ELEMENT_DATA;

  ngOnInit(): void {}
}
