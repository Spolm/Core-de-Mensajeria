import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { map } from "rxjs/operators";
import { catchError } from 'rxjs/operators';
import { Observable } from 'rxjs';

import { environment } from '../../../environments/environment';
import { Channel } from './channel';


const API_URL = environment.apiUrl;

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(
    private http: Http
  ) { }

  public getAllChannels(): Observable<Channel[]>{
    return this.http
    .get(API_URL+'/channel')
    .pipe( map( response => {
      const channels = response.json();
      return channels.map( (channel) => new Channel(channel) );
    }))
    .pipe( catchError( err => {
      this.handleError;
      return null;
    }));
  }

  private handleError (error: Response | any) {
    console.error('ApiService::handleError', error);
    return Observable.throw(error);
  }
}
