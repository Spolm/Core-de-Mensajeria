import { ProfileComponent } from "./../profile/profile.component";
import { PlotlyModule, PlotComponent } from "angular-plotly.js";
import { StatisticsServiceService } from "./statistics-service.service";
import { Component, OnInit } from "@angular/core";
import * as Plotly from "plotly.js/dist/plotly.js";
import { Config, Data, Layout } from "plotly.js/dist/plotly.js";
import { ToastrService } from "ngx-toastr";
import { HttpParams } from "@angular/common/http";
import { MatDialog, MatDialogConfig, MatDialogRef } from "@angular/material";
import { MoreFiltersComponent } from "./more-filters/more-filters.component";

interface myData {
    obj: Object;
}

enum ObjectType {
    company = 1,
    campaign,
    channel
}

@Component({
    selector: "app-statistics",
    templateUrl: "./statistics.component.html",
    styleUrls: ["./statistics.component.scss"]
})
export class StatisticsComponent implements OnInit {
    json2;
    datos;
    opcionSeleccionado: string = "0";
    verSeleccion: string = "";
    opcionDateSleccionado: Date;
    opcionDateSleccionado2: Date;
    verDate: string;
    paramDay1: string;
    paramDay2: string;
    paramMounth1: string;
    paramMounth2: string;
    paramYear1: string;
    paramYear2: string;
    paramType: string;

    companiesDropdown = [];
    companiesDropdownSettings = {};
    selectedCompaniesIds: Number[] = [];
    selectedCompanies = [];

    campaignsDropdown = [];
    campaignsDropdownSettings = {};
    selectedCampaignsIds = [];
    selectedCampaigns = [];

    channelsDropdown = [];
    channelsDropdownSettings = {};
    selectedChannelsIds = [];
    selectedChannels = [];

    public graph = {
        data: [],
        layout: {
            width: 500,
            height: 300,
            title: "Cantidad de mensajes enviados por canal"
        }
    };

    public graph2 = {
        data: [{ x: ["SMS", "Email"], y: [400, 100], type: "pie" }],
        layout: {
            width: 500,
            height: 300,
            title: "Cantidad de mensajes enviados por canal"
        }
    };

    constructor(
        private Servicio: StatisticsServiceService,
        private toastr: ToastrService,
        public dialog: MatDialog
    ) {
        this.datos = ["Compañias", "Campañas", "Canales"];
    }

    ngOnInit() {
        this.Servicio.getAllCompanies().subscribe(data => {
            this.insertIntoDropdown(ObjectType.company, data);
        });

        this.companiesDropdownSettings = {
            singleSelection: false,
            idField: "company_id",
            textField: "company_name",
            selectAllText: "Seleccionar todos",
            unSelectAllText: "Deseleccionar todos",
            itemsShowLimit: 1,
            allowSearchFilter: true
        };

        this.getAllCampaigns();

        this.campaignsDropdownSettings = {
            singleSelection: false,
            idField: "campaign_id",
            textField: "campaign_name",
            selectAllText: "Seleccionar todos",
            unSelectAllText: "Deseleccionar todos",
            itemsShowLimit: 1,
            allowSearchFilter: true
        };

        this.Servicio.getAllChannels().subscribe(data => {
            this.insertIntoDropdown(ObjectType.channel, data);
        });

        this.channelsDropdownSettings = {
            singleSelection: false,
            idField: "channel_id",
            textField: "channel_name",
            selectAllText: "Seleccionar todos",
            unSelectAllText: "Deseleccionar todos",
            itemsShowLimit: 1,
            allowSearchFilter: true
        };

        // this.Servicio.getStatisticsData1().subscribe(data => {
        //  this.json2 = data
        // this.chart(this.json2)
        // console.log("Data1:", this.json2)
        // })
        // this.Servicio.getStatisticsData2().subscribe(data => {
        //   this.json2 = data
        //   this.chart2(this.json2)
        //  console.log("Data2:", this.json2)
        //  })
        // this.Servicio.getStatisticsData3().subscribe(data => {
        //   this.json2 = data
        //    this.chart3(this.json2)
        //    console.log("Data3:", this.json2)
        //  })
    }

    private insertIntoDropdown(objectType: ObjectType, data: Object) {
        switch (objectType) {
            case ObjectType.company:
                for (var index in data) {
                    this.companiesDropdown.push({
                        company_id: data[index]["_idCompany"],
                        company_name: data[index]["_name"]
                    });
                }
                break;
            case ObjectType.campaign:
                this.campaignsDropdown = [];
                for (var index in data) {
                    this.campaignsDropdown.push({
                        campaign_id: data[index]["_idCampaign"],
                        campaign_name: data[index]["_nameCampaign"]
                    });
                }
                break;
            case ObjectType.channel:
                for (var index in data) {
                    this.channelsDropdown.push({
                        channel_id: data[index]["idChannel"],
                        channel_name: data[index]["nameChannel"]
                    });
                }
                break;
        }
    }

    capturar() {
        if (this.opcionSeleccionado != "0") {
            // Pasamos el valor seleccionado a la variable verSeleccion
            this.verSeleccion = this.opcionSeleccionado;
            console.log("Valor Capturado", this.verSeleccion);
        } else this.toastr.error("Debe seleccionar otra opcion");
    }

    capturarDate() {
        if (
            this.opcionDateSleccionado != null &&
            this.opcionDateSleccionado2 != null
        ) {
            this.verDate = this.opcionDateSleccionado.toString();
            this.paramDay1 =
                "?paramDay=" +
                new Date(this.opcionDateSleccionado).getUTCDate();
            this.paramMounth1 =
                "?paramMonth=" +
                new Date(this.opcionDateSleccionado).getUTCMonth();
            this.paramYear1 =
                "?paramYear=" +
                new Date(this.opcionDateSleccionado).getFullYear();
            this.paramDay2 =
                "?paramDay2=" +
                new Date(this.opcionDateSleccionado2).getUTCDate();
            this.paramMounth2 =
                "?paramMonth2=" +
                new Date(this.opcionDateSleccionado2).getUTCMonth();
            this.paramYear2 =
                "?paramYear2=" +
                new Date(this.opcionDateSleccionado2).getFullYear();
            this.paramType = "paramType=" + this.verSeleccion;
            console.log(
                "FechaCapturada",
                new Date(this.opcionDateSleccionado).getUTCDate(),
                new Date(this.opcionDateSleccionado).getUTCMonth(),
                new Date(this.opcionDateSleccionado).getFullYear(),
                new Date(this.opcionDateSleccionado2).getUTCDate(),
                new Date(this.opcionDateSleccionado2).getUTCMonth(),
                new Date(this.opcionDateSleccionado2).getFullYear()
            );
            this.Servicio.getStatisticsData4(
                this.paramDay1 +
                    "&" +
                    this.paramMounth1 +
                    "&" +
                    this.paramYear1 +
                    "&" +
                    this.paramDay2 +
                    "&" +
                    this.paramMounth2 +
                    "&" +
                    this.paramYear2 +
                    "&" +
                    this.paramType
            ).subscribe(data => {
                console.log(data);
            });
        } else this.toastr.error("Debe seleccionar una fecha");
    }

    DoGraficas() {
        /*     this.Servicio.getStatisticsData1().subscribe(data => {
      this.json2 = data
      this.chart(this.json2) 
      console.log("Data1:", this.json2)   
      })*/
        /*
      this.Servicio.getStatisticsData2().subscribe(data => {
        this.json2 = data
        this.chart(this.json2) 
        console.log("Data2:", this.json2)   
        })*/
        /*
        this.Servicio.getStatisticsData3().subscribe(data => {
          this.json2 = data
          this.chart(this.json2) 
          console.log("Data3:", this.json2) 
            })*/

        this.Servicio.getStatisticsData4(
            this.paramDay1 +
                "&" +
                this.paramMounth1 +
                "&" +
                this.paramYear1 +
                "&" +
                this.paramDay2 +
                "&" +
                this.paramMounth2 +
                "&" +
                this.paramYear2 +
                "&" +
                this.paramType
        ).subscribe(data => {
            this.json2 = data;
            this.chart2(this.json2);
            console.log("DataLine:", this.json2);
        });

        this.Servicio.getStatisticsData5(
            this.paramDay1 +
                "&" +
                this.paramMounth1 +
                "&" +
                this.paramYear1 +
                "&" +
                this.paramDay2 +
                "&" +
                this.paramMounth2 +
                "&" +
                this.paramYear2 +
                "&" +
                this.paramType
        ).subscribe(data => {
            this.json2 = data;
            this.chart(this.json2);
            console.log("DataPie:", this.json2);
        });

        this.Servicio.getStatisticsData6(
            this.paramDay1 +
                "&" +
                this.paramMounth1 +
                "&" +
                this.paramYear1 +
                "&" +
                this.paramDay2 +
                "&" +
                this.paramMounth2 +
                "&" +
                this.paramYear2 +
                "&" +
                this.paramType
        ).subscribe(data => {
            this.json2 = data;
            this.chart3(this.json2);
            console.log("DataChart:", this.json2);
        });
    }

    chart3(datos) {
        const graph = [datos];
        const linediv = document.getElementById("pie-chart");
        const layout = {
            width: 500,
            height: 300,
            title: "Cantidad de mensajes enviados por " + this.verSeleccion
        };
        Plotly.newPlot(linediv, graph, layout);
    }

    chart2(datos) {
        const graph = [datos];
        const linediv = document.getElementById("line-chart");
        const layout = {
            width: 500,
            height: 300,
            title: "Cantidad de mensajes enviados por " + this.verSeleccion
        };
        Plotly.newPlot(linediv, graph, layout);
    }

    chart(datos) {
        const graph = [datos];
        const linediv = document.getElementById("bar-chart");
        const layout = {
            width: 500,
            height: 300,
            title: "Cantidad de mensajes enviados por " + this.verSeleccion
        };
        Plotly.newPlot(linediv, graph, layout);
    }

    getAllCampaigns() {
        this.Servicio.getAllCampaigns().subscribe(
            data => {
                this.insertIntoDropdown(ObjectType.campaign, data);
            },
            error => {
                this.toastr.error("Error de conexión");
            }
        );
    }

    // Handle company selecction
    companySelected(company: any) {
        this.selectedCompaniesIds.push(company["company_id"]);
        this.selectedCompanies.push(company);
        this.getCampaignsForCompanies();
    }

    companyDeselected(company: any) {
        this.removeItemFromArray(
            company["company_id"],
            this.selectedCompaniesIds
        );
        this.removeObjectFromArray(
            company["company_id"],
            this.selectedCompanies,
            "company_id"
        );
        if (this.arrayIsEmpty(this.selectedCompaniesIds)) {
            this.getAllCampaigns();
        } else {
            this.getCampaignsForCompanies();
        }
    }

    selectAllCompanies() {
        for (var index in this.companiesDropdown) {
            this.selectedCompaniesIds.push(
                this.companiesDropdown[index]["company_id"]
            );
            this.selectedCompanies.push(this.companiesDropdown[index]);
        }
        this.getAllCampaigns();
    }

    deselectAllCompanies() {
        this.selectedCompaniesIds = [];
        this.selectedCompanies = [];
        this.getAllCampaigns();
    }

    // Handle campaign selecction
    campaignSelected(campaign: any) {
        this.selectedCampaignsIds.push(campaign["campaign_id"]);
        this.selectedCampaigns.push(campaign);
    }

    campaignDeselected(campaign: any) {
        this.removeItemFromArray(
            campaign["campaign_id"],
            this.selectedCampaignsIds
        );
        this.removeObjectFromArray(
            campaign["campaign_id"],
            this.selectedCampaigns,
            "campaign_id"
        );
    }

    selectAllCampaigns() {
        for (var index in this.campaignsDropdown) {
            this.selectedCampaignsIds.push(
                this.campaignsDropdown[index]["campaign_id"]
            );
            this.selectedCampaigns.push(this.campaignsDropdown[index]);
        }
    }

    deselectAllCampaigns() {
        this.selectedCampaignsIds = [];
        this.selectedCampaigns = [];
    }

    // Handle channel selection
    channelSelected(channel: any) {
        this.selectedChannelsIds.push(channel["channel_id"]);
        this.selectedChannels.push(channel);
    }

    channelDeselected(channel: any) {
        this.removeItemFromArray(
            channel["channel_id"],
            this.selectedChannelsIds
        );
        this.removeObjectFromArray(
            channel["channel_id"],
            this.selectedChannels,
            "channel_id"
        );
    }

    selectAllChannels() {
        for (var index in this.channelsDropdown) {
            this.selectedChannelsIds.push(
                this.channelsDropdown[index]["channel_id"]
            );
            this.selectedChannels.push(this.channelsDropdown[index]);
        }
    }

    deselectAllChannels() {
        this.selectedChannelsIds = [];
        this.selectedChannels = [];
    }

    getCampaignsForCompanies() {
        this.Servicio.getCampaingsForCompany(
            this.selectedCompaniesIds
        ).subscribe(data => {
            this.campaignsDropdown = [];
            this.insertIntoDropdown(ObjectType.campaign, data);
        });
    }

    removeItemFromArray(item: Number, array: Number[]) {
        for (var i = 0; i < array.length; i++) {
            if (item == array[i]) {
                array.splice(i, 1);
            }
        }
    }

    removeObjectFromArray<T>(id: any, array: any[], key: T) {
        for (var i = 0; i < array.length; i++) {
            if (id == array[i][key]) {
                array.splice(i, 1);
            }
        }
    }

    arrayIsEmpty(array) {
        return !Array.isArray(array) || !array.length;
    }

    sendUserRequest() {
        var params = this.convertSelectedItemsIntoHttpParams();
        this.Servicio.getStatistics(params).subscribe(
            data => {
                console.log(data);
            },
            error => {
                console.error(error);
            }
        );
    }

    convertSelectedItemsIntoHttpParams(): HttpParams {
        var params = new HttpParams();
        params = this.convertselectedCompaniesIdsIntoHttpParams(params);
        params = this.convertselectedCampaignsIdsIntoHttpParams(params);
        params = this.convertselectedChannelsIdsIntoHttpParams(params);
        return params;
    }

    convertselectedCompaniesIdsIntoHttpParams(params: HttpParams) {
        this.selectedCompaniesIds.forEach(companyId => {
            params = params.append("companyId", companyId.toString());
        });
        return params;
    }

    convertselectedCampaignsIdsIntoHttpParams(params: HttpParams) {
        this.selectedCampaignsIds.forEach(campaignId => {
            params = params.append("campaignId", campaignId.toString());
        });
        return params;
    }

    convertselectedChannelsIdsIntoHttpParams(params: HttpParams) {
        this.selectedChannelsIds.forEach(channelId => {
            params = params.append("channelId", channelId.toString());
        });
        return params;
    }

    openFilters() {
        const dialogConfig = new MatDialogConfig();
        dialogConfig.autoFocus = false;
        dialogConfig.width = "100%";
        dialogConfig.maxWidth = "100%";
        dialogConfig.height = "80%";
        dialogConfig.data = {
            id: 1,
            companiesDropdown: this.companiesDropdown,
            selectedCompanies: this.selectedCompanies,
            selectedCompaniesIds: this.selectedCompaniesIds,
            companiesDropdownSettings: this.companiesDropdownSettings,
            campaignsDropdown: this.campaignsDropdown,
            selectedCampaigns: this.selectedCampaigns,
            selectedCampaignsIds: this.selectedCampaignsIds,
            campaignsDropdownSettings: this.campaignsDropdownSettings,
            channelsDropdown: this.channelsDropdown,
            selectedChannels: this.selectedChannels,
            selectedChannelsIds: this.selectedChannelsIds,
            channelsDropdownSettings: this.channelsDropdownSettings
        };
        const dialogRef = this.dialog.open(MoreFiltersComponent, dialogConfig);
        dialogRef.updatePosition({ top: "55px", right: "0px", left: "0px" });
        dialogRef.afterClosed().subscribe(result => {
            console.log("Dialog was closed");
            console.log(result);
        });
    }
}
