import { Component, OnInit } from '@angular/core';
import { SoftwareServiceService } from '../services/softwareService/software-service.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-softwares',
  templateUrl: './softwares.component.html',
  styleUrls: ['./softwares.component.css']
})
export class SoftwaresComponent implements OnInit {

  constructor(private a: SoftwareServiceService, private route: ActivatedRoute) {
    this.a.getSoftwares().subscribe(podaci => {
    });
  }

  ngOnInit() {
  }


}
