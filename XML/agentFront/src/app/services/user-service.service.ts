import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { AgentPath } from '../../AgentPath';
import { AuthServiceService } from '../services/auth-service.service';
import {User} from '../model/User';

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {

  constructor(private http: HttpClient, private auth: AuthServiceService, private agentPath:AgentPath) { }
  loginUser(u: User) {
    console.log('Usao u loginUser');
     return this.http.post(this.agentPath.path + 'api/users/login', u, {headers: this.auth.createAuthorizationTokenHeader()});
  }

}
