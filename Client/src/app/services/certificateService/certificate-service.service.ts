import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {AuthServiceService} from '../authService/auth-service.service';


@Injectable({
  providedIn: 'root'
})
export class CertificateServiceService {

  constructor(private http: HttpClient, private auth: AuthServiceService) { }

  createSelfCertificate(idIssuer: string, startDate: string, endDate: string) {
    console.log('creating self certificate...');
    return this.http.post('http://localhost:8443/api/certificates/createSelfSigned/' + startDate + '/' + endDate, idIssuer, {headers: this.auth.createAuthorizationTokenHeader()});
  }
  createNonSelfCertificate(idSubject: string, startDate: string, endDate: string, author: string) {
    console.log('creating nonself certificate...');
    return this.http.post('https://localhost:8443/api/certificates/create/' + idSubject + '/' + startDate + '/' + endDate, author, {headers: this.auth.createAuthorizationTokenHeader()});
  }

  showCertificates() : Observable<any>{
    console.log('show certificate');
    return this.http.get('https://localhost:8443/api/certificates/allUsersWithCertificates', {headers: this.auth.createAuthorizationTokenHeader()});
  }

  showCertificatesWithIssuer(id : string) : Observable<any>{
    console.log('show certificate');
    return this.http.get('https://localhost:8443/api/certificates/allCertificatesIssuer/' + id, {headers: this.auth.createAuthorizationTokenHeader()});
  }

  validateCertificate(id: string) :  Observable<any> {
    console.log('validate certificate');
    return this.http.get('https://localhost:8443/api/certificates/validate/' + id, {headers: this.auth.createAuthorizationTokenHeader()});
  }
  revokeCertificate(id_subject:number, reasonText : string)
  {
    return this.http.post("https://localhost:8443/api/certificates/revoke/" + id_subject + "/" + reasonText, reasonText, {headers: this.auth.createAuthorizationTokenHeader()});
  }
}