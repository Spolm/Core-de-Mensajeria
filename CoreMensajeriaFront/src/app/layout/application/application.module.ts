import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ApplicationComponent } from './application.component';
import { ApplicationRoutingModule } from './application-routing.module';
import { PageHeaderModule } from './../../shared';
@NgModule({
  imports: [
    CommonModule, 
    ApplicationRoutingModule,
    PageHeaderModule
  ],
  declarations: [ApplicationComponent]
})
export class ApplicationModule { }
