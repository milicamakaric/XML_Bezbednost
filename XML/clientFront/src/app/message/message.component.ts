import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AuthServiceService } from '../services/auth-service/auth-service.service';
import { UserServiceService } from '../services/user-service/user-service.service';
import { RoomServiceService } from '../services/room-service/room-service.service';
import { Agent } from '../models/Agent';
import { User } from '../models/User';
import { FormGroup, FormControl, Form, Validators, NgForm } from '@angular/forms';
import { Message } from '../models/Message';
import { MessageServiceService } from '../services/message-service/message-service.service';

@Component({
  selector: 'app-message',
  templateUrl: './message.component.html',
  styleUrls: ['./message.component.css']
})
export class MessageComponent implements OnInit {

  room_id: number;
  agent: Agent = new Agent();
  logged: User = new User();
  message: Message = new Message();

  messageForm: FormGroup;
  agentForm: FormControl;
  title: FormControl;
  content: FormControl;


  constructor( private route: ActivatedRoute,private auth: AuthServiceService,private userService : UserServiceService, private roomService: RoomServiceService, private messageService: MessageServiceService) {
    this.route.params.subscribe( params => {this.room_id = params.room_id; });
   }

  ngOnInit() {

    this.createFormControls();
    this.createForm();

    this.roomService.getAgentByRoomId(this.room_id).subscribe(data => {
      console.log(data);
      this.agent=data as Agent;
      console.log(this.agent);
      this.getUlogovan();
    });

  }

  createFormControls()
  {
    this.agentForm=new FormControl('');
    this.title=new FormControl('', Validators.required);
    this.content=new FormControl('', Validators.required);
  }

  createForm(){
    this.messageForm = new FormGroup({
      agentForm: this.agentForm,
      title: this.title,
      content: this.content
    });
  }
  getUlogovan()
  {
    this.userService.getLogged(this.auth.getJwtToken()).subscribe(podaci => {
      //this.ssCertificate(podaci)
      console.log('return: ' + podaci);
     this.logged=podaci as User;
     console.log(this.logged);
    });
  }

  onSubmitMessageForm(form: NgForm)
  {
    var mess : Message = new Message();
    mess.client.id = this.logged.id;
    mess.agent.id = this.agent.id;
    mess.sending = true;
    mess.title=this.messageForm.value.title;
    mess.content=this.messageForm.value.content;
    console.log("submit");
    this.messageService.sendMessage(mess).subscribe(data => {
     
      window.location.href = "";
    });
  }
}
