import { Component, OnInit } from '@angular/core';
import { SoftwareServiceService } from '../services/softwareService/software-service.service';
import { CertificateServiceService } from '../services/certificateService/certificate-service.service';
import { ActivatedRoute } from '@angular/router';
import { UserServiceService } from '../services/userService/user-service.service';
import { AuthServiceService } from '../services/authService/auth-service.service';
import { User } from 'app/models/User';

@Component({
  selector: 'app-softwares',
  templateUrl: './softwares.component.html',
  styleUrls: ['./softwares.component.css']
})
export class SoftwaresComponent implements OnInit {

  id: string;
  certificates: Object;
  logged: boolean;
  notLogged: boolean;
  token: string;
  podatak: object;
  user: User = new User();
  constructor(private softwareService: SoftwareServiceService, private certificateService: CertificateServiceService,
     private route: ActivatedRoute, private userService: UserServiceService, private auth: AuthServiceService) {
    //this.a.getSoftwares().subscribe(podaci => {});
  }

  ngOnInit() {
    //tslint:disable:no-string-literal
    //this.id = this.route.snapshot.params['id'];
    //tslint:enable:no-string-literal
    //console.log('Pronadjen id je ' + this.id);
    this.certificateService.showCertificates().subscribe(data => this.certificates = data);
    this.token = this.auth.getJwtToken();
      console.log('Token je ');
      console.log(this.token);
      if (!this.token) {
        this.notLogged = true;
        console.log('Niko nije ulogovan');
      } else {
        console.log('Neko je ulogovan');
        this.logged = true;
        this.userService.getLogged(this.token).subscribe(podaci => { this.user = podaci as User; });
       }
  }
  
    logOutUser() {
      this.auth.removeJwtToken();
      this.notLogged = true;
      this.logged = false;
    }


}
