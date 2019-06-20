import { Component, OnInit } from '@angular/core';
import { AccommodationType } from 'app/models/AccommodationType';
import { AdditionalService } from '../models/AdditionalService';
import { AccommodationServiceService} from '../services/accommodationService/accommodation-service.service';
import { AdditionalServiceServiceService } from '../services/additionalServiceService/additional-service-service.service';
import { ActivatedRoute } from '@angular/router';
import { AuthServiceService } from 'app/services/authService/auth-service.service';
import { UserServiceService } from 'app/services/userService/user-service.service';


import { NgForm, FormGroup, Validators, FormControl } from '@angular/forms';
import { Address } from 'app/models/Address';
import { User } from 'app/models/User';
import { Agent } from 'app/models/Agent';


@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})

export class MainPageComponent implements OnInit {

  show: number; //0-nista se ne prikazuje, 1-type, 2-additional service, 3-agent, 4-users, 5-accommodation

  agentForm: FormGroup;
  firstName: FormControl;
  lastName: FormControl;
  pib: FormControl;
  longitude: FormControl;
  latitude: FormControl;
  state: FormControl;
  city: FormControl;
  street: FormControl;
  number: FormControl;
  ptt: FormControl;
  email: FormControl;
  password: FormControl;

  additionalServiceForm: FormGroup;
  service: FormControl;

  accommodationTypeForm: FormGroup;
  type: FormControl;

  accommodationForm: FormGroup;
  longitudeACC: FormControl;
  latitudeACC: FormControl;
  stateACC: FormControl;
  cityACC: FormControl;
  streetACC: FormControl;
  numberACC: FormControl;
  pttACC: FormControl;
  typeACC: FormControl;
  description: FormControl;
  serviceACC: FormControl;
  freeCancelation: FormControl;
  freeCancelationDays: FormControl;
  file: FormControl;

  users: any;
  types: any;
  services: any;

  showFreeCancelation: boolean;
	logged: boolean;
  notLogged: boolean;
  token: string;

    constructor(private auth : AuthServiceService, private accommodationService: AccommodationServiceService, 
      private route: ActivatedRoute, 
      private additionalService:AdditionalServiceServiceService,
      private userService: UserServiceService) { 
      this.show = 0;
      this.showFreeCancelation = false;
    }

  ngOnInit() {
    this.createFormControls();
    this.createForm();

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


  createFormControls(){
    this.firstName = new FormControl('', Validators.required);
    this.lastName = new FormControl('', Validators.required)
    this.pib = new FormControl('', [Validators.pattern(/^-?[0-9]{9}$/), Validators.required]);
    this.longitude = new FormControl('', [Validators.pattern(/^-?[0-9]+(\.[0-9][0-9]?)?$/), Validators.required]);
    this.latitude = new FormControl('', [Validators.pattern(/^-?[0-9]+(\.[0-9][0-9]?)?$/), Validators.required]);
    this.state = new FormControl('', Validators.required);
    this.city = new FormControl('', Validators.required);
    this.street = new FormControl('', Validators.required);
    this.number = new FormControl('', Validators.required);

    this.ptt = new FormControl('', Validators.required);
    this.password = new FormControl('',Validators.required);
    this.email = new FormControl('',Validators.required);

    this.ptt = new FormControl('', [Validators.pattern(/^-?[0-9]{5}$/), Validators.required]);


    this.service = new FormControl('', Validators.required);

    this.type = new FormControl('', Validators.required);

    this.longitudeACC = new FormControl('', [Validators.pattern(/^-?[0-9]+(\.[0-9][0-9]?)?$/), Validators.required]);
    this.latitudeACC = new FormControl('', [Validators.pattern(/^-?[0-9]+(\.[0-9][0-9]?)?$/), Validators.required]);
    this.stateACC = new FormControl('', Validators.required);
    this.cityACC = new FormControl('', Validators.required);
    this.streetACC = new FormControl('', Validators.required);
    this.numberACC = new FormControl('', Validators.required);
    this.pttACC = new FormControl('', [Validators.pattern(/^-?[0-9]{5}$/), Validators.required]);
    this.typeACC = new FormControl('', Validators.required);
    this.description = new FormControl('', Validators.required);
    this.serviceACC = new FormControl('', Validators.required);
    this.freeCancelation = new FormControl('', Validators.required);
    this.freeCancelationDays = new FormControl('');
    this.file = new FormControl('', Validators.required);
  }

  createForm(){
    this.agentForm = new FormGroup({
      firstName: this.firstName,
      lastName: this.lastName,
      email: this.email,
      password : this.password,
      pib: this.pib,
      longitude: this.longitude,
      latitude: this.latitude,
      state: this.state,
      city: this.city,
      street: this.street,
      number: this.number,
      ptt: this.ptt
    });

    this.additionalServiceForm = new FormGroup({
      service: this.service
    });

    this.accommodationTypeForm = new FormGroup({
      type: this.type
    });

    this.accommodationForm = new FormGroup({
      longitudeACC: this.longitudeACC,
      latitudeACC: this.latitudeACC,
      stateACC: this.stateACC,
      cityACC: this.cityACC,
      streetACC: this.streetACC,
      numberACC: this.numberACC,
      pttACC: this.pttACC,
      typeACC: this.typeACC,
      description: this.description,
      serviceACC: this.serviceACC,
      freeCancelation: this.freeCancelation,
      freeCancelationDays: this.freeCancelationDays,
      file: this.file
    });

  }

  onSubmitAccommodationTypeForm(){
    this.show = 0;

    var accommodationType: AccommodationType = new AccommodationType();
    accommodationType.name = this.accommodationTypeForm.value.type;

    this.accommodationService.addAccommodationType(accommodationType).subscribe(date => {
      console.log('accommodation type added');
    });
  }

  addType(){
    this.show = 1;
  }

  addAdditionalService(){
    this.show = 2;
    
  }

  onSubmitAdditionalServiceForm(form: NgForm){
    this.show = 0;

    var additionalService: AdditionalService = new AdditionalService();
    additionalService.name = this.additionalServiceForm.value.service;

    this.additionalService.addAdditionalService(additionalService).subscribe(data => {
      console.log('additional service added');
    });
  }

  addAgent(){
    this.show = 3;
  }

  onSubmitAgentForm(form: NgForm){
    console.log('submit agent form');

    var address: Address = new Address();
    address.state = this.agentForm.value.state;
    address.city = this.agentForm.value.city;
    address.street = this.agentForm.value.street;
    address.number = this.agentForm.value.number;
    address.ptt = this.agentForm.value.ptt;
    address.longitude = this.agentForm.value.longitude;
    address.latitude = this.agentForm.value.latitude;

 
    var agent: Agent = new Agent();
    agent.name = this.agentForm.value.firstName;
    agent.surname = this.agentForm.value.lastName;
    agent.email = this.agentForm.value.email;
    agent.password  = this.agentForm.value.password;
    agent.address = address;
    agent.pib = this.agentForm.value.pib;

    this.userService.addAgent(agent).subscribe(data => {
      this.show = 0;
        agent = data as Agent;
      if(agent.email === "error"){
          this.show=6;
      }
    });

  }

  getUsers(){
    console.log('getting users...');
    this.userService.getUsers().subscribe(data =>{
      this.users = data;
      this.show = 4;
    });
  }

  activateUser(id: number){
    console.log('activate user; id: ' + id);
    this.userService.activateUser(id).subscribe(data =>{
      console.log('user is activated');
    });
  }

  deleteUser(id: number){
    console.log('delete user; id: ' + id);
    this.userService.deleteUser(id).subscribe(data =>{
      console.log('user is deleted');
    });
  }

  blockUser(id: number){
    console.log('block user; id: ' + id);
    this.userService.blockUser(id).subscribe(data =>{
      console.log('user is bloked');
    });
  }
  
  logOutUser() {
    
    this.userService.logOut().subscribe(podaci => window.location.href='http://localhost:4200/main-page');
    this.auth.removeJwtToken();
    this.notLogged = true;
    this.logged = false;
  }

  addAccommodation(){
    this.accommodationService.getTypes().subscribe(data => {
      this.types = data;
      this.additionalService.getServices().subscribe(data2 => {
        this.services = data2;
        this.show=5;
      });
      
    });
  }

  onSubmitAccommodationForm(form: NgForm){
    console.log('submit accommodation form');


  }

  freeCancelationChanged(form: NgForm){
    console.log('selected value: ' + form.value.freeCancelation);
    if(form.value.freeCancelation == 'Yes'){
      this.showFreeCancelation = true;
      this.freeCancelationDays = new FormControl('', [Validators.pattern(/^-?[0-9]{1,3}$/), Validators.required]);
      this.createForm();
    }
    else{
      this.showFreeCancelation = false;
      this.freeCancelationDays = new FormControl('');
      this.createForm();
    }
  }
}
