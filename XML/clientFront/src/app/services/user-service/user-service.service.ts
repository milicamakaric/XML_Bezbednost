import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { AuthServiceService } from '../auth-service/auth-service.service';
import { User } from 'src/app/models/User';
import { ClientPath } from 'src/app/ClientPath';
import { AuthPath } from 'src/app/AuthPath';


@Injectable({
  providedIn: 'root'
})
export class UserServiceService {

  constructor(private http: HttpClient, private auth: AuthServiceService, private clientPath: ClientPath, private authPath: AuthPath) { }

  addUser(u: User) {
    console.log('Usao u addUser');
    return this.http.post(this.clientPath.path + 'api/users/registration', u );
  }

  loginUser(u: User) {
    console.log('Usao u loginUser');
    let user={
      "username": u.email,
      "password": u.password
    };
    console.log("user: " + user);
    return this.http.post(this.authPath.path + 'auth/login', user, {headers: this.auth.createAuthorizationTokenHeader()});
  }

  getLogged(token: string) {
    console.log("token: " + token);
    return this.http.post(this.clientPath.path + 'api/mainSecurity/userprofile', token, {headers: this.auth.createAuthorizationTokenHeader()});
  }

  logOut() {
    return this.http.get(this.authPath.path  + 'auth/logout', {headers: this.auth.createAuthorizationTokenHeader()});
  }

}
