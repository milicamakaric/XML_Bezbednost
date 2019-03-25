import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module' ;
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SoftwaresComponent } from './softwares/softwares.component';
const appRoutes: Routes = [ {path: '', component : SoftwaresComponent},
                            {path: 'pregled', component : SoftwaresComponent} ];

@NgModule({
  declarations: [
    AppComponent,
    SoftwaresComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule.forRoot(
      appRoutes,
      {enableTracing : true}
    )
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
