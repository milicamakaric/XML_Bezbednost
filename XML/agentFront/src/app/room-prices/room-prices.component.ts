import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { RoomServiceService } from 'app/services/room-service/room-service.service';
import { FormGroup, FormControl, Validators, NgForm } from '@angular/forms';
import { PriceForNight } from 'app/model/PriceForNight';
import { PriceServiceService} from '../services/price-service/price-service.service';
import { UserServiceService} from '../services/user-service/user-service.service';
import { AuthServiceService } from 'app/services/auth-service/auth-service.service';
import {User} from 'app/model/User';
@Component({
  selector: 'app-room-prices',
  templateUrl: './room-prices.component.html',
  styleUrls: ['./room-prices.component.css']
})
export class RoomPricesComponent implements OnInit {
acc_id: number;
ulogovan_id:number;
rooms: any = [];
show: number = 0;
priceForm: FormGroup;
price: FormControl;
startDate:FormControl;
endDate:FormControl;
selectedRoomId : number;
specialPrice: PriceForNight = new PriceForNight();

  constructor( private route: ActivatedRoute,private auth: AuthServiceService,private userService : UserServiceService, private roomService: RoomServiceService,private priceService:PriceServiceService) {
    this.route.params.subscribe( params => {this.acc_id = params.acc_id, this.ulogovan_id = params.ulogovan_id; });
   }

  ngOnInit() {

    this.createFormControls();
    this.createForm();

    this.roomService.getRooms(this.acc_id, this.ulogovan_id).subscribe(data => {console.log(data); this.rooms = data;});
    
  }

  createFormControls()
  {
    this.price= new FormControl('', Validators.required);
    this.startDate= new FormControl('', Validators.required);
    this.endDate= new FormControl('', Validators.required);
  }

  createForm()
  {
    this.priceForm = new FormGroup({
      price: this.price,
      startDate: this.startDate,
      endDate: this.endDate
    });
  }
  addPrice(room_id: number)
  {
    this.show=1;
    this.selectedRoomId = room_id;
  }

  onSubmitPriceForm(form: NgForm)
  {
    console.log("Selektovana je soba sa id: " + this.selectedRoomId);
    this.specialPrice.price=this.priceForm.value.price;
    this.specialPrice.startDate=this.priceForm.value.startDate;
    this.specialPrice.endDate = this.priceForm.value.endDate;
    console.log(this.specialPrice);

    this.userService.getLogged(this.auth.getJwtToken()).subscribe(podaci => {
      //this.ssCertificate(podaci)
      console.log('return: ' + podaci);
      var user = podaci as User;
      console.log(user.role);
     // window.location.href = 'http://localhost:4202/main-page';
    });
    this.priceService.addSpecialPrice(this.specialPrice, this.selectedRoomId);
  }

}
