import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ZuulPath } from 'src/app/ZuulPath';
import { AuthServiceService } from '../auth-service/auth-service.service';
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
}
