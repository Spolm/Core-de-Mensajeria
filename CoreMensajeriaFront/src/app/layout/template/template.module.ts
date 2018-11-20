import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NgbdModalApprove } from './modals/modal-approve';
import { TemplateRoutingModule } from './template-routing.module';
import { TemplateComponent } from './template.component';
import { PageHeaderModule } from './../../shared';

@NgModule({
  imports: [
    CommonModule,
    TemplateRoutingModule,
    PageHeaderModule
  ],
  declarations: [
    TemplateComponent,
    NgbdModalApprove
  ]
})
export class TemplateModule { }
