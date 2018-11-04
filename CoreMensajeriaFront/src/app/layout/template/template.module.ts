import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TemplateRoutingModule } from './template-routing.module';
import { TemplateComponent } from './template.component';
import { PageHeaderModule } from './../../shared';

@NgModule({
  imports: [
    CommonModule,
    TemplateRoutingModule,
    PageHeaderModule
  ],
  declarations: [TemplateComponent]
})
export class TemplateModule { }
