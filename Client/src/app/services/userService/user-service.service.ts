import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from 'src/app/models/User';

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {

  constructor(private http: HttpClient) { }

  addUser(u: User) {
    console.log('Usao u addUser');
    return this.http.post('//localhost:8080/api/users/registration', u );
  }

  loginUser(u: User) {
    console.log('Usao u loginUser');
    return this.http.post('//localhost:8080/api/users/login', u);
  }

  getSelfSigned(){
    return this.http.get('//localhost:8080/api/softwares/getSelfSigned');
  }

  getLogged(){
    return this.http.get('//localhost:8080/api/users/user');
  }
}
