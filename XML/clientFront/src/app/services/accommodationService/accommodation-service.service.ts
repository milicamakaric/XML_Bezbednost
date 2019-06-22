import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { AuthServiceService } from '../auth-service/auth-service.service';
import { ZuulPath } from 'src/app/ZuulPath';
@Injectable({
  providedIn: 'root'
})
export class AccommodationServiceService {

  

  constructor(private http: HttpClient, private auth: AuthServiceService, private zuulPath: ZuulPath) {
    
  }

  getTypes(){
    console.log('getTypes u servisu');
    return this.http.get(this.zuulPath.path + 'megatravelxml/api/accommodation/getTypes', {headers: this.auth.createAuthorizationTokenHeader()});
  }
}
