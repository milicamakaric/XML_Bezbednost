import { Component, OnInit } from '@angular/core';
import { AuthServiceService } from '../services/auth-service/auth-service.service';
import { UserServiceService } from '../services/user-service/user-service.service';
import { NgForm } from '@angular/forms';
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
  hotels: Array<AccommodationDTO> = [];
  show: number= 0;
  sortForm: SortForm = new SortForm();
  rooms: Array<Room> =[];
  roomsDTO: Array<RoomDTO> =[];

  showRooms: boolean =false;
  selectedHotel: AccommodationDTO= new AccommodationDTO()
  /*
  parkingLot: boolean;
  wifi: boolean;
  pet: boolean;
  tv: boolean;
  kitchen: boolean;
  bathroom: boolean;
  */
  
  types: any;
  services: AdditionalService[];
  idServices: Map<number, boolean> = new Map<number, boolean>();

  constructor(private auth: AuthServiceService, private userService: UserServiceService,
              private accommodationService: AccommodationServiceService, private additionalService: AdditionalServiceServiceService) { }

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

   this.getTypes();
   this.getServices();
   
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
      console.log('sortiraj');
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
    this.showRooms=true;
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
}
