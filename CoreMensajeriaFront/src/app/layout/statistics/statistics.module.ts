import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { StatisticsComponent } from './statistics.component';
import {StatisticsRoutingModule} from './statistics-routing.module'

@NgModule({
  imports: [
    CommonModule, StatisticsRoutingModule
  ],
  declarations: [StatisticsComponent]
})
export class StatisticsModule { }
