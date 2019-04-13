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
  checkUser: User = new User();
  hideError: boolean;
  errorMessage: string;
  passwordError: boolean;
  passwordErrorMessage: string;
  constructor(private a: UserServiceService, private route: ActivatedRoute) { }

  ngOnInit() {
    this.hideError = true;
    this.passwordError = true;
       
  }
  /*checkPassword(){
      if(this.user.password.length < 8){
        this.hideError = false;
        this.passwordError = false;
        this.passwordErrorMessage = "";        
      }else if(this.user.password)

  }  */
  validateUser() {
      console.log('Dodavanje' + this.user);
        // tslint:disable-next-line:align
        this.errorMessage = '';
        this.hideError = true;
        this.passwordError = true;
        if(!this.user.name){
          this.hideError = false;
          this.errorMessage = 'Name is required.';
        }else if(!this.user.surname){
          this.hideError = false; 
          this.errorMessage = 'Surname is required.';
        
        }else if(!this.user.email){
          this.hideError = false; 
          this.errorMessage = 'Mail is required.';
        
        }else if(!this.user.password){
          
          this.errorMessage = 'Password is required.';
          this.hideError = false;    
        }
    /*    if(this.hideError == true){
          this.checkPassword();
        }*/
        if(this.hideError == true){
          this.a.addUser(this.user).subscribe(podaci => { 
                this.checkUser = podaci as User;
                if(!podaci){
                  console.log('podaci null');
                  this.hideError = false;
                  this.errorMessage = 'All fields are required.';
                 
                }else if(this.checkUser.email === 'error'){
                  console.log('mejl nije ok');
                  
                  this.hideError = false;
                  this.errorMessage = 'Mail is already taken.';
                }else{
                  console.log('prebaci u login');
                  
                  window.location.href = 'http://localhost:4200/login';
                }
            });
        }
      }
}
