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

    show: boolean;
    showService: boolean;
    showAgent: boolean;
    type: AccommodationType;
    service : AdditionalService;

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

    constructor(private accommodationService: AccommodationServiceService, 
                private route: ActivatedRoute, 
                private additinalSer:AdditionalServiceServiceService,
                private userService: UserServiceService) { 
      this.show = false;
      this.showService = false;
      this.showAgent = false;
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

  addAgent(){
    this.showAgent = true;
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

    var user: User = new User();
    user.address = address;
    user.name = this.agentForm.value.firstName;
    user.surname = this.agentForm.value.lastName;

    var agent: Agent = new Agent();
    agent.user = user;
    agent.pib = this.agentForm.value.pib;

    this.userService.addAgent(agent).subscribe(data => {
      this.showAgent = false;
    });



  }
}
