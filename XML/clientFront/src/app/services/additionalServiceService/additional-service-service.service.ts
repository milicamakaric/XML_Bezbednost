import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
@Injectable({
  providedIn: 'root'
})
export class AdditionalServiceServiceService {

  constructor(private http: HttpClient) { }

  getServices(){
    console.log('getServices u servisu');
    return this.http.get('http://localhost:8080/api/additionalServices/getServices');  
  }
}
