import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { catchError } from 'rxjs/operators';

import { environment } from '../../../environments/environment';
import { Integrator } from './integrator';

const API_URL = environment.apiUrl;

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(
    private http: Http
  ) { }

  public getAllIntegrators(): Observable<Integrator[]>{
    return this.http
    .get(API_URL+'/integrators')
    .pipe( map( response => {
      const integrators = response.json();
      return integrators.map( (integrator) => new Integrator(integrator) );
    }))
    .pipe( catchError( err => {
      this.handleError( err );
      return null;
    }));
  }

  public getIntegratorsPerChannel(index: string): Observable<Integrator[]>{
    return this.http
    .get(API_URL+'/channel/i/'+index)
    .pipe( map( response => {
      const integrators = response.json();
      return integrators.map( (integrator) => new Integrator(integrator) );
    }))
    .pipe( catchError( err => {
      this.handleError( err );
      return null;
    }));
  }

  private handleError (error: Response | any) {
    console.error('ApiService::handleError', error);
    return Observable.throw(error);
  }

}