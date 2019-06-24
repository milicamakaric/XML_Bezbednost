import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { AuthServiceService } from './auth-service/auth-service.service';
import {User} from '../model/User';

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {

  constructor(private http: HttpClient, private auth: AuthServiceService) { }
  loginUser(u: User) {
    console.log('Usao u loginUser u pogresnom servisu');
     //return this.http.post(this.agentPath.path + 'api/users/login', u, {headers: this.auth.createAuthorizationTokenHeader()});
  }

}
