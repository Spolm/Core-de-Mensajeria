import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CampaignComponent } from './campaign.component';
import { CreateCampaignComponent } from './create-campaign/create-campaign.component'

const routes: Routes = [
    { path: '', component: CampaignComponent },
    { path: 'create', component: CreateCampaignComponent }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class CampaignRoutingModule {}