import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ChannelRoutingModule } from './channel-routing.module';
import { ChannelComponent } from './channel.component';
import { PageHeaderModule } from './../../shared';

@NgModule({
  imports: [
    CommonModule,
    ChannelRoutingModule,
    PageHeaderModule
  ],
  declarations: [ChannelComponent]
})
export class ChannelModule { }
