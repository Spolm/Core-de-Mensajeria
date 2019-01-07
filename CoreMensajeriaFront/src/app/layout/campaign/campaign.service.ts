import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { map, catchError, tap } from 'rxjs/operators';

const endpoint = 'http://localhost:8080/CoreMensajeria_war_exploded/M03_Campaigns/';
const httpOptions = {
  headers: new HttpHeaders({
    'Access-Control-Allow-Origin':'*',
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
    return this.http.get(endpoint + 'GetCampaignsByUser/'+localStorage.getItem('userid')).pipe(
      map(this.extractData));
  }

  getCampaignsByCompany(): Observable<any> {
    return this.http.get(endpoint + 'GetCampaignsByUser/'+localStorage.getItem('userid')).pipe(
      map(this.extractData));
  }
  
  
  addCampaign (campaign): Observable<any> {
    console.log(campaign)
    return this.http.post<any>(endpoint + 'AddCampaignP',campaign, httpOptions).pipe(
      tap((campaign) => console.log(`Campaign added w/`)),
    );
  }



  editCampaign (opcamp): Observable<any> {
    console.log(opcamp)
    return this.http.put<any>(endpoint + 'Edit/Campaign', opcamp, httpOptions).pipe(
      tap((campaign) => console.log(`Campaign added w/ ${opcamp._nameCampaign}`)),
    );
  }


  activateCampaign(_idCampaign: Number){
    return this.http.get(endpoint+'update/'+_idCampaign).subscribe();
  }

}
