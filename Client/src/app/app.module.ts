import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module' ;
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SoftwaresComponent } from './software/softwares.component';
import { RegistrationUserComponent } from './registration-user/registration-user.component';
import { LoginUserComponent } from './login-user/login-user.component';
import { CertificateComponent } from './certificate/certificate.component';
import { HomePageComponent } from './home-page/home-page.component';
/*const appRoutes: Routes = [ {path: '', component : RegistrationUserComponent},
                            {path: 'pregled/:id', component : SoftwaresComponent} ];
*/
@NgModule({
  declarations: [
    AppComponent,
    SoftwaresComponent,
    RegistrationUserComponent,
    LoginUserComponent,
    CertificateComponent,
    HomePageComponent
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
