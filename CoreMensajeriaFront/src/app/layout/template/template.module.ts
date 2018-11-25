import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NgbdModalApprove } from './modals/modal-approve';
import { TemplateRoutingModule } from './template-routing.module';
import { TemplateComponent } from './template.component';
import { CreateTemplateComponent } from './create-template/create-template.component';
import { PageHeaderModule } from './../../shared';
import { FormsModule } from '@angular/forms';


@NgModule({
  imports: [
    CommonModule,
    TemplateRoutingModule,
    PageHeaderModule,
    FormsModule
  ],
  declarations: [
    TemplateComponent,
    NgbdModalApprove,
    CreateTemplateComponent
  ]
})
export class TemplateModule { }
