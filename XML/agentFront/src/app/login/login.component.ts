import { Component, OnInit } from '@angular/core';
import { User } from '../model/User';
import { UserServiceService } from '../services/user-service.service';
import { ActivatedRoute } from '@angular/router';
import {UserTokenState} from '../model/UserTokenState';
import { HttpErrorResponse } from '@angular/common/http';
import { AuthServiceService } from '../services/auth-service.service';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit {
  
  user: User = new User();
  
  constructor(private u: UserServiceService, private route: ActivatedRoute, private auth : AuthServiceService) { }

  ngOnInit() {
  }

  loginUser(){
    console.log("hellou");
    this.u.loginUser(this.user).subscribe(podaci => { this.checkUser(podaci); } , err => {this.handleAuthError(err); });
      
  }

  checkUser(logged) {
    let user_token= logged as UserTokenState;
    // tslint:disable-next-line:triple-equals
    if(user_token.accessToken == 'error')	 {
      //this.htmlStr = 'The e-mail or password is not correct.';
    } else {
      this.auth.setJwtToken(user_token.accessToken);
      console.log(user_token.accessToken);
     // this.u.getLogged(user_token.accessToken).subscribe(podaci => {this.ssCertificate(podaci)});
    }
  }
  handleAuthError(err: HttpErrorResponse) {
    if (err.status === 404) {
      alert('Entered email is not valid!');
    }
  }

}
