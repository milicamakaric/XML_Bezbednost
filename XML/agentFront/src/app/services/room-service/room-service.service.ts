import { Injectable } from '@angular/core';
import { AuthServiceService } from '../auth-service/auth-service.service';
import { ZuulPath } from 'app/ZuulPath';

import { HttpClient, HttpParams } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class RoomServiceService {

  constructor(private auth: AuthServiceService, private zuulPath: ZuulPath, private http: HttpClient) { }

  getRooms(acc_id: number, ulogovan_id:number)
  {
    console.log("getRooms u servisu");
    return this.http.get(this.zuulPath.path + 'agent/room/getRooms/'+acc_id + '/' + ulogovan_id, {headers: this.auth.createAuthorizationTokenHeader()});
  }
}
