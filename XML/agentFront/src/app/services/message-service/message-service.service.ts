import { Injectable } from '@angular/core';
import { ZuulPath } from 'app/ZuulPath';
import { AuthServiceService } from '../auth-service/auth-service.service';
import { HttpClient } from '@angular/common/http';
import { Message } from '../../model/Message';

@Injectable({
  providedIn: 'root'
})
export class MessageServiceService {

  constructor(private http: HttpClient, private zuulPath: ZuulPath, private auth: AuthServiceService) { }

  getMessagesForAgent(agent_id: number)
  {
    console.log("Usao u servis za dobijanje poruka: " + agent_id);
    return this.http.get(this.zuulPath.path + 'agent/message/getAgentMessages/'+agent_id, {headers: this.auth.createAuthorizationTokenHeader()});
  }
  sendAnswer(mm: Message){
    console.log("slanje odg  ");
    return this.http.post(this.zuulPath.path + 'agent/message/sendAnswer',mm, {headers: this.auth.createAuthorizationTokenHeader()});
  
  }
}
