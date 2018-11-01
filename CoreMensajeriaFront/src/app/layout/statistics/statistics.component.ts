import { ProfileComponent } from './../profile/profile.component';
import { PlotlyModule, PlotComponent } from 'angular-plotly.js';
import { StatisticsServiceService } from './statistics-service.service';
import { Component, OnInit } from '@angular/core';

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
    data: [                                                               //y:this.json2
                { x: ['SMS', 'Email' , 'Telegrama' , 'Senales de humo '], y: [200, 300, 100 , 50] , type: 'bar' },
    ],
    layout: {width: 500, height: 300, title: 'Cantidad de mensajes enviados por canal'}
};

public graph2 = {
  data: [
              { labels: ['SMS', 'Email'], values: [400, 100], type: 'pie' },
  ],
  layout: {width: 500, height: 300, title: 'Cantidad de mensajes enviados por canal'}
};

  
  constructor(private Servicio : StatisticsServiceService) { }

  ngOnInit() {

    this.Servicio.getStatisticsData().subscribe(data => {
    this.json2 = data 
    console.log("data::" + JSON.stringify(data));
    })
    


  }
  
}
