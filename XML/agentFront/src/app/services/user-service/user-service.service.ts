import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { AuthServiceService } from '../auth-service/auth-service.service';
import { User } from 'app/model/User';
import { ZuulPath } from 'app/ZuulPath';



@Injectable({
  providedIn: 'root'
})
export class UserServiceService {

  constructor(private http: HttpClient, private auth: AuthServiceService, private zuulPath: ZuulPath) { }


  loginUser(u: User) {
    console.log('Usao u loginUser');
    let user={
      "username": u.email,
      "password": u.password
    };
    console.log("user: " + user);
    return this.http.post(this.zuulPath.path + 'authservice/auth/login', user, {headers: this.auth.createAuthorizationTokenHeader()});
  }

  getLogged(token: string) {
    console.log("token: " + token);
    return this.http.post(this.zuulPath.path + 'agent/agentSecurity/userprofile', token, {headers: this.auth.createAuthorizationTokenHeader()});
  }

  logOut() {
    return this.http.get(this.zuulPath.path  + 'authservice/auth/logout', {headers: this.auth.createAuthorizationTokenHeader()});
  }

}
