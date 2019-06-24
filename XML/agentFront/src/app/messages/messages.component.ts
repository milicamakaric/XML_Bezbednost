import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AuthServiceService } from 'app/services/auth-service/auth-service.service';
import { UserServiceService } from 'app/services/user-service/user-service.service';
import { MessageServiceService } from 'app/services/message-service/message-service.service';
import { Client } from 'app/model/Client';
import { FormGroup, FormControl, Validators, NgForm } from '@angular/forms';
import { MessageDTO } from 'app/model/MessageDTO';

@Component({
  selector: 'app-messages',
  templateUrl: './messages.component.html',
  styleUrls: ['./messages.component.css']
})
export class MessagesComponent implements OnInit {

  ulogovan_id : number;
  messages: Array<MessageDTO> =[];
  show: number =0;


  constructor(private route: ActivatedRoute,private auth: AuthServiceService,private userService : UserServiceService, private messageService: MessageServiceService) { 
    this.route.params.subscribe( params => {this.ulogovan_id = params.ulogovan_id; });
  }

  ngOnInit() {
   
    this.messageService.getMessagesForAgent(this.ulogovan_id).subscribe(data => { this.messages = data as MessageDTO[]});
    console.log(this.messages);
    

  }

  reply(clientId: number, clientName: string, clientSurname: string)
  {
    console.log("Client " + clientId + " " + clientName + " " + clientSurname);
    window.location.href="answer/"+clientId+"/"+clientName+"/"+clientSurname + "/" + this.ulogovan_id;
   
  }


}
