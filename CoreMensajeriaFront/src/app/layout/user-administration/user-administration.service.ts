import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse, HttpParams } from '@angular/common/http';
import { Observable, of, pipe } from 'rxjs';
import { map, catchError, tap } from 'rxjs/operators';
import { p } from '@angular/core/src/render3';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import { delay } from 'q';

const endpoint = 'http://localhost:8080/CoreMensajeria_war_exploded/';
const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
};

@Injectable({
  providedIn: 'root'
})

export class UserAdministrationService {

  constructor(private http: HttpClient, private router: Router, private toastr: ToastrService) {

  }

  private extractData(res: Response) {
    const body = res;
    return body || {};
  }

  getStates() {
    return this.http.get(endpoint + 'geographical_regions?id=1').pipe(
      map(this.extractData));
  }

  getUsers(companyId: String){
    return this.http.get(endpoint + 'profiles/responsabilities?companyId=' + companyId).pipe(
      map(this.extractData));
  }
  
  getPrivilegesByUserAndCompany(userId: string, companyId: number) {
    return this.http.get(endpoint + 'profiles/privileges?userId=' + userId + '&companyId=' + companyId).pipe(
      map(this.extractData));
  }

  getCompanies(userId: string) {
    return this.http.get(endpoint + 'profiles/companies?userId=' + userId).pipe(
      map(this.extractData));
  }
}
