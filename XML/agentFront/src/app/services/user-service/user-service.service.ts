import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { AuthServiceService } from '../auth-service/auth-service.service';
import { User } from 'app/model/User';
import { AgentPath } from 'AgentPath';
import { AuthPath } from 'AuthPath';



@Injectable({
  providedIn: 'root'
})
export class UserServiceService {

  constructor(private http: HttpClient, private auth: AuthServiceService, private agentPath: AgentPath, private authPath: AuthPath) { }


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
    return this.http.post(this.agentPath.path + 'agentSecurity/userprofile', token, {headers: this.auth.createAuthorizationTokenHeader()});
  }

  logOut() {
    return this.http.get(this.authPath.path  + 'auth/logout', {headers: this.auth.createAuthorizationTokenHeader()});
  }

}
