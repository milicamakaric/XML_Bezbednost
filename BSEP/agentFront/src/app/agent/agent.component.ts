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
    document.getElementById('response').setAttribute('hidden', 'true');
    document.getElementById('img').setAttribute('hidden', 'true');
  }

  communicate(){
    this.service.communicate(this.messageText).subscribe(data => {
      console.log(data);
      document.getElementById('message').setAttribute('hidden', 'true');
      document.getElementById('button_communicate').setAttribute('hidden', 'true');
      document.getElementById('response').removeAttribute('hidden');
      document.getElementById('img').removeAttribute('hidden');
      document.getElementById('response').innerHTML = data;
    });
  }

}
