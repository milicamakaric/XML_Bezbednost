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
import { HttpErrorResponse } from '@angular/common/http';
import { Accommodation } from 'app/models/Accommodation';
import { CommentServiceService } from 'app/services/CommentService/comment-service.service';
import {Comment} from 'app/models/Comment';
import { ImageAcc } from 'app/models/ImageAcc';

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
  distance: FormControl;
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
  nameACC: FormControl;
  distanceACC: FormControl;
  stateACC: FormControl;
  cityACC: FormControl;
  streetACC: FormControl;
  numberACC: FormControl;
  pttACC: FormControl;
  starsACC: FormControl;
  typeACC: FormControl;
  description: FormControl;
  serviceACC: FormControl;
  freeCancelation: FormControl;
  freeCancelationDays: FormControl;
  file: FormControl;

  ACCAgentsForm: FormGroup;
  agentsACC: FormControl;

  users: any;
  types: any;
  services: any;
  accommodations: any;
  agents: any;
  comments: any;
  choosenAgents: Array<string>;
  choosenAccommodation: number;
  showFreeCancelation: boolean;
  logged: boolean;
  notLogged: boolean;
  token: string;
  imgSource: string = '';
  choosenImages: Array<ImageAcc> = [];
  htmlStr: string = '';
  htmlStr1: string = '';
  addServices: number[]=[];
  imgCounter = 1;
  imgUploaded: boolean = true;

    constructor(private auth : AuthServiceService, private accommodationService: AccommodationServiceService, 
      private route: ActivatedRoute, 
      private additionalService:AdditionalServiceServiceService,
      private userService: UserServiceService,
      private commService: CommentServiceService) { 
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


  createFormControls() {
    this.firstName = new FormControl('', Validators.required);
    this.lastName = new FormControl('', Validators.required)
    this.pib = new FormControl('', [Validators.pattern(/^-?[0-9]{9}$/), Validators.required]);
    this.distance = new FormControl('', [Validators.pattern(/^-?[0-9]+(\.[0-9][0-9]?)?$/), Validators.required]);
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

    this.distanceACC = new FormControl('', [Validators.pattern(/^-?[0-9]+(\.[0-9][0-9]?)?$/), Validators.required]);
    this.stateACC = new FormControl('', Validators.required);
    this.nameACC = new FormControl('', Validators.required);
    this.cityACC = new FormControl('', Validators.required);
    this.starsACC = new FormControl('', Validators.required);
    this.streetACC = new FormControl('', Validators.required);
    this.numberACC = new FormControl('', Validators.required);
    this.pttACC = new FormControl('', [Validators.pattern(/^-?[0-9]{5}$/), Validators.required]);
    this.typeACC = new FormControl('', Validators.required);
    this.description = new FormControl('', Validators.required);
    //this.serviceACC = new FormControl('', Validators.required);
    this.serviceACC = new FormControl('');
    this.freeCancelation = new FormControl('', Validators.required);
    this.freeCancelationDays = new FormControl('');
    this.file = new FormControl('', Validators.required);

    this.agentsACC=  new FormControl('', Validators.required);
  }

  createForm(){
    this.agentForm = new FormGroup({
      firstName: this.firstName,
      lastName: this.lastName,
      email: this.email,
      password : this.password,
      pib: this.pib,
      distance: this.distance,
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
      distanceACC: this.distanceACC,
      nameACC: this.nameACC,
      stateACC: this.stateACC,
      cityACC: this.cityACC,
      streetACC: this.streetACC,
      numberACC: this.numberACC,
      starsACC: this.starsACC,
      pttACC: this.pttACC,
      typeACC: this.typeACC,
      description: this.description,
      serviceACC: this.serviceACC,
      freeCancelation: this.freeCancelation,
      freeCancelationDays: this.freeCancelationDays,
      file: this.file
    });

    this.ACCAgentsForm = new FormGroup({
      agentsACC: this.agentsACC
    });

  }

  onSubmitAccommodationTypeForm(){
    

    var accommodationType: AccommodationType = new AccommodationType();
    accommodationType.name = this.accommodationTypeForm.value.type;

    this.accommodationService.addAccommodationType(accommodationType).subscribe(date => {
      console.log('accommodation type added'); this.show = 0;
    }, err => {this.handle404ErrorType(err);});
  }

  addType(){
    this.show = 1;
    this.type.reset();
    this.htmlStr='';

  }

  addAdditionalService(){
    this.show = 2;
    this.service.reset();
    this.htmlStr1='';
    
  }

  onSubmitAdditionalServiceForm(form: NgForm){
    

    var additionalService: AdditionalService = new AdditionalService();
    additionalService.name = this.additionalServiceForm.value.service;

    this.additionalService.addAdditionalService(additionalService).subscribe(data => {
      console.log('additional service added');this.show = 0;} ,  err => {this.handle404ErrorService(err);});
  }

  addAgent(){
    this.show = 3;
    this.agentForm.reset();

  }

  onSubmitAgentForm(form: NgForm){
    console.log('submit agent form');

    var address: Address = new Address();
    address.state = this.agentForm.value.state;
    address.city = this.agentForm.value.city;
    address.street = this.agentForm.value.street;
    address.number = this.agentForm.value.number;
    address.ptt = this.agentForm.value.ptt;
    address.distance = this.agentForm.value.distance;

 
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
      for(let user of this.users)
      {
        if(user.id == id)
        {
          user.enabled=true;
        }
      }
    });
  }

  deleteUser(id: number){
    console.log('delete user; id: ' + id);
    this.userService.deleteUser(id).subscribe(data =>{
      console.log('user is deleted');
      for(let user of this.users)
      {
        if(user.id == id)
        {
          user.deleted=true;
        }
      }
    });
  }

  blockUser(id: number){
    console.log('block user; id: ' + id);
    this.userService.blockUser(id).subscribe(data =>{
      console.log('user is bloked');
      for(let user of this.users)
      {
        if(user.id == id)
        {
          user.blocked=true;
        }
      }
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
        this.accommodationForm.reset();
      });
      
    });
  }

  onSubmitAccommodationForm(form: NgForm){
    this.addServices=[];
    console.log('submit accommodation form');
    var address: Address = new Address();
    address.state = this.accommodationForm.value.stateACC;
    address.city = this.accommodationForm.value.cityACC;
    address.street = this.accommodationForm.value.streetACC;
    address.number = this.accommodationForm.value.numberACC;
    address.ptt = this.accommodationForm.value.pttACC;
    address.distance = this.accommodationForm.value.distanceACC;
    
    var accommodation: Accommodation = new Accommodation();
    accommodation.address = address;
    accommodation.name=this.accommodationForm.value.nameACC;
    accommodation.description = this.accommodationForm.value.description;

    accommodation.type.name = this.accommodationForm.value.typeACC;
    accommodation.stars = this.accommodationForm.value.starsACC;

    
    if(this.showFreeCancelation){
      accommodation.cancelation.allowed = true;
      accommodation.cancelation.numberOfDays =this.accommodationForm.value.freeCancelationDays;
    }else{
      accommodation.cancelation.allowed = false;
      accommodation.cancelation.numberOfDays =-1;
    }
    accommodation.image = this.accommodationForm.value.file;
    this.addServices = this.accommodationForm.value.serviceACC;
    if(this.addServices != null){
      for(let ser of this.addServices)
      {
        var as : AdditionalService = new AdditionalService();
        as.id = ser as number;
        accommodation.additionalServices.push(as);
      }
    }
    
    console.log(this.accommodationForm.value.file);
    console.log(accommodation.type);

    this.accommodationService.addAccommodation(accommodation).subscribe(resp => {
      console.log('accommodation  added');
      //this.show = 0;
      let accom= resp as Accommodation;
      this.accommodationService.addImages(accom.id, this.choosenImages).subscribe(pom =>
          {this.imgCounter = 1;}
          )
      }, err => {this.handle404ErrorType(err);} );
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
  selectionCh(form:NgForm){
    console.log('dosao ovdje');
    
  

  }

  handle404ErrorType(err: HttpErrorResponse)
  {
    if(err.status == 404)
    {
      console.log('This type of accommodation already exists.');
      this.htmlStr='This type of accommodation already exists.';
    }
  }

  handle404ErrorService(err: HttpErrorResponse)
  {
    if(err.status == 404)
    {
      console.log('This additional service already exists.');
      this.htmlStr1='This additional service already exists.';
    }
  }

  getAccommodations(){
    console.log('getting accommodations...');
    this.accommodationService.getAccommodations().subscribe(data =>{
      this.accommodations = data;
      this.show = 7;
    });
  }

  showAgents(id: number)
  {
    this.choosenAccommodation = id;
    console.log("Show form add agents to acc: " + id);
    document.getElementById("button"+id).setAttribute("hidden", "true");
    this.userService.getAgents(id).subscribe(data =>{
      document.getElementById("agentsForm").removeAttribute("hidden");
      this.agents = data;
    });
   
  }

  onSubmitACCAgentsForm(){
    console.log("In onSubmitACCAgentsForm");
    var agents = this.ACCAgentsForm.value.agentsACC;
    console.log(agents);
    this.accommodationService.addAgentToAccommodation(this.choosenAccommodation, agents).subscribe(data =>{
      console.log('Added agents to accommodation');
      document.getElementById("agentsForm").hidden = true;
      this.show = 7;

    });
  }

  getComments(){
    console.log('getting accommodations...');
    this.accommodationService.getAccommodations().subscribe(data =>{
      this.accommodations = data;
      this.show = 8;
    });

  }
  getNotAllowedComments(id:number){
    console.log('getting commnets...');
    this.accommodationService.getAccommodationComments(id).subscribe(data =>{
      this.comments = data;
      this.show = 9;
    });

  }
  selectedImage($event: Event): void {
    let reader = new FileReader();
    var counter = this.imgCounter;
    if(counter == 1){
      this.choosenImages = new Array<ImageAcc>();
    }
    // tslint:disable-next-line:no-string-literal
    var file = $event.target['files'][0];
    console.log('Image is choosen');
    console.log(file);
    reader.onload = (e: any) => {
        this.imgSource = e.target.result;
        var newPicture = new ImageAcc();
        newPicture.id = counter;
        newPicture.data = file;
        this.choosenImages.push(newPicture);
        this.imgCounter = counter + 1;
    };
    reader.readAsDataURL(file);
  }

  aproveComment(id:number){
    var comm  : Comment = new Comment();
    comm.id = id;
    this.commService.aproveComment(comm).subscribe(data => {
      this.show = 0;
       
    });

  }
}
