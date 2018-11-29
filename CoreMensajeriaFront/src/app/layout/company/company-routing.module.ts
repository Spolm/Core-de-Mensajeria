import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CompanyComponent } from './company.component';
import { CreateCompanyComponent } from './create-company/create-company.component'

const routes: Routes = [
    { path: '', component: CompanyComponent },
    { path: 'create', component: CreateCompanyComponent }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class CompanyRoutingModule {}