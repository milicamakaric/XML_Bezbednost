import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CertificateServiceService {

  constructor(private http: HttpClient) { }

  createSelfCertificate(id_issuer: string, startDate: string, endDate: string){
    console.log('creating self certificate...');
    return this.http.post('//localhost:8080/api/certificates/createSelfSigned/'+startDate+'/'+endDate, id_issuer);
  }

  showCertificates(){
    console.log('show certificate');
    return this.http.get('//localhost:8080/api/certificates/allDTO');
  }
}