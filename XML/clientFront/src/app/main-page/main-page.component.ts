import { Component, OnInit } from '@angular/core';
import { AuthServiceService } from '../services/auth-service/auth-service.service';
import { UserServiceService } from '../services/user-service/user-service.service';
import  {NgForm} from '@angular/forms';
import { SearchForm } from '../models/SearchForm';
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
  id_logged: number;
  searchForm: SearchForm = new SearchForm();
  allServices = [
    'Parking lot',
    'WiFi',
    'Pet friendly',
    'TV',
    'Kitchen',
    'Private bathroom'
  ];
  constructor(private auth: AuthServiceService, private userService: UserServiceService) { }

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
   }
  }

  logOutUser() {
    this.userService.logOut().subscribe(podaci => window.location.href='http://localhost:4201');
    this.auth.removeJwtToken();
    this.notLogged = true;
    this.logged = false;
  }
  findHotels() {
    console.log('Usao u find');
    console.log(this.searchForm);

  }
}
