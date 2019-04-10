import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router'
import { RegistrationUserComponent } from './registration-user/registration-user.component'
import { SoftwaresComponent } from './software/softwares.component'
import { LoginUserComponent } from './login-user/login-user.component'

const routes: Routes = [

  {
    path: 'registration',
    component: RegistrationUserComponent
  },
  {
    path: 'pregled/:id',
    component: SoftwaresComponent
  },
  {
    path: '',
    component: SoftwaresComponent
  },
  {
    path: 'login',
    component: LoginUserComponent
  }
];

@NgModule({
  declarations: [],
  imports: [
    CommonModule, RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
