import { Component, OnInit } from '@angular/core';
import { AuthServiceService } from 'app/services/auth-service/auth-service.service';
import { UserServiceService } from 'app/services/user-service/user-service.service';
import {AccommodationServiceService} from 'app/services/accommodation-service/accommodation-service.service';
import { NgForm, FormGroup, Validators, FormControl } from '@angular/forms';
import {Agent} from '../model/Agent';
import {Accommodation} from '../model/Accommodation';
import { Room } from 'app/model/Room';

import {UserTokenState} from '../model/UserTokenState';
@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})
export class MainPageComponent implements OnInit {
  show:number;
  accommodations: any;
  logged: boolean;
  notLogged: boolean;
  token: string;
  podatak: object;
  id_logged : number;
  roomForm: FormGroup;
  person: FormControl;
  price: FormControl;
  accomodationId:number;

 
  constructor(private auth: AuthServiceService, private userService : UserServiceService,private accService: AccommodationServiceService ) { }

  ngOnInit() {    
    this.createFormControls();
    this.createForm();

    this.show = 0;
    this.token = this.auth.getJwtToken();
    console.log('Token je ');
    console.log(this.token);
    if (!this.token) {
      this.notLogged = true;
      this.logged = false;
      console.log('Niko nije ulogovan');
    } else {
      console.log('Neko je ulogovan');
      this.getAccommodatoins();
      this.logged = true;
      this.notLogged = false;
     
     }
  }
  createFormControls(){
    this.person = new FormControl('', Validators.required);
    this.price = new FormControl('', Validators.required)
    
  }
  createForm(){
    this.roomForm = new FormGroup({
      person:this.person,
      price:this.price
    });
    
  }
  
  logOutUser() {
    
    this.userService.logOut().subscribe(podaci => window.location.href='http://localhost:4202/main-page');
    this.auth.removeJwtToken();
    this.notLogged = true;
    this.logged = false;
  }

  getAccommodatoins(){
    
    let user_token = this.auth.getJwtToken;
 /*   this.userService.getLogged(user_token).subscribe(podaci => {

    });
    */    
    var id :number;
    this.accService.getAccommodations(id).subscribe(data =>{
      this.accommodations = data;
      this.show = 1;
    });
  }
  addRoom(id: number){
    this.accomodationId = id;
    this.show = 2;
  }
  onSubmitRoomForm(){
    var agent: Agent = new Agent();
    var accommodation: Accommodation = new Accommodation();
    accommodation.id = this.accomodationId;

    var accommodationUnit: Room  = new Room();
    accommodationUnit.price = this.roomForm.value.price;
    accommodationUnit.persons = this.roomForm.value.persons;
    accommodationUnit.accomodation = accommodation;

    this.accService.addAccommodationUnit(accommodationUnit);
  }


}
