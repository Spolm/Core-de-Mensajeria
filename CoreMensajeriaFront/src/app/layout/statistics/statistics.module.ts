import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { StatisticsComponent } from './statistics.component';
import {StatisticsRoutingModule} from './statistics-routing.module'
import { PlotlyModule } from 'angular-plotly.js';



@NgModule({
  imports: [
    CommonModule, StatisticsRoutingModule, PlotlyModule
  ],
  declarations: [StatisticsComponent]
})
export class StatisticsModule { }
