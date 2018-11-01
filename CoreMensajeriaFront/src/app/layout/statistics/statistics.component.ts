import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-statistics',
  templateUrl: './statistics.component.html',
  styleUrls: ['./statistics.component.scss']
})
export class StatisticsComponent implements OnInit {
 
 
  public graph = {
    data: [
                { x: ['SMS', 'Email'], y: [400, 100], type: 'bar' },
    ],
    layout: {width: 500, height: 300, title: 'Cantidad de mensajes enviados por canal'}
};
  
  constructor() { }

  ngOnInit() {
  }
  
}
