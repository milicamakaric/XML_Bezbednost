import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { AdminPath } from '../../AdminPath';
import {AdditionalService } from '../../models/AdditionalService';
import { AuthServiceService } from '../authService/auth-service.service';
@Injectable({
  providedIn: 'root'
})
export class AdditionalServiceServiceService {

  constructor(private http: HttpClient,private adminPath: AdminPath, private auth: AuthServiceService) { }

  addAdditionalService(a:AdditionalService){
    console.log('Dodavanje nove dodatne usluge');
    return this.http.post(this.adminPath.path + 'api/additionalServices/addNew', a, {headers: this.auth.createAuthorizationTokenHeader()} );  
  }

  getServices(){
    console.log('getServices u servisu');
    return this.http.get(this.adminPath.path + 'api/additionalServices/getServices', {headers: this.auth.createAuthorizationTokenHeader()});  
  }
}
