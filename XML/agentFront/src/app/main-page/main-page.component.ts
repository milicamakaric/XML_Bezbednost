import { Component, OnInit } from '@angular/core';
import { AuthServiceService } from 'app/services/auth-service/auth-service.service';
import { UserServiceService } from 'app/services/user-service/user-service.service';

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})
export class MainPageComponent implements OnInit {

  logged: boolean;
  notLogged: boolean;
  token: string;
  podatak: object;
  id_logged : number;
  constructor(private auth: AuthServiceService, private userService : UserServiceService) { }

  ngOnInit() {
    
    this.token = this.auth.getJwtToken();
    console.log('Token je ');
    console.log(this.token);
    if (!this.token) {
      this.notLogged = true;
      this.logged = false;
      console.log('Niko nije ulogovan');
    } else {
      console.log('Neko je ulogovan');
      this.logged = true;
      this.notLogged = false;
     
     }
  }

  
  logOutUser() {
    
    this.userService.logOut().subscribe(podaci => window.location.href='http://localhost:4202/main-page');
    this.auth.removeJwtToken();
    this.notLogged = true;
    this.logged = false;
  }

}