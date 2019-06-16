import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../../models/User';
import {AuthServiceService} from '../authService/auth-service.service';

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {

  constructor(private http: HttpClient, private auth: AuthServiceService) { }

  addUser(u: User) {
    console.log('Usao u addUser');
    return this.http.post('https://localhost:8443/api/users/registration', u );
  }

  loginUser(u: User) {
    console.log('Usao u loginUser');
    return this.http.post('https://localhost:8443/api/users/login', u, {headers: this.auth.createAuthorizationTokenHeader()});
  }

  getSelfSigned() {
    return this.http.get('https://localhost:8443/api/softwares/getSelfSigned', {headers: this.auth.createAuthorizationTokenHeader()});
  }

  getLogged(token: string) {
    return this.http.post('https://localhost:8443/api/users/userprofile', token, {headers: this.auth.createAuthorizationTokenHeader()});
  }

  getCertificatedUsers(): Observable<any>
  {
    console.log('get certificated users');
    return this.http.get('https://localhost:8443/api/users/allCertificatedUsers', {headers: this.auth.createAuthorizationTokenHeader()});
  }

  logOut() {
    return this.http.get('https://localhost:8443/api/users/logout', {headers: this.auth.createAuthorizationTokenHeader()});
  }

  changeToCertificatedUser(param : string){
    console.log('change to certificated user');
    return this.http.post('https://localhost:8443/api/users/changetocertificated',param,  {headers: this.auth.createAuthorizationTokenHeader()});

  }

  rateOurApp(stars: number)
  {
    console.log('Rate our app');
    return this.http.post('https://localhost:8443/api/users/rateUs',stars, {headers: this.auth.createAuthorizationTokenHeader()});

  }
}
