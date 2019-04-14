import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module' ;
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RegistrationUserComponent } from './registration-user/registration-user.component';
import { LoginUserComponent } from './login-user/login-user.component';
import { CertificateComponent } from './certificate/certificate.component';
import { HomePageComponent } from './home-page/home-page.component';
import { ListOfCertificatesComponent } from './list-of-certificates/list-of-certificates.component';
/*const appRoutes: Routes = [ {path: '', component : RegistrationUserComponent},
                            {path: 'pregled/:id', component : SoftwaresComponent} ];
*/
@NgModule({
  declarations: [
    AppComponent,
    RegistrationUserComponent,
    LoginUserComponent,
    CertificateComponent,
    HomePageComponent,
    ListOfCertificatesComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    /*RouterModule.forRoot(
      appRoutes,
      {enableTracing : true}
    )*/
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
