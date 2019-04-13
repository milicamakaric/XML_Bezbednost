import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from 'src/app/models/User';
import {AuthServiceService} from 'src/app/services/authService/auth-service.service';

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {

  constructor(private http: HttpClient, private auth: AuthServiceService) { }

  addUser(u: User) {
    console.log('Usao u addUser');
    return this.http.post('//localhost:8080/api/users/registration', u );
  }

  loginUser(u: User) {
    console.log('Usao u loginUser');
    return this.http.post('//localhost:8080/api/users/login', u, {headers: this.auth.createAuthorizationTokenHeader()});
  }

  getSelfSigned() {
    return this.http.get('//localhost:8080/api/softwares/getSelfSigned');
  }

  getLogged(token: string) {
    return this.http.post('//localhost:8080/api/users/userprofile', token);
  }
}
