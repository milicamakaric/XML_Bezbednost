import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AgentServiceService {

  constructor(private http: HttpClient) { }

  communicate(text: string){
    console.log('Usao u communicate');
    return this.http.post('https://localhost:8442/api/agent/communicate', text, {responseType: 'text'});
  }
}
