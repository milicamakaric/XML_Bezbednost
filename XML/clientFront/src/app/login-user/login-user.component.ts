import { Component, OnInit } from '@angular/core';
import { AuthServiceService } from '../services/auth-service/auth-service.service';
import { HttpErrorResponse } from '@angular/common/http';
import { UserTokenState } from '../models/UserTokenState';
import { User } from '../models/User';
import { UserServiceService } from '../services/user-service/user-service.service';
import { ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-login-user',
  templateUrl: './login-user.component.html',
  styleUrls: ['./login-user.component.css']
})
export class LoginUserComponent implements OnInit {

  user:User = new User();
  htmlStr: string;
  sendUser: User = new User();

  constructor(private userService: UserServiceService, private route: ActivatedRoute, private auth : AuthServiceService) { }

  ngOnInit() {
  }

  loginUser() {
    console.log('Dodavanje' + this.user.email + ', pass: ' + this.user.password);
   
      if (this.checkEmail(this.user.email)) {
          this.userService.loginUser(this.user).subscribe(podaci => { this.checkUser(podaci); } , err => {this.handleAuthError(err); });
      } else {
        this.htmlStr = 'The e-mail is not valid.';
      }

   }

checkUser(logged) {
  let user_token= logged as UserTokenState;
  // tslint:disable-next-line:triple-equals
  if(user_token.accessToken == 'error') {
    this.htmlStr = 'The e-mail or password is not correct.';
  } else if (user_token.accessToken == 'notActivated') 
  {
	 this.htmlStr = 'Your account has not been activated yet.'; 
  }else {
    this.auth.setJwtToken(user_token.accessToken);
    console.log(user_token.accessToken);
    this.userService.getLogged(user_token.accessToken).subscribe(podaci => {
      //this.ssCertificate(podaci)
      console.log('return: ' + podaci);
      window.location.href = 'http://localhost:4201';
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
  return true;
}

  handleAuthError(err: HttpErrorResponse) {
    if (err.status === 404) {
      alert('Entered email is not valid!');
    }
  }

}
