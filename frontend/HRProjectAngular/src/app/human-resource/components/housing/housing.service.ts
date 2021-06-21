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
  token: any;

  constructor(private httpClient: HttpClient) {
    this.token = localStorage.getItem('token')
      ? localStorage.getItem('token')
      : '';
  }

  getHouses(): Observable<House[]> {
    const url = 'http://localhost:8999/housing/get-houses';
    return this.httpClient
      .get<any>(url, {
        headers: {
          'Allow-Cross-Origin-Origin0': '*',
          token: this.token,
        },
      })
      .pipe(map((houses) => houses.allHouse));
  }

  getHouseById(id: number): Observable<House> {
    const url2 = 'http://localhost:8999/housing/get-house/' + id;
    return this.httpClient
      .get<any>(url2, {
        headers: {
          'Allow-Cross-Origin-Origin0': '*',
          token: this.token,
        },
      })
      .pipe(map((h) => h.house));
  }

  updateHouse(house: House): Observable<any> {
    const url3 = 'http://localhost:8999/housing/update-house';
    return this.httpClient.post<House>(url3, house, {
      headers: {
        'Allow-Cross-Origin-Origin0': '*',
        'Content-Type': 'application/json',
        token: this.token,
      },
    });
  }

  addHouse(house: House, id: number): Observable<House> {
    const url = 'http://localhost:8999/housing/add-house/' + id;
    return this.httpClient.post<House>(url, house, {
      headers: {
        'Allow-Cross-Origin-Origin0': '*',
        'Content-Type': 'application/json',
        token: this.token,
      },
    });
  }

  // getFacilityListByHouseId(id: number): Observable<Facility[]> {}
}
