import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UserServiceService } from '../services/userService/user-service.service';
import {AuthServiceService} from '../services/authService/auth-service.service';
import { User } from 'app/models/User';


@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {
  logged: boolean;
  notLogged: boolean;
  token: string;
  podatak: object;
  user: User = new User();
  id_logged : number;
  constructor(private userService: UserServiceService, private route: ActivatedRoute, private auth: AuthServiceService) { }

  ngOnInit() {
    this.token = this.auth.getJwtToken();
    console.log('Token je ');
    console.log(this.token);
    if (!this.token) {
      this.notLogged = true;
      console.log('Niko nije ulogovan');
    } else {
      console.log('Neko je ulogovan');
      this.logged = true;
      this.userService.getLogged(this.token).subscribe(podaci => { this.pathToList(podaci); });
     }
  }

  pathToList(data)
  {
    this.user = data as User;
    this.id_logged=this.user.id;
    document.getElementById("listCertificates").setAttribute("href", "/list-of-certificates/" + this.id_logged);
  }
  logOutUser() {
    
    this.userService.logOut().subscribe(podaci => window.location.href='http://localhost:4200');
    this.auth.removeJwtToken();
    this.notLogged = true;
    this.logged = false;
  }

}
