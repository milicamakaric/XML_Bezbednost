import { Component, OnInit } from '@angular/core';
import { AuthServiceService } from '../services/auth-service/auth-service.service';
import { UserServiceService } from '../services/user-service/user-service.service';
import { NgForm, FormGroup, FormControl, Validators } from '@angular/forms';
import { SearchForm } from '../models/SearchForm';
import { AccommodationServiceService } from '../services/accommodationService/accommodation-service.service';
import { AdditionalServiceServiceService } from '../services/additionalServiceService/additional-service-service.service';
import { getLocaleExtraDayPeriods } from '@angular/common';
import { AdditionalService } from '../models/AdditionalService';
import { AccommodationDTO } from '../models/AccommodationDTO';
import { SortForm } from '../models/SortForm';
import { Room } from '../models/Room';
import { THROW_IF_NOT_FOUND } from '@angular/core/src/di/injector';
import { RoomDTO } from '../models/RoomDTO';
import { User } from '../models/User';
import { ReservationServiceService } from '../services/reservation-service/reservation-service.service';
import { Reservation } from '../models/Reservation';
import { SortRoom } from '../models/SortRoom';
import { CommentStmt } from '@angular/compiler';
import { Cancelation } from '../models/Cancelation';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})
export class MainPageComponent implements OnInit {
  ulogovan : User;
  logged: boolean;
  notLogged: boolean;
  hideRes:boolean;
  allowComments: boolean = false;
  token: string;
  podatak: object;
  id_logged: number;
  searchForm: SearchForm = new SearchForm();
  hotels: Array<AccommodationDTO> = [];
  show: number = 0;
  sortForm: SortForm = new SortForm();
  rooms: Array<Room> =[];
  roomsDTO: Array<RoomDTO> =[];
  reservations:Array<Reservation> = [];
  reservation:Reservation;
  showRooms: boolean =false;
  selectedHotel: AccommodationDTO= new AccommodationDTO()
  sortRoom: SortRoom = new SortRoom();
  allowedComments: Array<Comment> = [];
  showSuccess = false;
  showError = false;
  /*
  parkingLot: boolean;
  wifi: boolean;
  pet: boolean;
  tv: boolean;
  kitchen: boolean;
  bathroom: boolean;
  */
  
  types: any;
  services: AdditionalService[] = [];
  idServices: Map<number, boolean> = new Map<number, boolean>();

  commentForm: FormGroup;
  comment: FormControl;
  stars: FormControl;
  
  constructor(private auth: AuthServiceService, private userService: UserServiceService,
              private accommodationService: AccommodationServiceService,private reservationService:ReservationServiceService, private additionalService: AdditionalServiceServiceService) { }

  ngOnInit() {
    this.createFormControls();
    this.createForm();

    this.token = this.auth.getJwtToken();
    console.log('Token je ');
    console.log(this.token);
    this.showSuccess = false;
      
    if (!this.token) {
      this.notLogged = true;
      console.log('Niko nije ulogovan');
    } else {
      console.log('Neko je ulogovan');
      this.logged = true;
   }

   this.hideRes = true;
   this.getTypes();
   this.getServices();
   this.userService.getLogged(this.auth.getJwtToken()).subscribe(podaci => {
    //this.ssCertificate(podaci)
    console.log('return: ' + podaci);
    this.ulogovan = podaci as User;
   });
  }

  createFormControls()
  {
    this.comment = new FormControl('', Validators.required);
    this.stars= new FormControl('', Validators.required);
  }

  createForm(){
    this.commentForm=new FormGroup({
      comment: this.comment,
      stars: this.stars
    });
  }
  logIn(){
    window.location.href="/login"
  }

  logOutUser() {
    this.userService.logOut().subscribe(podaci => window.location.href='http://localhost:4201');
    this.auth.removeJwtToken();
    this.notLogged = true;
    this.logged = false;
  }

  getTypes(){
    this.accommodationService.getTypes().subscribe(data => {
      this.types = data;
    });
  }

  getServices(){
    this.additionalService.getServices().subscribe(data =>{
      this.services = data as AdditionalService[];

      for(var i=0; i<this.services.length; i++){
        this.idServices.set(this.services[i].id, false);
      }
    });
  }

  findHotels() {
    console.log('Usao u find');
    this.searchForm.listOfServices = new Array<string>();
    /*
    if (this.pet) {
      this.searchForm.listOfServices.push('Pet friendly');
    }
    if (this.tv) {
      this.searchForm.listOfServices.push('TV');
    }
    if (this.bathroom) {
      this.searchForm.listOfServices.push('Private bathroom');
    }
    if (this.kitchen) {
      this.searchForm.listOfServices.push('Kitchen');
    }
    if (this.parkingLot) {
      this.searchForm.listOfServices.push('Parking lot');
    }
    if (this.wifi) {
      this.searchForm.listOfServices.push('WiFi');
    }
    */
   
   for(var i=0; i<this.services.length; i++){
     if(this.idServices.get(this.services[i].id))
      this.searchForm.listOfServices.push(this.services[i].name);
    }

    console.log("City " + this.searchForm.city);
    console.log(this.searchForm);
    console.log("Type " + this.searchForm.type);
    console.log("Additional " + this.searchForm.listOfServices);
    console.log("Cancelation " + this.searchForm.cancelation);
    console.log("Distance " + this.searchForm.distance);

    if(this.searchForm.type == undefined)
    {
      this.searchForm.type="undefined";
    }

    if( this.searchForm.cancelation== undefined)
    {
      this.searchForm.cancelation="undefined";
    }

    if(this.searchForm.distance == undefined)
    {
      this.searchForm.distance = -1;
    }

    if(this.searchForm.stars == undefined)
    {
      this.searchForm.stars=0;
    }
    var res : Reservation = new Reservation();
    res.startDate = this.searchForm.startDate;
    res.endDate = this.searchForm.endDate;
   // res.client.id = this.ulogovan.id;
    this.reservation = res;
    this.accommodationService.search(this.searchForm).subscribe(data => {
      console.log("Vraceno " + data);
      this.hotels=data as Array<AccommodationDTO>;
      this.show=1;
    });

  }

  serviceChanged(id: number){
    var value = this.idServices.get(id);

    if(value == true){
      this.idServices.set(id, false);
    }else{
      this.idServices.set(id, true);
    }

     console.log('service changed');
  }

  sortHotels()
  {
    console.log(this.sortForm);
    this.accommodationService.sortingHotels(this.sortForm, this.hotels).subscribe(data => {
        this.hotels = data as Array<AccommodationDTO>;
        console.log(this.hotels);
        console.log('List is sorted.');
    });
  }
  sortRooms() {
    console.log(this.sortRoom);
    this.accommodationService.sortingRooms(this.sortRoom, this.roomsDTO).subscribe(data => {
        this.roomsDTO = data as Array<RoomDTO>;
        console.log(this.roomsDTO);
        console.log('List of rooms is sorted.');
    });
  }
  showDetails(hotel_id: number)
  {
    for(let h of this.hotels)
    {
      if(h.id == hotel_id)
      {
        this.selectedHotel = h;
        //this.rooms = h.rooms;
        this.roomsDTO = h.rooms;
      }
    }
    this.sortRoom = new SortRoom();
    this.showRooms = true;
  }
  showComments(idHotel: number) {
    for (let pom of this.hotels) {
      if (pom.id == idHotel) {
        this.selectedHotel = pom;
      }
    }

    this.allowedComments = new Array<Comment>();
    this.accommodationService.getAllowedComments(idHotel).subscribe(data => {
      this.allowedComments = data as Array<Comment>;
      console.log(this.allowedComments);
      console.log('Found comments.');
  });
    this.allowComments = true;
  }
  sendMessage(room_id: number)
  {
    if(!this.token)
    {
      alert('You must be logged in to be able to send messages!');
    }
    else
    {
      window.location.href="message/"+room_id;
    }
  }

  showReservations(){
      console.log("dosao po rez "+this.ulogovan.id);
      this.show=-1;
      this.reservationService.getUserReservations(this.ulogovan.id).subscribe(data => {

        this.reservations = data as Array<Reservation>;
        this.hideRes = false;
        
    });

  }
  sendAgentMessagge(res : Reservation){
    console.log("dosao u send agent message"+res.id);
    
    window.location.href="message/"+res.room.id;
     

  }

  addComment(res : Reservation){

    this.show=6;
    this.hideRes = true;

  }
  CancelReservation(res : Reservation){
    console.log("dosao u otkazi rez");
    //provjera da li moze da otkaze rezervaciju 
    this.reservationService.checkCancelationUser(res.id).subscribe(data => {

      var cancel = data as Cancelation;
      if(cancel.allowed){
        console.log("dozvoljeno");
        //idi da je otkazes
       this.cancelRes(res);
      }else{
        console.log("nije dozovljeno");
        this.show = 5;
        //prikazi porukicu
     
      }
  });
  }

  cancelRes(res: Reservation)
  {
    this.reservationService.cancelReservation(res).subscribe(data1 =>{
      console.log(" otkazao ");
      res.status="canceled";
    });
  }
 
  MakeRes(roomId : number){
    console.log("u fi sam "+roomId);
    
    this.reservation.client.id = this.ulogovan.id;
    console.log("dosao u component da rez");
    this.reservationService.reserve(this.reservation,roomId,this.ulogovan.id).subscribe(data => {
      console.log("vrati se posle rez");
        this.showSuccess = true;
      
        setTimeout(() => {
          window.location.href = '';
    
        }, 3000);
    
    }, err => {this.handleError(err); });

//this.reservationService.reserve(this.reservation);
  }

  onSubmitCommentForm(form: NgForm){
    console.log('submiting comment form');
  }

  handleError(err: HttpErrorResponse) {
    if (err.status === 404) {
      this.showError=true;
      setTimeout(() => {
        window.location.href = '';
  
      }, 3000);
    }
  }

}
