import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { Employee } from './domain/Employee';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class EmployeeService {
  constructor(private httpClient: HttpClient) {}

  getEmployeeList(): Observable<Employee[]> {
    const url = 'http://localhost:8999/profile/allSummary';
    return this.httpClient.get<any>(url).pipe(map((employees) => employees.AllSummary));
  }
}
