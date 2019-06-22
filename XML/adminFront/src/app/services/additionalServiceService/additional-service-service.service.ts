import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import {AdditionalService } from '../../models/AdditionalService';
import { AuthServiceService } from '../authService/auth-service.service';
import { ZuulPath } from 'app/ZuulPath';

@Injectable({
  providedIn: 'root'
})
export class AdditionalServiceServiceService {

  constructor(private http: HttpClient, private zuulPath: ZuulPath, private auth: AuthServiceService) { }

  addAdditionalService(a:AdditionalService){
    console.log('Dodavanje nove dodatne usluge');
    return this.http.post(this.zuulPath.path + 'megatravelxml/api/additionalServices/addNew', a, {headers: this.auth.createAuthorizationTokenHeader()} );  
  }

  getServices(){
    console.log('getServices u servisu');
    return this.http.get(this.zuulPath.path + 'megatravelxml/api/additionalServices/getServices', {headers: this.auth.createAuthorizationTokenHeader()});  
  }
}
