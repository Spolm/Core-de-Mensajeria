import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CampaignRoutingModule } from './campaign-routing.module';
import { CampaignComponent } from './campaign.component';
import { PageHeaderModule } from './../../shared';

@NgModule({
  imports: [
    CommonModule,
    CampaignRoutingModule,
    PageHeaderModule
  ],
  declarations: [CampaignComponent]
})
export class CampaignModule { }
