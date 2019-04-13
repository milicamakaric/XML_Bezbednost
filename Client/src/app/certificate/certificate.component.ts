import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CertificateServiceService } from  '../services/certificateService/certificate-service.service';
import { UserServiceService } from '../services/userService/user-service.service';
import { User } from '../models/User';
import { Observable } from 'rxjs';
import { InitialStylingValuesIndex } from '@angular/core/src/render3/interfaces/styling';

@Component({
  selector: 'app-certificate',
  templateUrl: './certificate.component.html',
  styleUrls: ['./certificate.component.css']
})
export class CertificateComponent implements OnInit {

  id: Object;
  self: Object;
  startDate: string;
  endDate: string;
  user: User;
  object: Object;
  today: Date;
  certificatedUsers: Array<any>;
  author: string;

  constructor(private certificateService: CertificateServiceService, private userService: UserServiceService, private route: ActivatedRoute) { 
    this.route.params.subscribe( params => {this.self = params.self, this.id = params.id; });
    this.today = new Date();
    //this.certificatedUsers = null;
  }
  ngOnInit() {
    if (this.self === 'nonself') {
      this.userService.getCertificatedUsers().subscribe(data => {
        this.certificatedUsers = data;
        console.log(data);
        console.log(data.length);
        this.showField();
      });
    }
  }
  showField() {
    console.log('parametar: ' + this.self);
    if (this.self === 'nonself') {
       console.log('usao u if');
       // this.choose = false;
       // this.userService.getCertificatedUsers().subscribe(data => this.certificated_users = data);
       if (!this.certificatedUsers) {
            console.log('Nema sertifikovanih');
            window.alert('There are not certificated issuers!');
            window.location.href = 'http://localhost:4200/softwares';
        } else {
            document.getElementById('skriveno').removeAttribute('hidden');
            console.log('Ima sertifikovanih');
            console.log(this.certificatedUsers);
           /* for ( const u of this.certificatedUsers){
              let korisnik = u as User;
              let opt =  document.createElement('option');
              opt.innerHTML = korisnik.name + ' ' + korisnik.surname;
              document.getElementById('lista_issuer').appendChild(opt);
            }*/
        }
    }
  }
  createCertificate() {
    console.log('create ' + this.author);
    if (this.self == 'self') {
      console.log('id:' + this.id);
      console.log('start:' + this.startDate);
      console.log('end:' + this.endDate);
      this.certificateService.createSelfCertificate(this.id as string, this.startDate, this.endDate).subscribe(
        data => window.location.href = 'http://localhost:4200/softwares'
        );
    } else {
      console.log('id:' + this.id);
      console.log('start:' + this.startDate);
      console.log('end:' + this.endDate);
      //ovde pozvati funkciju za pravljenje obicnog sertifikata
    }
  }

  startDateChanged(){
  }

}
