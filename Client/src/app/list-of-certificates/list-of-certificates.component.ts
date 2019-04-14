import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CertificateServiceService } from '../services/certificateService/certificate-service.service';
import { User } from '../models/User';
import {AuthServiceService} from '../services/authService/auth-service.service';
import {UserServiceService} from '../services/userService/user-service.service';


@Component({
  selector: 'app-list-of-certificates',
  templateUrl: './list-of-certificates.component.html',
  styleUrls: ['./list-of-certificates.component.css']
})
export class ListOfCertificatesComponent implements OnInit {
  id: Object;
  users: Array<any>;
  user: User;
  message: string;
  constructor(private route: ActivatedRoute, private certificateService: CertificateServiceService, private userService : UserServiceService, private auth : AuthServiceService) {
    this.route.params.subscribe( params => {this.id = params.id; });
    console.log("ID ulogovanog je: " + this.id);

   }

  ngOnInit() {
    var token_user = this.auth.getJwtToken() as string;
    this.userService.getLogged(token_user).subscribe(podaci => {this.showCert(podaci)});
  }

  showCert(data)
  {
    this.user = data as User;

    for(let role of this.user.authorities)
    {
      if(role.authority == "ROLE_ADMIN")
      this.certificateService.showCertificates().subscribe(data =>this.areThereCerts(data));
      if(role.authority == "ROLE_USER")
      this.certificateService.showCertificatesWithIssuer(this.id as string).subscribe(data => this.areThereCerts(data));
    }

    

  }

  areThereCerts(data)
  {
    this.users=data;
    if(data==null)
      alert("There are not certificates that you can see in this moment!");
    
  }

  validate(id){
    console.log('validate id: ' + id);
    this.certificateService.validateCertificate(id as string).subscribe(data => this.message = data as string);
    console.log('message: ' + this.message);
  }
  
}
