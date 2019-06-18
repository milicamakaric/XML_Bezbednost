import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { LoginComponent } from './login/login.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AdminPath } from '../app/AdminPath';
import { AuthPath } from '../app/AuthPath';
import { MainPageComponent } from './main-page/main-page.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    MainPageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [AdminPath, AuthPath],
  bootstrap: [AppComponent]
})
export class AppModule { }
