import { Component, OnInit } from '@angular/core';
import { AuthServiceService } from '../services/auth-service/auth-service.service';
import { UserServiceService } from '../services/user-service/user-service.service';
import { NgForm } from '@angular/forms';
import { SearchForm } from '../models/SearchForm';
import { AccommodationServiceService } from '../services/accommodationService/accommodation-service.service';
import { AdditionalServiceServiceService } from '../services/additionalServiceService/additional-service-service.service';
import { getLocaleExtraDayPeriods } from '@angular/common';
import { AdditionalService } from '../models/AdditionalService';

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
    console.log(this.searchForm);

  }

  serviceChanged(id: number, form: NgForm){
    var value = this.idServices.get(id);

    if(value == true){
      this.idServices.set(id, false);
    }else{
      this.idServices.set(id, true);
    }

     console.log('service changed');
  }
}
