import { Component, NgModule, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CampaignRoutingModule } from './campaign-routing.module';
import { CampaignComponent } from './campaign.component';
import { PageHeaderModule } from './../../shared';
import { Campaign } from '../../../model/campaign-model';

@NgModule({
  imports: [
    CommonModule,
    CampaignRoutingModule,
    PageHeaderModule
  ],
  declarations: [CampaignComponent]
})
export class CampaignModule implements OnInit {  
  
  private campaignList = Array<Campaign>();
  //private toast: Toast;
  private response: any;
  private empty: boolean;
  selectedCampaign: Campaign;

  constructor() { }
 
  ngOnInit() {
  }

  onSelect(campaign: Campaign): void {
    this.selectedCampaign = campaign;
  }
}
