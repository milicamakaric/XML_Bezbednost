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

  user:User = new User();
  htmlStr: string;
  sendUser: User = new User();

  constructor(private userService: UserServiceService, private route: ActivatedRoute, private auth : AuthServiceService) { }

  ngOnInit() {
  }

  loginUser() {
<<<<<<< HEAD
    console.log('Dodavanje' + this.user.email + ', pass: ' + this.user.password);
   
      if (this.checkEmail(this.user.email)) {
          this.userService.loginUser(this.user).subscribe(podaci => { this.checkUser(podaci); } , err => {this.handleAuthError(err); });
      } else {
        this.htmlStr = 'The e-mail is not valid.';
      }
=======
    console.log('Dodavanje' + this.user.email + ', pass: ' + this.user.passsword);
    this.userService.loginUser(this.user).subscribe(podaci => {
       this.checkUser(podaci); 
      } , err => {this.handleAuthError(err); });
    
>>>>>>> 9ee684a96cfa6e167bf79716894a23274239ff1c
   }

checkUser(logged) {
  let user_token= logged as UserTokenState;
  // tslint:disable-next-line:triple-equals
  if(user_token.accessToken == 'error') {
    this.htmlStr = 'The e-mail or password is not correct.';
  }  else if (user_token.accessToken == 'notActivated') 
  {
	 this.htmlStr = 'Your account has not been activated yet.'; 
  }else {
    this.auth.setJwtToken(user_token.accessToken);
    console.log(user_token.accessToken);
    this.userService.getLogged(user_token.accessToken).subscribe(podaci => {
      //this.ssCertificate(podaci)
      console.log('return: ' + podaci);
      window.location.href = 'http://localhost:4200/main-page';
    });
  }
}
checkEmail(text): boolean {
  //const patternMail = /\b[\w\.-]+@[\w\.-]+\.\w{2,4}\b/;
  // tslint:disable-next-line:max-line-length
  const patternMail = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
 // return re.test(String(email).toLowerCase());
  if (!patternMail.test(text)) {
    alert('Incorrect email.');
    return false;
  }
<<<<<<< HEAD
  return true;
=======

  if (admin){
    //this.userService.getSelfSigned().subscribe(podaci => { this.checkSelfSigned(podaci, loggedUser.id) });
  } else if (obican) {
      /*if (loggedUser.certificated == false) {
        window.location.href = 'http://localhost:4200/certificate/nonself/' + loggedUser.id;
      } else {
      window.location.href = 'http://localhost:4200'; // ovde treba preusmeriti na pocetnu
      }*/
}

>>>>>>> 9ee684a96cfa6e167bf79716894a23274239ff1c
}

  handleAuthError(err: HttpErrorResponse) {
    if (err.status === 404) {
      alert('Entered email is not valid!');
    }
  }

}

