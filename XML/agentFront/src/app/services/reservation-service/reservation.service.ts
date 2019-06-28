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
  
  canBeActive(res_id : number){
    console.log(" usao u canBeActive u reservation service");
    return this.http.get(this.zuulPath.path + 'agent/reservation/canBeActive/' + res_id, {headers : this.auth.createAuthorizationTokenHeader()});
  
  }

  canBeFinished(res_id : number){
    console.log(" usao u canBeFinished u reservation service");
    return this.http.get(this.zuulPath.path + 'agent/reservation/canBeFinished/' + res_id, {headers : this.auth.createAuthorizationTokenHeader()});
  
  }

  setActive(id: number){
    return this.http.post(this.zuulPath.path + 'agent/reservation/setActive', id, {headers : this.auth.createAuthorizationTokenHeader()});
  }

  setFinish(id: number){
    return this.http.post(this.zuulPath.path + 'agent/reservation/setFinish', id, {headers : this.auth.createAuthorizationTokenHeader()});
  }
}
