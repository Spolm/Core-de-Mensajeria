import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import {Observable, of, pipe} from 'rxjs';
import { map, catchError, tap } from 'rxjs/operators';
import {p} from '@angular/core/src/render3';

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

  getTemplate(templateId: number){
      return this.http.get(endpoint + 'templates/'+templateId.toString()).pipe(
          map(this.extractData));
  }

  getParameters(companyId:number){
      return this.http.get(endpoint + 'parameters/get?companyId=' + companyId.toString()).pipe(
      map(this.extractData));
  }

  getCompanies(userId: number){
    return this.http.get(endpoint + 'M02_Companies/GetCompanies?id=' + userId).pipe(
      map(this.extractData));
  }

  getChannels(){
    return this.http.get(endpoint + 'channel').pipe(
      map(this.extractData));
  }

  getIntegrators(channel: number){
    return this.http.get(endpoint + 'channel/i/' + channel).pipe(
      map(this.extractData));
  }

  approveTemplate(templateId: Number){
    return this.http.post(endpoint+'templates/update/'+templateId, templateId).subscribe();
  }

  PostParameter(name: string, companyId: number) {
      console.log('POST');
      const body = new URLSearchParams();
      //colocar nombre de parametro
      body.set('name', name);
      //colocar id de compañia
      body.set('companyId', companyId.toString());
      const options = {
          headers: new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded')
      };
      this.http.post(endpoint + 'parameters/add', body.toString(), options).subscribe();
  }
}
