import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import {UserServiceService} from '../services/userService/user-service.service';

@Component({
  selector: 'app-rating',
  templateUrl: './rating.component.html',
  styleUrls: ['./rating.component.css']
})
export class RatingComponent implements OnInit {

  constructor(private route: ActivatedRoute, private u: UserServiceService) { }
  stars = new FormControl();
  ngOnInit() {
  }

  rating(){

    if(this.stars.value==null)
    {
      alert("Please, rate us with 1-5 stars!");
    }
    else
    {
      console.log("Oznaceno je " + this.stars.value);
      this.u.rateOurApp(this.stars.value as number).subscribe(data => { this.validateRate(data)});
    }

  }

  validateRate(podaci)
  {
    let validno = podaci as boolean;
    if(validno)
    {
      window.location.href= 'http://localhost:4200';
    }
    else
    {
      alert("Please, rate us with 1-5 stars!");
    }
  }

}
