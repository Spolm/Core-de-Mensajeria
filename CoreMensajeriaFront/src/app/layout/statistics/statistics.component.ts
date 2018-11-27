import { ProfileComponent } from "./../profile/profile.component";
import { PlotlyModule, PlotComponent } from "angular-plotly.js";
import { StatisticsServiceService } from "./statistics-service.service";
import { Component, OnInit } from "@angular/core";
import * as Plotly from "plotly.js/dist/plotly.js";
import { Config, Data, Layout } from "plotly.js/dist/plotly.js";
import { ToastrService } from "ngx-toastr";

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
    selectedCompanies: Number[] = [];

    campaignsDropdown = [];
    campaignsDropdownSettings = {};
    selectedCampaigns = [];

    channelsDropdown = [];
    channelsDropdownSettings = {};
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
        private toastr: ToastrService
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
                console.log(data);
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

    companySelected(company: any) {
        this.selectedCompanies.push(company["company_id"]);
        this.getCampaignsForCompanies();
    }

    companyDeselected(company: any) {
        this.removeItemFromArray(company["company_id"], this.selectedCompanies);
        if (this.arrayIsEmpty(this.selectedCompanies)) {
            this.getAllCampaigns();
        } else {
            this.getCampaignsForCompanies();
        }
    }

    getCampaignsForCompanies() {
        this.Servicio.getCampaingsForCompany(this.selectedCompanies).subscribe(
            data => {
                this.campaignsDropdown = [];
                this.insertIntoDropdown(ObjectType.campaign, data);
            }
        );
    }

    selectAllCompanies() {
        for (var index in this.companiesDropdown) {
            this.selectedCompanies.push(
                this.companiesDropdown[index]["company_id"]
            );
        }
        this.getAllCampaigns();
    }

    deselectAllCampaigns() {
        this.selectedCompanies = [];
        this.getAllCampaigns();
    }

    removeItemFromArray(item: Number, array: Number[]) {
        for (var i = 0; i < array.length; i++) {
            if (item == array[i]) {
                array.splice(i, 1);
            }
        }
    }

    arrayIsEmpty(array) {
        return !Array.isArray(array) || !array.length;
    }
}
