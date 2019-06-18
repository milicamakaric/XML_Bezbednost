import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { AdminPath } from '../../AdminPath';
import { AccommodationType } from '../../models/AccommodationType';
@Injectable({
  providedIn: 'root'
})
export class AccommodationServiceService {

  constructor(private http: HttpClient,private adminPath: AdminPath) { }

  addAccommodationType(a : AccommodationType){
    console.log('Dodavanje novog tipa smjestaja ');
    return this.http.post(this.adminPath.path + 'api/accommTypes/addNew', a );  
  }
}
