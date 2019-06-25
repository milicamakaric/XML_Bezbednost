import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AuthServiceService } from '../auth-service/auth-service.service';
import { ZuulPath } from 'src/app/ZuulPath';

@Injectable({
  providedIn: 'root'
})
export class RoomServiceService {

  constructor(private http: HttpClient, private auth: AuthServiceService, private zuulPath: ZuulPath) { }

  getAgentByRoomId(room_id: number){
    console.log('getAgent u servisu');
    return this.http.get(this.zuulPath.path + 'megatravelxml/api/room/getAgentOfRoom/' + room_id, {headers: this.auth.createAuthorizationTokenHeader()});
  }
}
