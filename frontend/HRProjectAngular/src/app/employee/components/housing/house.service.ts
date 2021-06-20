import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { House } from './domain/house';
import { FacilityReport } from './domain/facility-report';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
  }),
};

@Injectable({
  providedIn: 'root',
})
export class HouseService {
  constructor(private httpClient: HttpClient) {}

  getHouseInfoByUserId(id: number): Observable<House> {
    const url = 'http://localhost:8999/housing/get-user-house/' + id;
    return this.httpClient.get<House>(url);
  }

  getAllFacilityReportByHouseId(id: number): Observable<FacilityReport[]> {
    const url = 'http://localhost:8999/housing/get-facility-report/' + id;
    return this.httpClient.get<FacilityReport[]>(url);
  }

  postFacilityReportByUserId(fr: FacilityReport,id:number): Observable<FacilityReport> {
    const url = 'http://localhost:8999/facility-report/post-facility-report/'+ id;
    return this.httpClient.post<FacilityReport>(url, fr, httpOptions);
  }
}
