import { Injectable } from '@angular/core';
import { Message } from 'src/app/models/Message';
import { HttpClient } from '@angular/common/http';
import { ZuulPath } from 'src/app/ZuulPath';
import { AuthServiceService } from '../auth-service/auth-service.service';

@Injectable({
  providedIn: 'root'
})
export class MessageServiceService {

  constructor(private http: HttpClient, private zuulPath: ZuulPath, private auth: AuthServiceService) { }

  sendAnswer(mm: Message){
    console.log("slanje odg  ");
    return this.http.post(this.zuulPath.path + 'megatravelxml/api/message/sendMessage',mm, {headers: this.auth.createAuthorizationTokenHeader()});
  
  }
}
