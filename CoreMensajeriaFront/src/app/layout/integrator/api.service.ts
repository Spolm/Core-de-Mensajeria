import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs';
import { map, tap } from 'rxjs/operators';
import { catchError } from 'rxjs/operators';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';


import { environment } from '../../../environments/environment';
import { Integrator } from './integrator';

const API_URL = environment.apiUrl;
const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json'
  })
};
@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(
    private http: Http,
    private http2: HttpClient
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

  public getIntegratorsPerChannel(index: number): Observable<Integrator[]>{
    return this.http
    .get(API_URL + '/channel/i/' + index)
    .pipe( map( response => {
      const integrators = response.json();
      return integrators.map( (integrator) => new Integrator(integrator) );
    }))
    .pipe( catchError( err => {
      this.handleError( err );
      return null;
    }));
  }

  public disabledIntegrator(integrator: Integrator): Observable<any>{
    return this.http
      .put(API_URL + '/integrators/disabled/' + integrator.idIntegrator, JSON.stringify(integrator))
      .pipe( tap(
        _ => console.log(integrator.idIntegrator)
      ))
  }

  public enabledIntegrator(integrator: Integrator): Observable<any>{
    return this.http
      .put(API_URL + '/integrators/enabled/' + integrator.idIntegrator, JSON.stringify(integrator))
      .pipe( tap(
        _ => console.log(integrator.idIntegrator)
      ))
  }

  private handleError (error: Response | any) {
    console.error('ApiService::handleError', error);
    return Observable.throw(error);
  }

}