import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AuthServiceService } from 'app/services/auth-service/auth-service.service';
import { UserServiceService } from 'app/services/user-service/user-service.service';
import { Reservation } from 'app/model/Reservation';
import { ReservationService } from 'app/services/reservation-service/reservation.service';
import { ReservationDTO } from 'app/model/ReservationDTO';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-reservations',
  templateUrl: './reservations.component.html',
  styleUrls: ['./reservations.component.css']
})
export class ReservationsComponent implements OnInit {

  ulogovan_id: number;
  reservations: Array<ReservationDTO> =[];
  idResActive: Map<number, boolean> = new Map<number, boolean>();
  idResFinished: Map<number, boolean> = new Map<number, boolean>();
  constructor(private route: ActivatedRoute,private auth: AuthServiceService,private userService : UserServiceService, private reservationService: ReservationService) { 
    this.route.params.subscribe( params => {this.ulogovan_id = params.ulogovan_id; });
  }

  ngOnInit() {
    this.reservationService.getAgentReservations(this.ulogovan_id).subscribe(data =>{
      console.log(data);
      this.reservations = data as Array<ReservationDTO>;
      console.log(this.reservations);
      this.createMap(this.reservations);
    })
  }

  createMap(reservations: Array<ReservationDTO>)
  {
    for(let r of reservations)
    {
      this.reservationService.canBeActive(r.id).subscribe(data =>{
        console.log(data);
       this.idResActive.set(r.id, data as boolean);
      });

      this.reservationService.canBeFinished(r.id).subscribe(data =>{
        console.log(data);
        this.idResFinished.set(r.id, data as boolean);
      });
    }
  }
  enabledButtonActive(res_id: number)
  {
    return this.idResActive.get(res_id);
    
  }

  enabledButtonFinish(res_id: number)
  {
    return this.idResFinished.get(res_id);
  }

}
