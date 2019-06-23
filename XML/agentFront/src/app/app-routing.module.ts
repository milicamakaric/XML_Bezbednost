import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from '../app/login/login.component';
import { MainPageComponent } from './main-page/main-page.component';
import { RoomPricesComponent } from './room-prices/room-prices.component';

const routes: Routes = [
  {
    path: '',
    component: LoginComponent
    },
    {
      path: 'main-page',
      component: MainPageComponent
      },
      {
        path: 'room-prices/:acc_id/:ulogovan_id',
        component: RoomPricesComponent
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
