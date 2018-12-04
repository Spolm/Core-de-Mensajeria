import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LayoutComponent } from './layout.component';

const routes: Routes = [
    {
        path: '',
        component: LayoutComponent,
        children: [
            { path: '', redirectTo: 'blank-page', pathMatch: 'prefix' },
            { path: 'dashboard', loadChildren: './dashboard/dashboard.module#DashboardModule' },
            { path: 'charts', loadChildren: './charts/charts.module#ChartsModule' },
            { path: 'tables', loadChildren: './tables/tables.module#TablesModule' },
            { path: 'forms', loadChildren: './form/form.module#FormModule' },
            { path: 'bs-element', loadChildren: './bs-element/bs-element.module#BsElementModule' },
            { path: 'grid', loadChildren: './grid/grid.module#GridModule' },
            { path: 'components', loadChildren: './bs-component/bs-component.module#BsComponentModule' },
            { path: 'profile', loadChildren: './profile/profile.module#ProfileModule' },
            { path: 'blank-page', loadChildren: './blank-page/blank-page.module#BlankPageModule' },
            { path: 'channel', loadChildren: './channel/channel.module#ChannelModule' },
            { path: 'integrator', loadChildren: './integrator/integrator.module#IntegratorModule' },            
            { path: 'statistics', loadChildren: './statistics/statistics.module#StatisticsModule' },
            { path: 'application', loadChildren: './application/application.module#ApplicationModule' },
            { path: 'template', loadChildren: './template/template.module#TemplateModule' },
            { path: 'company', loadChildren: './company/company.module#CompanyModule' },
            { path: 'campaigns', loadChildren: './campaign/campaign.module#CampaignModule' },
            { path: 'createuser', loadChildren: './createuser/createuser.module#CreateuserModule'},
        ]
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class LayoutRoutingModule {}
