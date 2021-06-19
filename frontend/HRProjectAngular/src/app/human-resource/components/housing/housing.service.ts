import { Injectable } from '@angular/core';
import { House } from './domain/house';
import { Observable } from 'rxjs';
// import { HOUSES } from './domain/mock-houses';
import { HTTPReq } from '../../../service/HTTPReq/HTTPReq.service';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class HousingService {
  // houses: Observable<House[]> | undefined;

  constructor(private hr: HTTPReq, private httpClient: HttpClient) {}

  getHouses(): Observable<House[]> {
    this.hr.getData('/housing/get-houses', null).subscribe((data: any) => {
      console.log(data.allHouse);
    });

    const url = 'http://localhost:8999/housing/get-houses';
    console.log(
      this.httpClient
        .get<any>(url)
        .pipe(map((houses) => houses.allHouse))
        .subscribe((houses) => houses)
    );
    return this.httpClient.get<any>(url).pipe(map((houses) => houses.allHouse));
  }

  getHouseById(id: number): Observable<House> {
    const url2 = 'http://localhost:8999/housing/get-house/' + id;
    return this.httpClient.get<any>(url2).pipe(map((h) => h.house));
  }

  // getFacilityListByHouseId(id: number): Observable<Facility[]> {}
}
