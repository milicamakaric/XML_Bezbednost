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
  constructor(private a: UserServiceService, private route: ActivatedRoute) { }

  ngOnInit() {
  }

  validateUser() {
      console.log('Dodavanje' + this.user);
        // tslint:disable-next-line:align
        this.a.addUser(this.user).subscribe(podaci => { window.location.href = 'http://localhost:4200/login';
        });
      }
}
