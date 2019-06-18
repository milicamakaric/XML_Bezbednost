import { Component, OnInit } from '@angular/core';
import { AccommodationType } from 'app/models/AccommodationType';
import { AdditionalService } from '../models/AdditionalService';
import { AccommodationServiceService} from '../services/accommodationService/accommodation-service.service';
import {AdditionalServiceServiceService} from '../services/additionalServiceService/additional-service-service.service';
import { ActivatedRoute } from '@angular/router';
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
    constructor(private accommodationService: AccommodationServiceService,private route: ActivatedRoute,private additinalSer:AdditionalServiceServiceService) { 
      this.show = false;
      this.showService = false;
    }

  ngOnInit() {
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
}
