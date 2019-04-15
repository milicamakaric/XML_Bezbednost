import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CertificateServiceService } from '../services/certificateService/certificate-service.service';
import { User } from '../models/User';
import { StringDTO } from '../models/StringDTO';
import {AuthServiceService} from '../services/authService/auth-service.service';
import {UserServiceService} from '../services/userService/user-service.service';
import { HttpErrorResponse } from '@angular/common/http';
import { element } from '@angular/core/src/render3';


@Component({
  selector: 'app-list-of-certificates',
  templateUrl: './list-of-certificates.component.html',
  styleUrls: ['./list-of-certificates.component.css']
})
export class ListOfCertificatesComponent implements OnInit {
  id: Object;
  users: Array<any>;
  user: User;
  message: StringDTO;
  reasonText: string;
  id_subject : number;

  constructor(private route: ActivatedRoute, private certificateService: CertificateServiceService, private userService : UserServiceService, private auth : AuthServiceService) {
    this.route.params.subscribe( params => {this.id = params.id; });
    console.log("ID ulogovanog je: " + this.id);
    this.id_subject=0;

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
    this.certificateService.validateCertificate(id as string).subscribe(data =>{
       this.message = data as StringDTO; 
       console.log('message: ' + this.message.message);
       document.getElementById('validateDiv').removeAttribute('hidden');
       document.getElementById("revokeDiv").setAttribute("hidden", "true");
       document.getElementById("connectDiv").setAttribute("hidden", "true");
       document.getElementById("validation").setAttribute("value", this.message.message);
      })
    
  }

  handleAuthError(err: HttpErrorResponse) {
  
    if(err.status === 200) { //403 Forbidden
     // alert('Ime aviokompanije je zauzeto!');
      console.log('err statusText: ' + err);
    }
  }

  revokeCertificate(id_subject)
  {
    this.reasonText="";
    console.log("Id subject: " + id_subject);
    this.id_subject = id_subject as number;

    document.getElementById("revokeDiv").removeAttribute("hidden");
    document.getElementById("connectDiv").setAttribute("hidden", "true");
    document.getElementById("validateDiv").setAttribute("hidden", "true");
  }

  revokation()
  {
    console.log("Id subject: " + this.id_subject + " reason: " + this.reasonText);

    this.certificateService.revokeCertificate(this.id_subject, this.reasonText).subscribe(data => {window.location.href="http://localhost:4200/list-of-certificates/"+this.id});
   
  }
  
}
