import { Injectable } from '@angular/core';
import { ZuulPath } from 'app/ZuulPath';
import { AuthServiceService } from '../auth-service/auth-service.service';

import { HttpClient, HttpParams } from '@angular/common/http';
import { Room } from 'app/model/Room';
@Injectable({
  providedIn: 'root'
})
export class AccommodationServiceService {

  constructor(private http: HttpClient, private zuulPath: ZuulPath, private auth: AuthServiceService) { }
  getAccommodations(id:number)
  {
    console.log(' smjestaj od agenta');

    return this.http.get(this.zuulPath.path + 'agent/accommodation/getAccommodations/'+id, {headers: this.auth.createAuthorizationTokenHeader()});
  }

  addAccommodationUnit(unit: Room){
    console.log('adding room');

    return this.http.get(this.zuulPath.path + 'agent/accommodation/addRoom/',unit, {headers: this.auth.createAuthorizationTokenHeader()});


  }

}

