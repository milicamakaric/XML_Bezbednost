import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
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
}
