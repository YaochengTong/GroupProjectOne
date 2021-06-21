import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { Employee } from './domain/Employee';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class EmployeeService {
  token: any;

  constructor(private httpClient: HttpClient) {
    this.token = localStorage.getItem('token')
      ? localStorage.getItem('token')
      : '';
  }

  getEmployeeList(): Observable<Employee[]> {
    const url = 'http://localhost:8999/profile/allSummary';
    return this.httpClient
      .get<any>(url, {
        headers: {
          'Allow-Cross-Origin-Origin0': '*',
          token: this.token,
        },
      })
      .pipe(map((employees) => employees.AllSummary));
  }
}
