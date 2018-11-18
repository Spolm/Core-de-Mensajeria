import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProfileRoutingModule } from './profile-routing.module';
import { ProfileComponent } from './profile.component';
import { PageHeaderModule } from './../../shared';
import { HttpModule } from '@angular/http';

@NgModule({
  imports: [CommonModule,ProfileRoutingModule,PageHeaderModule,HttpModule],
  declarations: [ProfileComponent]
})
export class ProfileModule { }
