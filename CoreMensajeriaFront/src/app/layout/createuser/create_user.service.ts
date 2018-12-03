import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import {Users} from "./models/users";
import { map, catchError, tap } from 'rxjs/operators';

const httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json'
    })};

@Injectable()
export class CreateUser {

  constructor(private http: HttpClient) { }
  baseUrl: string = 'https://jsonplaceholder.typicode.com/users';

  createUser (user): Observable<any> {
    return this.http.post<any>('http://localhost:8080/CoreMensajeria_war_exploded/profile/add', JSON.stringify(user), httpOptions).pipe(
    tap((user) => console.log(`User creates`)),
    );
  }
  getUsers() {
    return this.http.get<Users[]>(this.baseUrl);
  }

}