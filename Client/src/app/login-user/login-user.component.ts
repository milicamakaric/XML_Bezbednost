import { Component, OnInit } from '@angular/core';
import { User } from '../models/User';
import { UserServiceService } from '../services/userService/user-service.service';
import { ActivatedRoute } from '@angular/router';
import { CheckboxControlValueAccessor } from '@angular/forms';


@Component({
  selector: 'app-login-user',
  templateUrl: './login-user.component.html',
  styleUrls: ['./login-user.component.css']
})

export class LoginUserComponent implements OnInit {

  user: User = new User();
  htmlStr: string;
  constructor(private u: UserServiceService, private route: ActivatedRoute) { }

  ngOnInit() {
  }


  loginUser() {
    console.log('Dodavanje' + this.user.email + ', pass: ' + this.user.password);
      // tslint:disable-next-line:align
    this.u.loginUser(this.user).subscribe(podaci => { this.checkUser(podaci) });
    }

  checkUser(logged){
    var loggedUser = logged as User;
    if(loggedUser.email == "error"){
      this.htmlStr = 'The e-mail or password is not correct.'
    }
    else{
      this.u.getSelfSigned().subscribe(podaci => { this.checkSelfSigned(podaci) });
    }
  }

  checkSelfSigned(data){
    var selfSigned = data as boolean;
    if(selfSigned){
      //ovde otvoriti index.html
      window.location.href = 'http://localhost:4200/pregled/6'
    }else{
      //poslati na stranicu za pravljenje self signed seritifikata
      window.location.href = 'http://localhost:4200/pregled/5'
    }
  }

}
