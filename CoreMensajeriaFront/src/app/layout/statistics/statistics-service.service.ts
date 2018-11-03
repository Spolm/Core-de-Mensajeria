import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class StatisticsServiceService {

  constructor(private http: HttpClient) { }

getStatisticsData(){

return this.http.get('http://localhost:8080/ApiPrueba2_war_exploded/api/Servicios/numero')

}


}
