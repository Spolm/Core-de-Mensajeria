import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders, HttpParams } from "@angular/common/http";

@Injectable({
    providedIn: "root"
})
export class StatisticsServiceService {
    private ApiURL: string =
        "http://localhost:8080/CoreMensajeria_war_exploded/M09_Statistics/";

    constructor(private http: HttpClient) {}

    getInitialMessagesForCompanies() {
        return this.http.get(this.ApiURL + "companiesCount");
    }

    getInitialMessagesForCampaigns() {
        return this.http.get(this.ApiURL + "campaignsCount");
    }

    getInitialMessagesForChannels() {
        return this.http.get(this.ApiURL + "channelsCount");
    }

    getAllCompanies(userId: string) {
        let params = new HttpParams();
        params = params.set("userId", userId);
        return this.http.get(this.ApiURL + "companies", { params });
    }

    getAllChannels() {
        return this.http.get(this.ApiURL + "channels");
    }

    getIntegrators(channelIds: number[]) {
        let params = new HttpParams();
        channelIds.forEach(id => {
            params = params.append("channelId", id.toString());
        });
        return this.http.get(this.ApiURL + "integrators", { params });
    }

    getCampaingsForCompany(companyIds: Number[]) {
        var params = new HttpParams();
        companyIds.forEach(id => {
            params = params.append("companyId", id.toString());
        });
        return this.http.get(this.ApiURL + "campaigns", { params });
    }

    getStatistics(params: HttpParams) {
        return this.http.get(this.ApiURL + "filters", { params });
    }
}
