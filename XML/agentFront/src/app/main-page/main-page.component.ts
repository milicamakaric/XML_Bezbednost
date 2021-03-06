import { Component, OnInit } from '@angular/core';
import { AuthServiceService } from 'app/services/auth-service/auth-service.service';
import { UserServiceService } from 'app/services/user-service/user-service.service';
import {AccommodationServiceService} from 'app/services/accommodation-service/accommodation-service.service';
import { NgForm, FormGroup, Validators, FormControl } from '@angular/forms';
import {Agent} from '../model/Agent';
import {Accommodation} from '../model/Accommodation';
import { Room } from 'app/model/Room';

import {UserTokenState} from '../model/UserTokenState';
import { User } from 'app/model/User';
import { LoginComponent } from 'app/login/login.component';
import { AccommodationDTO } from 'app/model/AccommodationDTO';
@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})
export class MainPageComponent implements OnInit {
  show:number;
  accommodations: Array<AccommodationDTO> = [];
  logged: boolean;
  notLogged: boolean;
  token: string;
  podatak: object;
  id_logged : number;
  roomForm: FormGroup;
  person: FormControl;
  price: FormControl;
  accomodationId:number;
  ulogovan: User = new User();
  comments: Array<Comment> =[];

  
 
  constructor(private auth: AuthServiceService, private userService : UserServiceService,private accService: AccommodationServiceService) { }

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
      this.logged = true;
      this.notLogged = false;
     
     }

     this.userService.getLogged(this.auth.getJwtToken()).subscribe(podaci => {
      //this.ssCertificate(podaci)
      console.log('return: ' + podaci);
      this.ulogovan = podaci as User;
     });
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
    
    this.userService.logOut().subscribe(podaci => window.location.href='');
    this.auth.removeJwtToken();
    this.notLogged = true;
    this.logged = false;
  }

  getAccommodatoins(data){
    
   var ulogovan_korisnik = data as User;
   console.log("Ulogovan " + ulogovan_korisnik);
    this.accService.getAccommodations(ulogovan_korisnik.id).subscribe(data =>{
      this.accommodations = data as AccommodationDTO[];
      this.show = 1;
    });
  }
  addRoom(id: number){
    this.accomodationId = id;
    this.show = 2;
  }
  onSubmitRoomForm(form: NgForm){
    var agent: Agent = new Agent();
    
    var accommodation: Accommodation = new Accommodation();
    accommodation.id = this.accomodationId;
    console.log(this.accomodationId);
    var accommodationUnit: Room  = new Room();
    accommodationUnit.defaultPrice = this.roomForm.value.price;
    accommodationUnit.capacity = this.roomForm.value.person;
    accommodationUnit.accomodation = accommodation;
    accommodationUnit.agent.id = this.ulogovan.id;

    this.accService.addAccommodationUnit(accommodationUnit,this.accomodationId).subscribe(data =>{
      this.show = 0;
    });
  }

 showAccommodations()
 {
 
    this.getAccommodatoins(this.ulogovan);
   // window.location.href = 'http://localhost:4202/main-page';
 
 }

 goToMessages()
 {
   window.location.href='messages/' + this.ulogovan.id;
 }

 goToReservations()
 {
   window.location.href='reservations/' + this.ulogovan.id;
 }

 showComments(acc_id:number)
 {
   console.log("Show comments for " + acc_id);
   this.show=3;
 }
}
