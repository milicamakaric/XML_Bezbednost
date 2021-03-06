import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';

import { AppRoutingModule } from './app-routing.module'
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LoginComponent } from './login/login.component';
import { MainPageComponent } from './main-page/main-page.component';
import { ZuulPath } from './ZuulPath';
import { RoomPricesComponent } from './room-prices/room-prices.component';
import { MessagesComponent } from './messages/messages.component';
import { AnswerFormComponent } from './answer-form/answer-form.component';
import { ReservationsComponent } from './reservations/reservations.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    MainPageComponent,
    RoomPricesComponent,
    MessagesComponent,
    AnswerFormComponent,
    ReservationsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [ZuulPath],
  bootstrap: [AppComponent]
})
export class AppModule { }
