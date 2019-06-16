import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { RegistrationUserComponent } from './registration-user/registration-user.component';
import { LoginUserComponent } from './login-user/login-user.component';
import { CertificateComponent } from './certificate/certificate.component';
import { HomePageComponent } from './home-page/home-page.component';
import {ListOfCertificatesComponent} from './list-of-certificates/list-of-certificates.component';
import { RatingComponent } from './rating/rating.component';

const routes: Routes = [
  {
    path: '',
    component: HomePageComponent
  },
  {
    path: 'registration',
    component: RegistrationUserComponent
  },
  {
    path: 'login',
    component: LoginUserComponent
  },
  {
    path: 'certificate/:self/:id',
    component: CertificateComponent
  },
  {
    path: 'certificate/:nonself/:id',
    component: CertificateComponent
  },
  {
    path: 'list-of-certificates/:id',
    component: ListOfCertificatesComponent
  },
  {
    path: 'rateus',
    component: RatingComponent
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
