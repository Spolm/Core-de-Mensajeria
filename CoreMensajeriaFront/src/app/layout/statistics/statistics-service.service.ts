import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";

@Injectable({
    providedIn: "root"
})
export class StatisticsServiceService {
    private ApiURL: string =
        "http://localhost:8080/CoreMensajeria_war_exploded/M09_Statistics/";

    constructor(private http: HttpClient) {}

    /*getStatisticsData1(){

    getStatisticsData2() {
          return this.http.get(this.ApiURL + 'MessageCompanyLine');
    }

    getStatisticsData3() {
          return this.http.get(this.ApiURL + 'MessageCompanyPie');
    }

  return this.http.get(this.ApiURL + 'MessageCompanyPie') 
}*/

    getStatisticsData4(sparam: string) {
        return this.http.get(this.ApiURL + "PruebaParam" + sparam);
    }
    getStatisticsData5(sparam: string) {
        return this.http.get(this.ApiURL + "PruebaParam2" + sparam);
    }
    getStatisticsData6(sparam: string) {
        return this.http.get(this.ApiURL + "PruebaParam3" + sparam);
    }
}
