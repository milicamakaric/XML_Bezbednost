import { Component, OnInit } from '@angular/core';
import { User } from '../models/User';
import { UserServiceService } from '../services/userService/user-service.service';
import { ActivatedRoute } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-registration-user',
  templateUrl: './registration-user.component.html',
  styleUrls: ['./registration-user.component.css']
})
export class RegistrationUserComponent implements OnInit {

  user: User = new User();
  showError: boolean;
  constructor(private a: UserServiceService, private route: ActivatedRoute) { }

  ngOnInit() {
  }

  validateUser() {
      console.log('Dodavanje' + this.user);
        // tslint:disable-next-line:align
        this.showError = false;
        if(this.user.email === ''){
          this.showError = true;
        }else if(this.user.name === ''){
          this.showError = true; 
        }else if(this.user.surname === ''){
          this.showError = true; 
        }else if(this.user.password === ''){
          this.showError = true;    
        }
        if(this.showError == false){
          this.a.addUser(this.user).subscribe(podaci => { window.location.href = 'http://localhost:4200/login';
          });
        }
      }
}
