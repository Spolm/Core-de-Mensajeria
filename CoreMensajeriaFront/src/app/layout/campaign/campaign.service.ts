import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { map, catchError, tap } from 'rxjs/operators';

const endpoint = 'http://localhost:8080/CoreMensajeria_war_exploded/M03_Campaigns/';
const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json'
  })};

@Injectable({
  providedIn: 'root'
})
export class CampaignService {

  constructor(private http: HttpClient) { 
      
   }
   
   private extractData(res: Response) {
    let body = res;
    return body || { };
  }

  getCampaigns(): Observable<any> {
    return this.http.get(endpoint + 'GetCampaigns?id=1'/*+ localStorage.getItem('companyId')*/).pipe(
      map(this.extractData));
  }

  getCampaignsByCompany(): Observable<any> {
    return this.http.get(endpoint + 'GetCampaignsByUser?id='+localStorage.getItem('userid')).pipe(
      map(this.extractData));
  }
  
  
  addCampaign (campaign): Observable<any> {
    return this.http.post<any>(endpoint + 'AddCampaign', JSON.stringify(campaign), httpOptions).pipe(
      tap((campaign) => console.log(`Campaign added w/ ${campaign._name}`)),
    );
  }

  addCompany (company): Observable<any> {
    return this.http.post<any>(endpoint + 'M03_Campaign/AddCampaign', JSON.stringify(company), httpOptions).pipe(
      tap((campaign) => console.log(`Campaign added w/ ${company._name}`)),
    );
  }

  editCampaign (campaign): Observable<any> {
    return this.http.post<any>(endpoint + 'M03_Campaign/AddCampaign', JSON.stringify(campaign), httpOptions).pipe(
      tap((campaign) => console.log(`Campaign added w/ ${campaign._name}`)),
    );
  }

  editCompany (company): Observable<any> {
    return this.http.post<any>(endpoint + 'M03_Campaign/AddCampaign', JSON.stringify(company), httpOptions).pipe(
      tap((campaign) => console.log(`Campaign added w/ ${company._name}`)),
    );
  }

  activateCampaign(_idCampaign: Number){
    return this.http.get(endpoint+'update/'+_idCampaign).subscribe();
  }

}
