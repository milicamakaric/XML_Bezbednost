import { Component, OnInit } from '@angular/core';
import { AgentServiceService } from '../services/agent-service.service'

@Component({
  selector: 'app-agent',
  templateUrl: './agent.component.html',
  styleUrls: ['./agent.component.css']
})
export class AgentComponent implements OnInit {

  messageText: string;

  constructor(private service: AgentServiceService) { }

  ngOnInit() {
  }

  communicate(){
    this.service.communicate(this.messageText).subscribe(data => {console.log(data)});
  }

}
