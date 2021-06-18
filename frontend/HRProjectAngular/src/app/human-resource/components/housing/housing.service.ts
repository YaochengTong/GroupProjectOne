import { Injectable } from '@angular/core';
import { House } from './domain/house';
import { Observable, of } from 'rxjs';
import { HOUSES } from './domain/mock-houses';
import { HTTPReq } from '../../../service/HTTPReq/HTTPReq.service';

@Injectable({
  providedIn: 'root',
})
export class HousingService {
  constructor() {}



  getHouses(): Observable<House[]> {
    return of(HOUSES);
  }

  getHouseById(id: number): Observable<House> {
    const house = HOUSES.find((h) => h.id === id)!;
    return of(house);
  }
}
