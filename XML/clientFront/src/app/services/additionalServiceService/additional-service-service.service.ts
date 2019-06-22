import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { AuthServiceService } from '../auth-service/auth-service.service';
import { ZuulPath } from 'src/app/ZuulPath';
@Injectable({
  providedIn: 'root'
})
export class AdditionalServiceServiceService {

  constructor(private http: HttpClient, private auth: AuthServiceService, private zuulPath: ZuulPath) { }

  getServices(){
    console.log('getServices u servisu');
    return this.http.get(this.zuulPath.path + 'megatravelxml/api/additionalServices/getServices', {headers: this.auth.createAuthorizationTokenHeader()});  
  }
}
