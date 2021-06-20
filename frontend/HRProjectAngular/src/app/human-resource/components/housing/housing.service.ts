import { Injectable } from '@angular/core';
import { House } from './domain/house';
import { Observable } from 'rxjs';
// import { HOUSES } from './domain/mock-houses';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
  }),
};

@Injectable({
  providedIn: 'root',
})
export class HousingService {
  // houses: Observable<House[]> | undefined;

  constructor(private httpClient: HttpClient) {}

  getHouses(): Observable<House[]> {
    const url = 'http://localhost:8999/housing/get-houses';
    return this.httpClient.get<any>(url).pipe(map((houses) => houses.allHouse));
  }

  getHouseById(id: number): Observable<House> {
    const url2 = 'http://localhost:8999/housing/get-house/' + id;
    return this.httpClient.get<any>(url2).pipe(map((h) => h.house));
  }

  updateHouse(house: House): Observable<any> {
    const url3 = 'http://localhost:8999/housing/update-house';
    return this.httpClient.post<House>(url3, house, httpOptions);
  }

  // getFacilityListByHouseId(id: number): Observable<Facility[]> {}
}
