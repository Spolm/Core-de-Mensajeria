import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class StatisticsServiceService {

  private ApiURL : string = 'http://localhost:8080/CoreMensajeria_war_exploded/M09_Statistics/'

  constructor(private http: HttpClient) { }


  //idea: lograr enviar los datos captados y en el back adaptar los metodos con los pathparam
  // de modo tal que cuando apriete consultar ejecute 3 metodos( uno por cada grafica) que logre ajustarse
  //a los parametros

getStatisticsData1(){

return this.http.get(this.ApiURL + 'MessageCompanyBar')
}

getStatisticsData2(){

 return this.http.get(this.ApiURL + 'MessageCompanyLine') 
}

getStatisticsData3(){

  return this.http.get(this.ApiURL + 'MessageCompanyPie') 
}


}