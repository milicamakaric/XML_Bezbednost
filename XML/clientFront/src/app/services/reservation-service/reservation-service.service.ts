import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ZuulPath } from 'src/app/ZuulPath';
import { AuthServiceService } from '../auth-service/auth-service.service';
import {Reservation} from '../../models/Reservation';
@Injectable({
  providedIn: 'root'
})
export class ReservationServiceService {

  constructor(private http: HttpClient, private zuulPath: ZuulPath, private auth: AuthServiceService) { }

  getUserReservations(id:number)
  {
    console.log("Usao po rezervacije od korisnika");
    return this.http.get(this.zuulPath.path + 'megatravelxml/api/reservation/getUserReservation/'+id, {headers: this.auth.createAuthorizationTokenHeader()});
  }
  checkCancelationUser(id:number){
    console.log("Usao u check usera");
   
    return this.http.get(this.zuulPath.path + 'megatravelxml/api/reservation/checkCancelation/'+id, {headers: this.auth.createAuthorizationTokenHeader()});
 
  }
  cancelReservation(res :Reservation){
    console.log('otkazivanje rez');
    return this.http.post(this.zuulPath.path + 'megatravelxml/api/reservation/cancelReservation', res, {headers: this.auth.createAuthorizationTokenHeader()});
  
  }
  reserve(res:Reservation,idRoom : number,idClient : number){
    console.log('dosao da rezervise');
    //return  this.http.post(this.zuulPath.path + 'megatravelxml/api/reservation/reserve', res, {headers: this.auth.createAuthorizationTokenHeader()});
    return this.http.post(this.zuulPath.path + 'megatravelxml/api/reservation/reserve/'+idRoom+"/"+idClient, res, {headers: this.auth.createAuthorizationTokenHeader()});
  
  }
}
