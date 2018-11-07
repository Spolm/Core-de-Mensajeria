import { Injectable } from '@angular/core';
import { Response } from '@angular/http';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';


const apiRestUrl = 'http://localhost:8080/CoreMensajeria_war_exploded/';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor( private http: HttpClient){

  }

  private extractData(res: Response) {
    let body = res;
    return body || { };
  }

  public GetAllIntegrators(): Observable<any>{
     
    return this.http.get( apiRestUrl + 'integrators' ).
      pipe( map( this.extractData ) );
  
  }

  public GetSMSIntegrators(): Observable<any>{
    return this.http.get( apiRestUrl + 'channel/i/1' ).
      pipe( map( this.extractData ) );
  }

  public GetMailIntegrators(): Observable<any>{
    return this.http.get( apiRestUrl + 'channel/i/2' ).
      pipe( map( this.extractData ) );
  }
  
}


