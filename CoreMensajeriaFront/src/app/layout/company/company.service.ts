import { Company } from './../../../model/company-model';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { map, catchError, tap } from 'rxjs/operators';

const endpoint = 'http://localhost:8080/CoreMensajeria_war_exploded/M02_Companies/';
const httpOptions = {
  headers: new HttpHeaders({
    'Access-Control-Allow-Origin':'*',
    'Content-Type':  'application/json'
  })};

@Injectable({
  providedIn: 'root'
})
export class CompanyService {



  constructor(private http: HttpClient) { 
      
   }
   
   private extractData(res: Response) {
    let body = res;
    return body || { };
  }

  getCompanies(): Observable<any> {
   
      return this.http.get(endpoint + 'GetCompaniesByUserPP/'+localStorage.getItem('userid')).pipe(
        map(this.extractData));
      
  }

  getCompaniesByUser(): Observable<any> {
    return this.http.get(endpoint + 'GetCompaniesByUser?id='+localStorage.getItem('userid')).pipe(
      map(this.extractData));
      
  }

  
  /*addCompany (company): Promise<any> {
    return this.http.post<any>(endpoint + 'M02_Company/AddCompany', company).toPromise()
      .then(res =>{
        return res
      })
  }*/

  addCompany (company): Observable<any> {
    company._idUser = localStorage.getItem('userid')
    console.log(company)
    return this.http.post<any>(endpoint + 'AddCompanyPP', company, httpOptions).pipe(
      tap((company) => console.log('company added w/ ${company._name}')),
    );
  }
  editCompany (opcionSeleccionado): Observable<any> {
    opcionSeleccionado._idUser = localStorage.getItem('userid')
    console.log(opcionSeleccionado)
    return this.http.put<any>(endpoint + 'Edit/CompanyPP', opcionSeleccionado, httpOptions).pipe(
      tap((company) => console.log('company added w/ ${company._name}')),
    );
  }


  activateCompany (company): Observable<any> {
    return this.http.post<any>(endpoint + 'updateCompanyStatus', company, httpOptions).pipe(
      tap((company) => console.log('company added w/ ${company._name}')),
    );
  }
}
