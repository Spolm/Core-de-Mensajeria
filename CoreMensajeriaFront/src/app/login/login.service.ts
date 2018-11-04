import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { map, catchError, tap } from 'rxjs/operators';

const endpoint = 'http://localhost:8080/CoreMensajeria_war_exploded/';
const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json'
  })};

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  

   constructor(private http: HttpClient) { 
      
   }
   
   private extractData(res: Response) {
    let body = res;
    return body || { };
  }

  getUsers(): Observable<any> {
    return this.http.get(endpoint + 'users').pipe(
      map(this.extractData));
  }
  
  logUser (user): Observable<any> {
    return this.http.post<any>(endpoint + 'login', JSON.stringify(user), httpOptions).pipe(
      tap((user) => console.log(`User logged w/ ${user._username}`)),
    );
  }
}
