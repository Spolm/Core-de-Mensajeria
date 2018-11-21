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
    opcionDateSleccionado: Date = null;
    verDate: string;
    paramDate: string;
    paramType: string;

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
        if (this.opcionDateSleccionado != null) {
            this.verDate = this.opcionDateSleccionado.toString();
            this.paramDate = "?paramDate=" + this.verDate;
            this.paramType = "paramType=" + this.verSeleccion;
            console.log("FechaCapturada", this.verDate);
            this.Servicio.getStatisticsData4(
                this.paramDate + "&" + this.paramType
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
            this.paramDate + "&" + this.paramType
        ).subscribe(data => {
            this.json2 = data;
            this.chart2(this.json2);
            console.log("DataLine:", this.json2);
        });

        this.Servicio.getStatisticsData5(
            this.paramDate + "&" + this.paramType
        ).subscribe(data => {
            this.json2 = data;
            this.chart(this.json2);
            console.log("DataPie:", this.json2);
        });

        this.Servicio.getStatisticsData6(
            this.paramDate + "&" + this.paramType
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
}
