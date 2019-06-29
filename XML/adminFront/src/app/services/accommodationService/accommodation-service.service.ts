import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { AccommodationType } from '../../models/AccommodationType';
import { AuthServiceService } from '../authService/auth-service.service';
import { ListAgents } from 'app/models/ListAgents';
import { stringify } from 'querystring';
import { ZuulPath } from 'app/ZuulPath';
import { ImageAcc } from 'app/models/ImageAcc';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class AccommodationServiceService {

  constructor(private http: HttpClient, private zuulPath: ZuulPath, private auth: AuthServiceService) { }

  addAccommodation(a:AccommodationType){
    console.log('Dodavanje novog  smjestaja ');
    return this.http.post(this.zuulPath.path + 'megatravelxml/api/accommodation/addNewAccommodation', a, {headers: this.auth.createAuthorizationTokenHeader()} );  
  
  }
  addAccommodationType(a : AccommodationType){
    console.log('Dodavanje novog tipa smjestaja ');
    return this.http.post(this.zuulPath.path + 'megatravelxml/api/accommodation/addNewAccommodationType', a, {headers: this.auth.createAuthorizationTokenHeader()} );  
  }
  addImages(id: number, images: Array<ImageAcc>): Observable<any> {
    console.log('addImages function');
    console.log('Id jee' + id);
    let data = new FormData();
    for(let i = 0; i < images.length; i++)
    {
      data.append("images", images[i].data);
    }
    console.log(data.getAll("images"));
    // tslint:disable-next-line:max-line-length
    return this.http.post(this.zuulPath.path + 'megatravelxml/api/accommodation/addImage/' + id, data, {headers: this.auth.createAuthorizationTokenHeader()} );
    }

  getTypes(){
    console.log('getTypes u servisu');
    return this.http.get(this.zuulPath.path + 'megatravelxml/api/accommodation/getTypes', {headers: this.auth.createAuthorizationTokenHeader()});
  }

  getAccommodations()
  {
    console.log('getAccommodations u servisu');
    return this.http.get(this.zuulPath.path + 'megatravelxml/api/accommodation/getAll', {headers: this.auth.createAuthorizationTokenHeader()});
  }

  getAccommodationComments(id :number)
  {
    console.log('getComments u servisu');
    return this.http.get(this.zuulPath.path + 'megatravelxml/api/accommodation/getComments/'+id, {headers: this.auth.createAuthorizationTokenHeader()});
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
    return this.http.post(this.zuulPath.path + 'megatravelxml/api/accommodation/addAgentsToAccommodation/' + id + '/' + param, {headers: this.auth.createAuthorizationTokenHeader()});
    // tslint:disable-next-line:max-line-length
    //return this.http.post(this.adminPath.path + 'api/accommodation/addAgentsToAccommodation/' + id , param, {headers: this.auth.createAuthorizationTokenHeader()});

  }
}
