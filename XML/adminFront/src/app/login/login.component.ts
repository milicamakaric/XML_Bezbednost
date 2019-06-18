import { Component, OnInit } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { CheckboxControlValueAccessor } from '@angular/forms';
import { identifierModuleUrl } from '@angular/compiler';
import { User } from '../models/User';
import { UserServiceService } from '../services/userService/user-service.service';
import { AuthServiceService } from '../services/authService/auth-service.service';
import { UserTokenState } from '../models/UserTokenState';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user: User = new User();
  htmlStr: string;
  sendUser: User = new User();

  constructor(private userService: UserServiceService, private route: ActivatedRoute, private auth : AuthServiceService) { }

  ngOnInit() {
  }

  loginUser() {
    console.log('Dodavanje' + this.user.email + ', pass: ' + this.user.password);
    this.userService.loginUser(this.user).subscribe(podaci => {
       this.checkUser(podaci); 
      } , err => {this.handleAuthError(err); });
    
   }

checkUser(logged) {
  let user_token= logged as UserTokenState;
  // tslint:disable-next-line:triple-equals
  if(user_token.accessToken == 'error') {
    this.htmlStr = 'The e-mail or password is not correct.';
  } else {
    this.auth.setJwtToken(user_token.accessToken);
    console.log(user_token.accessToken);
    this.userService.getLogged(user_token.accessToken).subscribe(podaci => {
      //this.ssCertificate(podaci)
      console.log('return: ' + podaci);
      window.location.href = 'http://localhost:4200/main-page';
    });
  }
}

ssCertificate(data){
  var loggedUser = data as User;
  var admin = false as boolean;
  var obican = false as boolean;

  for(let role of loggedUser.roles)
  {
    if(role.name == "ROLE_ADMIN")
      admin=true;
    if(role.name == "ROLE_USER")
      obican=true;
  }

  if (admin){
    //this.userService.getSelfSigned().subscribe(podaci => { this.checkSelfSigned(podaci, loggedUser.id) });
  } else if (obican) {
      if (loggedUser.certificated == false) {
        window.location.href = 'http://localhost:4200/certificate/nonself/' + loggedUser.id;
      } else {
      window.location.href = 'http://localhost:4200'; // ovde treba preusmeriti na pocetnu
      }
}

}

checkSelfSigned(data, id) {
  let selfSigned = data as boolean;
  if (selfSigned) {
    // ovde otvoriti index.html
    window.location.href = 'http://localhost:4200';
  } else {
    // poslati na stranicu za pravljenje self signed seritifikata
    window.location.href = 'http://localhost:4200/certificate/self/' + id;
  }
}
handleAuthError(err: HttpErrorResponse) {
  if (err.status === 404) {
    alert('Entered email is not valid!');
  }
}

}
