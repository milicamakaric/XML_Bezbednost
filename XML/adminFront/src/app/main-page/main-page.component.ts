import { Component, OnInit } from '@angular/core';
import { AccommodationType } from 'app/models/AccommodationType';
import { AdditionalService } from '../models/AdditionalService';
import { AccommodationServiceService} from '../services/accommodationService/accommodation-service.service';
import { AdditionalServiceServiceService } from '../services/additionalServiceService/additional-service-service.service';
import { ActivatedRoute } from '@angular/router';
import { NgForm, FormGroup, Validators, FormControl } from '@angular/forms';
import { Address } from 'app/models/Address';
import { User } from 'app/models/User';
import { Agent } from 'app/models/Agent';
import { UserServiceService } from 'app/services/userService/user-service.service';

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})

export class MainPageComponent implements OnInit {

    show: number; //0-nista se ne prikazuje, 1-type, 2-additional service, 3-agent, 4-users

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

    additionalServiceForm: FormGroup;
    service: FormControl;

    accommodationTypeForm: FormGroup;
    type: FormControl;

    users: any;

    constructor(private accommodationService: AccommodationServiceService, 
                private route: ActivatedRoute, 
                private additinalSer:AdditionalServiceServiceService,
                private userService: UserServiceService) { 
      this.show = 0;
    }

  ngOnInit() {
    this.createFormControls();
    this.createForm();
  }

  createFormControls(){
    this.firstName = new FormControl('', Validators.required);
    this.lastName = new FormControl('', Validators.required)
    this.pib = new FormControl('', Validators.required);
    this.longitude = new FormControl('', Validators.required);
    this.latitude = new FormControl('', Validators.required);
    this.state = new FormControl('', Validators.required);
    this.city = new FormControl('', Validators.required);
    this.street = new FormControl('', Validators.required);
    this.number = new FormControl('', Validators.required);
    this.ptt = new FormControl('', Validators.required);

    this.service = new FormControl('', Validators.required);

    this.type = new FormControl('', Validators.required);
  }

  createForm(){
    this.agentForm = new FormGroup({
      firstName: this.firstName,
      lastName: this.lastName,
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

    this.additinalSer.addAdditionalService(additionalService).subscribe(data => {
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

    agent.address = address;
    agent.pib = this.agentForm.value.pib;

    this.userService.addAgent(agent).subscribe(data => {
      this.show = 0;
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
}
