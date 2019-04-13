import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CertificateServiceService {

  constructor(private http: HttpClient) { }

  createSelfCertificate(idIssuer: string, startDate: string, endDate: string) {
    console.log('creating self certificate...');
    return this.http.post('//localhost:8080/api/certificates/createSelfSigned/' + startDate + '/' + endDate, idIssuer);
  }
  createNonSelfCertificate(idSubject: string, startDate: string, endDate: string, author: string) {
    console.log('creating nonself certificate...');
    return this.http.post('//localhost:8080/api/certificates/create/' + idSubject + '/' + startDate + '/' + endDate, author);
  }

  showCertificates(){
    console.log('show certificate');
    return this.http.get('//localhost:8080/api/certificates/allDTO');
  }
}