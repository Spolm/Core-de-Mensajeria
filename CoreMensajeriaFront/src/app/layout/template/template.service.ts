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

export class TemplateService {

  constructor(private http: HttpClient) {
    
  }

  private extractData(res: Response) {
    let body = res;
    return body || { };
  }

  getTemplates(){
    return this.http.get(endpoint + 'templates/messages').pipe(
      map(this.extractData));
  }

  approveTemplate(templateId: Number){
    return this.http.post(endpoint+'templates/update/'+templateId, templateId).subscribe();
  }
  
}
