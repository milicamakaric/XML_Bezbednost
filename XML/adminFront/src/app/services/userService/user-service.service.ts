import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../../models/User';
import {AuthServiceService} from '../authService/auth-service.service';
import { AdminPath } from '../../AdminPath';
import { AuthPath } from '../../AuthPath';
import { Agent } from 'app/models/Agent';

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {

  constructor(private http: HttpClient, private auth: AuthServiceService, private adminPath: AdminPath, private authPath: AuthPath) { }

  addUser(u: User) {
    console.log('Usao u addUser');
    return this.http.post(this.adminPath.path + 'api/users/registration', u );
  }

  loginUser(u: User) {
    console.log('Usao u loginUser');
    let user={
      "username": u.email,
      "password": u.passsword
    };
    console.log("user: " + user);
    return this.http.post(this.authPath.path + 'auth/login', user, {headers: this.auth.createAuthorizationTokenHeader()});
  }
/*
  getSelfSigned() {
    return this.http.get(this.adminPath.path + 'api/softwares/getSelfSigned', {headers: this.auth.createAuthorizationTokenHeader()});
  }*/

  getLogged(token: string) {
    console.log("token: " + token);
    return this.http.post(this.adminPath.path + 'api/mainSecurity/userprofile', token, {headers: this.auth.createAuthorizationTokenHeader()});
  }
<<<<<<< HEAD
=======

  addAgent(agent: Agent) {
    console.log('Usao u addAgent');
    return this.http.post(this.adminPath.path + 'api/users/addAgent', agent);
  }

  getUsers(){
    console.log('usao u getUsers');
    return this.http.get(this.adminPath.path + 'api/users/getUsers');
  }

  activateUser(id: number){
    console.log('usao u activateUser');
    return this.http.put(this.adminPath.path + 'api/users/activateUser', id);
  }

  blockUser(id: number){
    console.log('usao u blockUser');
    return this.http.put(this.adminPath.path + 'api/users/blockUser', id);
  }

  deleteUser(id: number){
    console.log('usao u deleteUser');
    return this.http.put(this.adminPath.path + 'api/users/deleteUser', id);
  }

/*
  getCertificatedUsers(): Observable<any>
  {
    console.log('get certificated users');
    return this.http.get(this.adminPath.path + 'api/users/allCertificatedUsers', {headers: this.auth.createAuthorizationTokenHeader()});
  }
>>>>>>> 9ee684a96cfa6e167bf79716894a23274239ff1c

  logOut() {
    return this.http.get(this.authPath.path + 'auth/logout', {headers: this.auth.createAuthorizationTokenHeader()});
  }

  
}

