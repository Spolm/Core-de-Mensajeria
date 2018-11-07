import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpModule } from '@angular/http';
import { HttpClientModule } from '@angular/common/http';
import { IntegratorRoutingModule } from './integrator-routing.module';
import { IntegratorComponent } from './integrator.component';
import { PageHeaderModule } from './../../shared';

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
