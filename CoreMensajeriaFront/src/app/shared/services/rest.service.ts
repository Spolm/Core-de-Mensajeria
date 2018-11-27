import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class RestService {

  endpoint = 'http://localhost:8080/CoreMensajeria_war_exploded/';
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

  getData(relativePath): Observable<any> {
    return this.http.get(this.endpoint + relativePath).pipe(
      map(this.extractData)
    );
  }
  
  postData (relativePath, data): Observable<any> {
    return this.http.post<any>(this.endpoint + relativePath, JSON.stringify(data), this.httpOptions).pipe();
  }
  
  putData (relativePath, data): Observable<any> {
    return this.http.put(this.endpoint + relativePath, JSON.stringify(data), this.httpOptions).pipe();
  }
  
  deleteData (relativePath): Observable<any> {
    return this.http.delete<any>(this.endpoint + relativePath, this.httpOptions).pipe();
  }
}
