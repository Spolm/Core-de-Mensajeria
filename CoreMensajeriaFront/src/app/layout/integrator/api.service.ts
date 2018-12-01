import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
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
    private http: Http,
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
      return err;
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
      return err;
    }));
  }

  public disabledIntegrator(integrator: Integrator): Observable<any>{
    return this.http
      .put(API_URL + '/integrators/disabled/' + integrator.idIntegrator, JSON.stringify(integrator))
      .pipe(
        catchError( err => {
          this.handleError( err );
          return err;
        })
      );
  }

  public enabledIntegrator(integrator: Integrator): Observable<any>{
    return this.http
      .put(API_URL + '/integrators/enabled/' + integrator.idIntegrator, JSON.stringify(integrator))
      .pipe(
        catchError( err => {
          this.handleError( err );
          return err;
        })
      );
  }

  private handleError (error: any) {
    console.log('ApiService: handleError', error);
  }

}