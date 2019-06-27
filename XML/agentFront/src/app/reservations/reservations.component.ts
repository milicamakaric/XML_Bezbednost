import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AuthServiceService } from 'app/services/auth-service/auth-service.service';
import { UserServiceService } from 'app/services/user-service/user-service.service';
import { Reservation } from 'app/model/Reservation';
import { ReservationService } from 'app/services/reservation-service/reservation.service';

@Component({
  selector: 'app-reservations',
  templateUrl: './reservations.component.html',
  styleUrls: ['./reservations.component.css']
})
export class ReservationsComponent implements OnInit {

  ulogovan_id: number;
  reservations: Array<Reservation> =[];
  constructor(private route: ActivatedRoute,private auth: AuthServiceService,private userService : UserServiceService, private reservationService: ReservationService) { 
    this.route.params.subscribe( params => {this.ulogovan_id = params.ulogovan_id; });
  }

  ngOnInit() {
    this.reservationService.getAgentReservations(this.ulogovan_id).subscribe(data =>{
      console.log(data);
      this.reservations = data as Array<Reservation>;
      console.log(this.reservations);
    })
  }

  enabledButtonActive(res_startDate: Date)
  {
    console.log("Start date: " + res_startDate);
  }

  enabledButtonFinish(res_endDate: Date)
  {
    console.log("End date: " + res_endDate);
  }

}
