import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CertificateServiceService } from  '../services/certificateService/certificate-service.service';
import { UserServiceService } from '../services/userService/user-service.service';
import { User } from '../models/User';

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

  constructor(private certificateService: CertificateServiceService, private userService: UserServiceService, private route: ActivatedRoute) { 
    this.route.params.subscribe( params => {this.self = params.self, this.id=params.id});
    this.today = new Date();
  }

  ngOnInit() {
    console.log('parametar: ' + this.self);
    
  }

  createCertificate(){
    if(this.self == 'self'){
      console.log('id:' + this.id);
      console.log('start:' + this.startDate);
      console.log('end:' + this.endDate);  
      this.certificateService.createSelfCertificate(this.id as string, this.startDate, this.endDate).subscribe(
        data => window.location.href = 'http://localhost:4200/softwares'
        );
    }else{
      console.log('id:' + this.id);
      console.log('start:' + this.startDate);
      console.log('end:' + this.endDate);  
      //ovde pozvati funkciju za pravljenje obicnog sertifikata
    }
  }

  startDateChanged(){
    
  }

}
