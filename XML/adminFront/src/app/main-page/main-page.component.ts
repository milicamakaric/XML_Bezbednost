import { Component, OnInit } from '@angular/core';
import { AccommodationType } from 'app/models/AccommodationType';
import { AdditionalService } from '../models/AdditionalService';
import { AccommodationServiceService} from '../services/accommodationService/accommodation-service.service';
import {AdditionalServiceServiceService} from '../services/additionalServiceService/additional-service-service.service';
import { ActivatedRoute } from '@angular/router';
import { AuthServiceService } from 'app/services/authService/auth-service.service';
import { UserServiceService } from 'app/services/userService/user-service.service';


@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})
export class MainPageComponent implements OnInit {
    show: boolean;
    showService:boolean;
    type: AccommodationType;
    service : AdditionalService;
	logged: boolean;
  notLogged: boolean;
  token: string;
    constructor(private accommodationService: AccommodationServiceService,private route: ActivatedRoute,private additinalSer:AdditionalServiceServiceService, private auth : AuthServiceService, private userService: UserServiceService) { 
      this.show = false;
      this.showService = false;
    }

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
  newAccommodationType(){
       this.show = false;
      this.accommodationService.addAccommodationType(this.type);
  }

  addType(){
    this.show = true;
  }

  addAdditionalService(){
    this.showService = true;
    
  }
  newAdditionalService(){
    this.showService = false;
    this.additinalSer.addAdditionalService(this.service);
  }
  
  logOutUser() {
    
    this.userService.logOut().subscribe(podaci => window.location.href='http://localhost:4200/main-page');
    this.auth.removeJwtToken();
    this.notLogged = true;
    this.logged = false;
  }
}
