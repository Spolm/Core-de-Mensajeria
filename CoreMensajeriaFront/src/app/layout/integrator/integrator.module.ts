import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpModule } from '@angular/http';
import { IntegratorRoutingModule } from './integrator-routing.module';
import { IntegratorComponent } from './integrator.component';
import { PageHeaderModule } from './../../shared';
import { HttpClientModule } from '@angular/common/http';


@NgModule({
  imports: [
    CommonModule,
    IntegratorRoutingModule,
    PageHeaderModule,
    HttpModule,
    HttpClientModule
  ],
  declarations: [IntegratorComponent]
})
export class IntegratorModule { }
