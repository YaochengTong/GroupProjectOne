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
    let token:any = localStorage.getItem('token')?localStorage.getItem('token'):'';
    return this.httpClient.get<any>(url, {
      headers: {
        'Allow-Cross-Origin-Origin0': '*',
        'token': token
      },
    }).pipe(map((houses) => houses.allHouse));
  }

  getHouseById(id: number): Observable<House> {
    let token:any = localStorage.getItem('token')?localStorage.getItem('token'):'';
    const url2 = 'http://localhost:8999/housing/get-house/' + id;
    return this.httpClient.get<any>(url2, {
      headers: {
        'Allow-Cross-Origin-Origin0': '*',
        'token': token
      },
    }).pipe(map((h) => h.house));
  }

  updateHouse(house: House): Observable<any> {
    let token:any = localStorage.getItem('token')?localStorage.getItem('token'):'';
    const url3 = 'http://localhost:8999/housing/update-house';
    return this.httpClient.post<House>(url3, house, {
      headers: {
        'Allow-Cross-Origin-Origin0': '*',
        'Content-Type': 'application/json',
        'token': token
      },
    });
  }

  // getFacilityListByHouseId(id: number): Observable<Facility[]> {}
}
