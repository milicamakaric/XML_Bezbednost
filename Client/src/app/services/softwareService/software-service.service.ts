import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {AuthServiceService} from '../authService/auth-service.service';


@Injectable({
  providedIn: 'root'
})
export class SoftwareServiceService {

  constructor(private http: HttpClient, private auth: AuthServiceService) { }

  getSoftwares(): Observable<any> {
    return this.http.get('//localhost:8080/api/softwares/getAll', {headers: this.auth.createAuthorizationTokenHeader()});
  }
}
