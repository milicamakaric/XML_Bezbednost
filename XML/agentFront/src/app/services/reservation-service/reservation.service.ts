import { Injectable } from '@angular/core';
import { AuthServiceService } from '../auth-service/auth-service.service';
import { ZuulPath } from 'app/ZuulPath';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Reservation } from '../../model/Reservation';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  constructor(private auth: AuthServiceService, private zuulPath: ZuulPath, private http: HttpClient) { 

  }
  addAgentReservation(res:Reservation){
    console.log(" dosao da doda agent res");
    return this.http.post(this.zuulPath.path + 'agent/reservation/addAgentReservation',res, {headers : this.auth.createAuthorizationTokenHeader()});
  
  }

  getAgentReservations(agent_id : number){
    console.log(" usao u getAgentReservations u reservation service");
    return this.http.get(this.zuulPath.path + 'agent/reservation/getAgentReservations/' + agent_id, {headers : this.auth.createAuthorizationTokenHeader()});
  
  }
}
