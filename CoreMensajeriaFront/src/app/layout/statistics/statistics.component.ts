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
    selectedCompanies = [];

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
        this.companiesDropdown = [
            { company_id: 1, company_name: "Compañía 1" },
            { company_id: 2, company_name: "Compañía 2" },
            { company_id: 3, company_name: "Compañía 3" },
            { company_id: 3, company_name: "Compañía 4" }
        ];

        this.companiesDropdownSettings = {
            singleSelection: false,
            idField: "company_id",
            textField: "company_name",
            selectAllText: "Seleccionar todos",
            unSelectAllText: "Deseleccionar todos",
            itemsShowLimit: 1,
            allowSearchFilter: true
        };

        this.campaignsDropdown = [
            { campaign_id: 1, campaign_name: "Campaña 1" },
            { campaign_id: 2, campaign_name: "Campaña 2" },
            { campaign_id: 3, campaign_name: "Campaña 3" },
            { campaign_id: 3, campaign_name: "Campaña 4" }
        ];

        this.campaignsDropdownSettings = {
            singleSelection: false,
            idField: "campaign_id",
            textField: "campaign_name",
            selectAllText: "Seleccionar todos",
            unSelectAllText: "Deseleccionar todos",
            itemsShowLimit: 1,
            allowSearchFilter: true
        };

        this.channelsDropdown = [
            { channel_id: 1, channel_name: "SMS" },
            { channel_id: 2, channel_name: "Email" }
        ];

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
                new Date(this.opcionDateSleccionado).getDate() +
                1;
            this.paramMounth1 =
                "?paramMonth=" +
                new Date(this.opcionDateSleccionado).getMonth() +
                1;
            this.paramYear1 =
                "?paramYear=" +
                new Date(this.opcionDateSleccionado).getFullYear();
            this.paramDay2 =
                "?paramDay2=" +
                new Date(this.opcionDateSleccionado2).getDate() +
                1;
            this.paramMounth2 =
                "?paramMonth2=" +
                new Date(this.opcionDateSleccionado2).getMonth() +
                1;
            this.paramYear2 =
                "?paramYear2=" +
                new Date(this.opcionDateSleccionado2).getFullYear();
            this.paramType = "paramType=" + this.verSeleccion;
            console.log(
                "FechaCapturada",
                new Date(this.opcionDateSleccionado).getDate() + 1,
                new Date(this.opcionDateSleccionado).getMonth() + 1,
                new Date(this.opcionDateSleccionado).getFullYear(),
                new Date(this.opcionDateSleccionado2).getDate() + 1,
                new Date(this.opcionDateSleccionado2).getMonth() + 1,
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
    /* Eliminar(id, id2, id3){
  var coso= document.getElementById(id);
  var coso2 =document.getElementById(id2);
  var coso3 = document.getElementById(id3);
   
   if (!coso &&!coso2 &&!coso3)

   {
console.log("ALERTA");
   }
   else
   {
     coso.remove();
     coso2.remove();
     coso3.remove();
       }
   }*/

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
}
