// import { Component, OnInit } from '@angular/core';

// export interface Info {
//   name: string;
//   ssn: number;
//   startingDate: string;
//   visaStatus: string;
//   order: number;
//   total: number;
// }

// const ELEMENT_DATA: Info[] = [
//   {
//     name: 'One',
//     ssn: 123456,
//     startingDate: '2021-12-13 12:00:00',
//     visaStatus: 'OPT',
//     order: 1,
//     total: 100,
//   },
//   {
//     name: 'Two',
//     ssn: 222222,
//     startingDate: '2021-12-15 12:00:00',
//     visaStatus: 'H1B',
//     order: 2,
//     total: 100,
//   },
//   {
//     name: 'Three',
//     ssn: 166666,
//     startingDate: '2021-12-16 12:00:00',
//     visaStatus: 'OPT Extension',
//     order: 1,
//     total: 100,
//   },
//   {
//     name: 'Four',
//     ssn: 1677777,
//     startingDate: '2020-02-13 12:00:00',
//     visaStatus: 'F1',
//     order: 3,
//     total: 100,
//   },
//   {
//     name: 'Five',
//     ssn: 888888,
//     startingDate: '2019-10-13 12:00:00',
//     visaStatus: 'OPT',
//     order: 4,
//     total: 100,
//   },
//   {
//     name: 'Six',
//     ssn: 1654421,
//     startingDate: '2021-12-24 12:00:00',
//     visaStatus: 'H1B',
//     order: 5,
//     total: 100,
//   },
//   {
//     name: 'Seven',
//     ssn: 19876123,
//     startingDate: '2021-12-05 12:00:00',
//     visaStatus: 'OPT',
//     order: 6,
//     total: 100,
//   },
//   {
//     name: 'Eight',
//     ssn: 12355421,
//     startingDate: '2021-08-13 12:00:00',
//     visaStatus: 'OPT',
//     order: 7,
//     total: 100,
//   },
// ];

// @Component({
//   selector: 'app-check-employee',
//   templateUrl: './check-employee.component.html',
//   styleUrls: ['./check-employee.component.scss'],
// })
// export class CheckEmployeeComponent implements OnInit {
//   constructor() {}

//   displayedColumns: string[] = [
//     'name',
//     'ssn',
//     'startingDate',
//     'visaStatus',
//     'order',
//     'total',
//   ];
//   dataSource = ELEMENT_DATA;

//   ngOnInit(): void {}
// }


import {HttpClient} from '@angular/common/http';
import {Component, ViewChild, AfterViewInit} from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {merge, Observable, of as observableOf} from 'rxjs';
import {catchError, map, startWith, switchMap} from 'rxjs/operators';

@Component({
  selector: 'app-check-employee',
  templateUrl: './check-employee.component.html',
  styleUrls: ['./check-employee.component.scss'],
})
export class CheckEmployeeComponent  implements AfterViewInit {
displayedColumns: string[] = ['created', 'state', 'number', 'title'];
exampleDatabase: ExampleHttpDatabase | null | undefined;
data: GithubIssue[] = [];
resultsLength = 0;
isLoadingResults = true;
isRateLimitReached = false;
  @ViewChild(MatPaginator)
  paginator!: MatPaginator;
  @ViewChild(MatSort)
  sort!: MatSort;
constructor(private _httpClient: HttpClient) {}
ngAfterViewInit() {
this.exampleDatabase = new ExampleHttpDatabase(this._httpClient);
// If the user changes the sort order, reset back to the first page.
this.sort.sortChange.subscribe(() => this.paginator.pageIndex = 0);
merge(this.sort.sortChange, this.paginator.page)
.pipe(
startWith({}),
switchMap(() => {
this.isLoadingResults = true;
return this.exampleDatabase!.getRepoIssues(
this.sort.active, this.sort.direction, this.paginator.pageIndex);
}),
map(data => {
// Flip flag to show that loading has finished.
this.isLoadingResults = false;
this.isRateLimitReached = false;
this.resultsLength = data.total_count;
return data.items;
}),
catchError(() => {
this.isLoadingResults = false;
// Catch if the GitHub API has reached its rate limit. Return empty data.
this.isRateLimitReached = true;
return observableOf([]);
})
).subscribe(data => this.data = data);
}
}
export interface GithubApi {
items: GithubIssue[];
total_count: number;
}
export interface GithubIssue {
created_at: string;
number: string;
state: string;
title: string;
}
/** An example database that the data source uses to retrieve data for the table. */
export class ExampleHttpDatabase {
constructor(private _httpClient: HttpClient) {}
getRepoIssues(sort: string, order: string, page: number): Observable<GithubApi> {
const href = 'https://api.github.com/search/issues';
const requestUrl =
`${href}?q=repo:angular/components&sort=${sort}&order=${order}&page=${page + 1}`;
return this._httpClient.get<GithubApi>(requestUrl);
}
}
