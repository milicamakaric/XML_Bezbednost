import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UserServiceService } from '../services/userService/user-service.service';
import {AuthServiceService} from '../services/authService/auth-service.service';


@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {
  logged: boolean;
  notLogged: boolean;
  token: string;
  constructor(private userService: UserServiceService, private route: ActivatedRoute, private auth: AuthServiceService) { }

  ngOnInit() {
    this.token = this.auth.getJwtToken();
    console.log(this.token);
    if (this.token === '') {
      this.notLogged = true;
      console.log('Niko nije ulogovan');
    } else {
      this.logged = true;
      console.log('Neko je ulogovan');
    }
  }

}
