import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { AuthServiceService } from '../auth-service/auth-service.service';
import { ZuulPath } from 'src/app/ZuulPath';
import { SearchForm } from 'src/app/models/SearchForm';
import { SortForm } from 'src/app/models/SortForm';
import { AccommodationDTO } from 'src/app/models/AccommodationDTO';
@Injectable({
  providedIn: 'root'
})
export class AccommodationServiceService {

  

  constructor(private http: HttpClient, private auth: AuthServiceService, private zuulPath: ZuulPath) {
    
  }

  getTypes(){
    console.log('getTypes u servisu');
    return this.http.get(this.zuulPath.path + 'megatravelxml/api/accommodation/getTypes', {headers: this.auth.createAuthorizationTokenHeader()});
  }

  search(searchForm: SearchForm)
  {
    console.log("Usao u search u accService");
    return this.http.post(this.zuulPath.path + 'megatravelxml/api/accommodation/search', searchForm,  {headers: this.auth.createAuthorizationTokenHeader()});
  }

  sortingHotels(sortForm: SortForm, hotels: Array<AccommodationDTO>) {
    var item = sortForm.sortItem;
    console.log(item);
    var type = sortForm.sortType;
    console.log(type);
    console.log(hotels.length);
    console.log('u sortingu je');
    var sending= item + '=' + type;
    // tslint:disable-next-line:max-line-length
    return this.http.post(this.zuulPath.path + 'megatravelxml/api/accommodation/sort/' + sending, hotels,  {headers: this.auth.createAuthorizationTokenHeader()});
  }
}
