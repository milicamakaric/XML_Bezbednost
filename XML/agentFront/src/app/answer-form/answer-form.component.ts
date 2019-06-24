import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AuthServiceService } from 'app/services/auth-service/auth-service.service';
import { UserServiceService } from 'app/services/user-service/user-service.service';
import { MessageServiceService } from 'app/services/message-service/message-service.service';
import { FormGroup, FormControl, Form, Validators, NgForm } from '@angular/forms';
import { Message } from 'app/model/Message';

@Component({
  selector: 'app-answer-form',
  templateUrl: './answer-form.component.html',
  styleUrls: ['./answer-form.component.css']
})
export class AnswerFormComponent implements OnInit {

  clientId: number;
  ulogovan_id: number;
  clientName: string;
  clientSurname:string;
  message: Message = new Message();

  answerForm: FormGroup;
  client: FormControl;
  title: FormControl;
  content: FormControl;

  constructor(private route: ActivatedRoute,private auth: AuthServiceService,private userService : UserServiceService, private messageService: MessageServiceService) {
    this.route.params.subscribe( params => {this.clientId = params.clientId;  this.clientName=params.clientName; this.clientSurname=params.clientSurname; this.ulogovan_id=params.ulogovan_id;});
   }

  ngOnInit() {
    this.createFormControls();
    this.createForm();
  }

  createFormControls()
  {
    this.client=new FormControl('');
    this.title=new FormControl('', Validators.required);
    this.content=new FormControl('', Validators.required);
  }

  createForm(){
    this.answerForm = new FormGroup({
      client: this.client,
      title: this.title,
      content: this.content
    });
  }

  onSubmitAnswerForm(form: NgForm)
  {
    console.log("usao u submit");
    this.message.client.id = this.clientId;
    this.message.agent.id = this.ulogovan_id;
    this.message.sending = false;
    this.message.title=this.answerForm.value.title;
    this.message.content=this.answerForm.value.content;

    console.log("Mess " + this.message);
  }
}
