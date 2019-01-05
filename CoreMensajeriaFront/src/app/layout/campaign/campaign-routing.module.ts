import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CampaignComponent } from './campaign.component';
import { CreateCampaignComponent } from './create-campaign/create-campaign.component'
import { ModifyCampaignComponent } from './modify-campaign/modify-campaign.component';

const routes: Routes = [
    { path: '', component: CampaignComponent },
    { path: 'create', component: CreateCampaignComponent },
    { path: 'modify', component: ModifyCampaignComponent }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class CampaignRoutingModule {}