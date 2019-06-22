import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { AuthServiceService } from '../auth-service/auth-service.service';
import { User } from 'src/app/models/User';
import { ZuulPath } from 'src/app/ZuulPath';


@Injectable({
  providedIn: 'root'
})
export class UserServiceService {

  constructor(private http: HttpClient, private auth: AuthServiceService, private zuulPath: ZuulPath) { }

  addUser(u: User) {
    console.log('Usao u addUser');
    return this.http.post(this.zuulPath.path + 'megatravelxml/api/users/registration', u );
  }

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
    return this.http.post(this.zuulPath.path + 'megatravelxml/api/mainSecurity/userprofile', token, {headers: this.auth.createAuthorizationTokenHeader()});
  }

  logOut() {
    return this.http.get(this.zuulPath.path  + 'authservice/auth/logout', {headers: this.auth.createAuthorizationTokenHeader()});
  }

}
