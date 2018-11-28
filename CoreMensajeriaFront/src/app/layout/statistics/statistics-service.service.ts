import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders, HttpParams } from "@angular/common/http";

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
    /* Companias */
    getDataLineChartCompany(sparam: string) {
        return this.http.get(this.ApiURL + "PruebaParam" + sparam);
    }
    getDataBarChartCompany(sparam: string) {
        return this.http.get(this.ApiURL + "PruebaParam2" + sparam);
    }
    getDataPieChartCompany(sparam: string) {
        return this.http.get(this.ApiURL + "PruebaParam3" + sparam);
    }
  /*  Campanas  */
    getDataLineChartCampaign(sparam: string) {
        return this.http.get(this.ApiURL + "PruebaParam" + sparam);
    }
    getDataBarChartCampaign(sparam: string) {
        return this.http.get(this.ApiURL + "PruebaParam2" + sparam);
    }
    getDataPieChartCampaign(sparam: string) {
        return this.http.get(this.ApiURL + "PruebaParam3" + sparam);
    }
 /* Canales   */
    getDataLineChartChannels(sparam: string) {
        return this.http.get(this.ApiURL + "PruebaParam" + sparam);
    }
    getDataBarChartChannels(sparam: string) {
        return this.http.get(this.ApiURL + "PruebaParam2" + sparam);
    }
    getDataPieChartChannels(sparam: string) {
        return this.http.get(this.ApiURL + "PruebaParam3" + sparam);
    }

    getAllCompanies() {
        return this.http.get(this.ApiURL + "companies");
    }

    getAllCampaigns() {
        return this.http.get(this.ApiURL + "campaigns");
    }

    getAllChannels() {
        return this.http.get(this.ApiURL + "channels");
    }

    getCampaingsForCompany(companyIds: Number[]) {
        var params = new HttpParams();
        companyIds.forEach(id => {
            params = params.append("companyId", id.toString());
        });
        return this.http.get(this.ApiURL + "campaignCompany", { params });
    }

    getStatistics(params: HttpParams) {
        return this.http.get(this.ApiURL + "data", { params });
    }
}
