import { Component, OnInit } from '@angular/core';
import { SoftwareServiceService } from '../services/softwareService/software-service.service';
import { CertificateServiceService } from '../services/certificateService/certificate-service.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-softwares',
  templateUrl: './softwares.component.html',
  styleUrls: ['./softwares.component.css']
})
export class SoftwaresComponent implements OnInit {

  id: string;
  certificates: Object;
  constructor(private softwareService: SoftwareServiceService, private certificateService: CertificateServiceService, private route: ActivatedRoute) {
    //this.a.getSoftwares().subscribe(podaci => {});
  }

  ngOnInit() {
    //tslint:disable:no-string-literal
    //this.id = this.route.snapshot.params['id'];
    //tslint:enable:no-string-literal
    //console.log('Pronadjen id je ' + this.id);
    this.certificateService.showCertificates().subscribe(data => this.certificates = data);
  }


}
