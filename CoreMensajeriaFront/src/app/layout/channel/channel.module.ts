import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ChannelRoutingModule } from './channel-routing.module';
import { ChannelComponent } from './channel.component';
import { PageHeaderModule } from './../../shared';
import { HttpClientModule  } from '@angular/common/http';


@NgModule({
  imports: [
    CommonModule,
    ChannelRoutingModule,
    PageHeaderModule,
    HttpClientModule
  ],
  declarations: [ChannelComponent]
})
export class ChannelModule { }
