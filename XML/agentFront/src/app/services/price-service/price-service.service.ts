import { Injectable } from '@angular/core';
import { AuthServiceService } from '../auth-service/auth-service.service';
import { ZuulPath } from 'app/ZuulPath';
import { HttpClient, HttpParams } from '@angular/common/http';
import { PriceForNight } from 'app/model/PriceForNight';

@Injectable({
  providedIn: 'root'
})
export class PriceServiceService {

  constructor(private auth: AuthServiceService, private zuulPath: ZuulPath, private http: HttpClient) { }
  addSpecialPrice(sp:PriceForNight,id:number){
    
    console.log('adding special price'); 
    console.log("Price: " + sp + " id " + id); 
    return this.http.post(this.zuulPath.path + 'agent/price/addSpecialPrice/'+id,sp, {headers : this.auth.createAuthorizationTokenHeader()});
  
  }
}
