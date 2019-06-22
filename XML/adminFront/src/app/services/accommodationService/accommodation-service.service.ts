import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { AdminPath } from '../../AdminPath';
import { AccommodationType } from '../../models/AccommodationType';
import { AuthServiceService } from '../authService/auth-service.service';
import { ListAgents } from 'app/models/ListAgents';
import { stringify } from 'querystring';
@Injectable({
  providedIn: 'root'
})
export class AccommodationServiceService {

  constructor(private http: HttpClient,private adminPath: AdminPath, private auth: AuthServiceService) { }

  addAccommodation(a:AccommodationType){
    console.log('Dodavanje novog  smjestaja ');
    return this.http.post(this.adminPath.path + 'api/accommodation/addNewAccommodation', a, {headers: this.auth.createAuthorizationTokenHeader()} );  
  
  }
  addAccommodationType(a : AccommodationType){
    console.log('Dodavanje novog tipa smjestaja ');
    return this.http.post(this.adminPath.path + 'api/accommodation/addNewAccommodationType', a, {headers: this.auth.createAuthorizationTokenHeader()} );  
  }

  getTypes(){
    console.log('getTypes u servisu');
    return this.http.get(this.adminPath.path + 'api/accommodation/getTypes', {headers: this.auth.createAuthorizationTokenHeader()});
  }

  getAccommodations()
  {
    console.log('getAccommodations u servisu');
    return this.http.get(this.adminPath.path + 'api/accommodation/getAll', {headers: this.auth.createAuthorizationTokenHeader()});
  }

  addAgentToAccommodation(id: number, listOfAgents: Array<string>) {
    // tslint:disable-next-line:max-line-length
    console.log(id);
    console.log('making request');
    var param = "";
    console.log(listOfAgents);
    for (var agentId of listOfAgents ) {
      console.log(agentId);
          param += agentId;
          param += '=';
    }
    console.log(param);
    
    // tslint:disable-next-line:max-line-length
    return this.http.post(this.adminPath.path + 'api/accommodation/addAgentsToAccommodation/' + id + '/' + param, {headers: this.auth.createAuthorizationTokenHeader()});
    // tslint:disable-next-line:max-line-length
    //return this.http.post(this.adminPath.path + 'api/accommodation/addAgentsToAccommodation/' + id , param, {headers: this.auth.createAuthorizationTokenHeader()});

  }
}
