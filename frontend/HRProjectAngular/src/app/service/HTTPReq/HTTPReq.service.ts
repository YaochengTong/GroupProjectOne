import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'any'
})
export class HTTPReq {

  constructor(private http: HttpClient) { }

  getData(path: string, paramObj: any, endPoint: string = 'http://localhost:8999'): Observable<any> {
    return this.http.get(endPoint + path, {
      headers: {
        'Allow-Cross-Origin-Origin0': '*'
      },
      responseType: 'json',
      params: paramObj
    });
  }

  postData(path: string, paramObj: any, endPoint: string = 'http://localhost:8999'): Observable<any> {
    return this.http.post(endPoint + path, {
      headers: {
        'Allow-Cross-Origin-Origin0': '*'
      },
      responseType: 'json',
      params: paramObj
    });
  }
}
