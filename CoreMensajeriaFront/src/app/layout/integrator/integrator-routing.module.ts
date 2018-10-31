import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { IntegratorComponent } from './integrator.component';

const routes: Routes = [
    {
        path: '',
        component: IntegratorComponent
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class IntegratorRoutingModule {}