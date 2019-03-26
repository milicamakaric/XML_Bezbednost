import { Component, OnInit } from '@angular/core';
import { SoftwareServiceService } from '../services/softwareService/software-service.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-softwares',
  templateUrl: './softwares.component.html',
  styleUrls: ['./softwares.component.css']
})
export class SoftwaresComponent implements OnInit {

  id: string;
  constructor(private a: SoftwareServiceService, private route: ActivatedRoute) {
    this.a.getSoftwares().subscribe(podaci => {
    });
  }

  ngOnInit() {
    /* tslint:disable:no-string-literal */
    this.id = this.route.snapshot.params['id'];
    /* tslint:enable:no-string-literal */
    console.log('Pronadjen id je ' + this.id);
  }


}
