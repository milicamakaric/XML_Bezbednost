import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { AuthServiceService } from '../authService/auth-service.service';
import { ZuulPath } from 'app/ZuulPath';
import {Comment} from 'app/models/Comment'
@Injectable({
  providedIn: 'root'
})
export class CommentServiceService {

  constructor(private http: HttpClient, private zuulPath: ZuulPath, private auth: AuthServiceService) {
    

   }
   aproveComment(comm :Comment){
    console.log('Dodavanje nove dodatne usluge');
    return this.http.post(this.zuulPath.path + 'megatravelxml/api/comment/aprove', comm, {headers: this.auth.createAuthorizationTokenHeader()} );  
  
    
  }
}
