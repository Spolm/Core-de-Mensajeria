import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { IntegratorRoutingModule } from './integrator-routing.module';
import { IntegratorComponent } from './integrator.component';
import { PageHeaderModule } from './../../shared';

@NgModule({
  imports: [
    CommonModule,
    IntegratorRoutingModule,
    PageHeaderModule
  ],
  declarations: [IntegratorComponent]
})
export class IntegratorModule { }
