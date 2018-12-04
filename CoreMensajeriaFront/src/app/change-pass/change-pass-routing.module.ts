import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ChangePassComponent } from './change-pass.component'

const routes: Routes = [
  {
    path: '', 
    component: ChangePassComponent
}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ChangePassRoutingModule { }
