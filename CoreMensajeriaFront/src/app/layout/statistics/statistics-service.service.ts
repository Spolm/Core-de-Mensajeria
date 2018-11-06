import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class StatisticsServiceService {

  constructor(private http: HttpClient) { }

getStatisticsData1(){

return this.http.get('http://localhost:8080/CoreMensajeria_war/M09_Statistics/MessageCompanyBar')
}

getStatisticsData2(){

 return this.http.get('http://localhost:8080/CoreMensajeria_war/M09_Statistics/MessageCompanyLine') 
}

getStatisticsData3(){

  return this.http.get('http://localhost:8080/CoreMensajeria_war/M09_Statistics/MessageCompanyPie') 
}


}
