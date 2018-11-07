import { ProfileComponent } from './../profile/profile.component';
import { PlotlyModule, PlotComponent } from 'angular-plotly.js';
import { StatisticsServiceService } from './statistics-service.service';
import { Component, OnInit } from '@angular/core';
import * as Plotly from 'plotly.js/dist/plotly.js';
import{Config, Data, Layout} from 'plotly.js/dist/plotly.js';

interface myData{
  obj: Object;
}

@Component({
  selector: 'app-statistics',
  templateUrl: './statistics.component.html',
  styleUrls: ['./statistics.component.scss']
})
export class StatisticsComponent implements OnInit {
 json2;

 
 public graph = {
    
  data : [

 ],
layout: {width: 500, height: 300, title: 'Cantidad de mensajes enviados por canal'}

};


public graph2 = {
  data: [
              { x: ['SMS', 'Email'], y: [400, 100], type: 'pie' },
  ],
  layout: {width: 500, height: 300, title: 'Cantidad de mensajes enviados por canal'}
};

  
  constructor(private Servicio : StatisticsServiceService) { }

  ngOnInit() {

    this.Servicio.getStatisticsData1().subscribe(data => {
     this.json2 = data
    this.chart(this.json2) 
    console.log("Data1:", this.json2)   
    })

    this.Servicio.getStatisticsData2().subscribe(data => {
      this.json2 = data
      this.chart2(this.json2)
      console.log("Data2:", this.json2)
    })

    this.Servicio.getStatisticsData3().subscribe(data => {
      this.json2 = data
      this.chart3(this.json2)
      console.log("Data3:", this.json2)
    })
    
  }

  chart3(datos){
    const graph= [datos];
     const linediv = document.getElementById("pie-chart");
    const  layout = {width: 500, height: 300, title: 'Cantidad de mensajes enviados por canal'}
    Plotly.plot(linediv,graph,layout);
  }

  chart2(datos){
    const graph= [datos];
     const linediv = document.getElementById("line-chart");
    const  layout = {width: 500, height: 300, title: 'Cantidad de mensajes enviados por canal'}
    Plotly.plot(linediv,graph,layout);
  }

  chart(datos){
    const graph= [datos];
     const linediv = document.getElementById("bar-chart");
    const  layout = {width: 500, height: 300, title: 'Cantidad de mensajes enviados por canal'}
    Plotly.plot(linediv,graph,layout);
   }

}