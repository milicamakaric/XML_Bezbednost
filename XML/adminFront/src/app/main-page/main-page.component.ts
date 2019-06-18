import { Component, OnInit } from '@angular/core';
import { AccommodationType } from 'app/models/AccommodationType';
import { AccommodationServiceService} from '../services/accommodationService/accommodation-service.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})
export class MainPageComponent implements OnInit {
    show: boolean;
    type: AccommodationType;
    constructor(private accommodationService: AccommodationServiceService,private route: ActivatedRoute) { 
      this.show = false;
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
}
