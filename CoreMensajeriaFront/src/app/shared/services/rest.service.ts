import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { map, catchError, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class RestService {

  endpoint = 'http://localhost:8080/CoreMensajeria_war/';
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json'
    })
  };

  constructor(private http: HttpClient) { }

  private extractData(res: Response) {
    let body = res;
    return body || { };
  }

  getData(component): Observable<any> {
    return this.http.get(this.endpoint + component).pipe(
      map(this.extractData));
  }
  
  getDataId(component, id): Observable<any> {
    return this.http.get(this.endpoint + component + '/' + id).pipe(
      map(this.extractData));
  }
  
  addData (component, data): Observable<any> {
    console.log(data);
    return this.http.post<any>(this.endpoint + component, JSON.stringify(data), this.httpOptions).pipe(
      catchError(this.handleError<any>('addProduct'))
    );
  }
  
  updateData (component, data, id): Observable<any> {
    return this.http.put(this.endpoint + component + '/' + id, JSON.stringify(data), this.httpOptions).pipe(
      tap(_ => console.log(`updated id=${id}`)),
      catchError(this.handleError<any>('updateData'))
    );
  }
  
  deleteData (component, id): Observable<any> {
    return this.http.delete<any>(this.endpoint + component + '/' + id, this.httpOptions).pipe(
      tap(_ => console.log(`deleted id=${id}`)),
      catchError(this.handleError<any>('deleteData'))
    );
  }
  
  private handleError<T> (operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
  
      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead
  
      // TODO: better job of transforming error for user consumption
      console.log(`${operation} failed: ${error.message}`);
  
      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }
}
