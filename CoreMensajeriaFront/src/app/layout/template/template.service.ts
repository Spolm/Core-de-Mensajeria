import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse,HttpParams } from '@angular/common/http';
import {Observable, of, pipe} from 'rxjs';
import { map, catchError, tap } from 'rxjs/operators';
import {p} from '@angular/core/src/render3';
import { ToastrService } from 'ngx-toastr';
import { Router } from "@angular/router";

const endpoint = 'http://localhost:8080/CoreMensajeria_war_exploded/';
const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json'
  })};

@Injectable({
  providedIn: 'root'
})

export class TemplateService {

  constructor(private http: HttpClient, private router: Router, private toastr: ToastrService) {

  }

  private extractData(res: Response) {
    let body = res;
    return body || { };
  }

  getTemplates(){
    return this.http.get(endpoint + 'templates?userId=4').pipe(
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

  getCompanies(userId: string){
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
      let userId = localStorage.getItem('userid');
      return this.http.post(endpoint+'templates/update/'+templateId, userId).subscribe();
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

    // @ts-ignore
    postTemplate(formMessage: string, parameters: any[], newParameters: any[], company: number, channel_integrator: any[]){
        let flag: boolean;
        const json = {
            'messagge': formMessage.valueOf(),
            'userId': localStorage.getItem('userid'),
            'company': company,
            'parameters': parameters,
            'newParameters': newParameters,
            'channel_integrator': channel_integrator.valueOf(),
            // se coloca una campaña y origen por defecto
            'applicationId': 2,
            'campaign': 10
        };
        this.http.post(endpoint + 'templates/add', json).subscribe((res: boolean) => {
            flag = res;
            if (flag) {
                    this.toastr.success('Plantilla guardada', 'Exito',
                        {
                            timeOut: 2800,
                            progressBar: true
                        });
                    this.router.navigate(['/template']);
                } else {
                this.toastr.error('No se a podido insertar', 'Error',
                    {
                        timeOut: 2800,
                        progressBar: true
                    });
                }
            },
            error => {
                this.toastr.error('Falla en la conexion', 'Error',
                    {
                        timeOut: 2800,
                        progressBar: true
                    });
            });
        }

    templateDetails(id: number) {
        this.router.navigate(['template', id]);
    }
}
