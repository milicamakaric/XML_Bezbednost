import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
@Injectable({
  providedIn: 'root'
})
export class AccommodationServiceService {

  

  constructor(private http: HttpClient) {
    
  }

  getTypes(){

    const httpOptions = {
      headers: new HttpHeaders({ 
        'Access-Control-Allow-Origin':'*'
      })
    };

    console.log('getTypes u servisu');
    return this.http.get('http://localhost:8761/megatravel-xml/api/accommodation/getTypes', httpOptions);
  }
}
