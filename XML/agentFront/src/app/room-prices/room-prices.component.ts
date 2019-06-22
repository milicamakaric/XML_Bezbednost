import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { RoomServiceService } from 'app/services/room-service/room-service.service';
import { FormGroup, FormControl, Validators, NgForm } from '@angular/forms';
import { PriceForNight } from 'app/model/PriceForNight';

@Component({
  selector: 'app-room-prices',
  templateUrl: './room-prices.component.html',
  styleUrls: ['./room-prices.component.css']
})
export class RoomPricesComponent implements OnInit {
acc_id: number;
ulogovan_id:number;
rooms: Array<any>=[];
show: number = 0;
priceForm: FormGroup;
price: FormControl;
startDate:FormControl;
endDate:FormControl;
selectedRoomId : number;
specialPrice: PriceForNight = new PriceForNight();

  constructor( private route: ActivatedRoute, private roomService: RoomServiceService) {
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
  }

}
