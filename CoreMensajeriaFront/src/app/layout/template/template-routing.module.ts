import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { TemplateComponent } from './template.component';
import { CreateTemplateComponent } from './create-template/create-template.component';
import { TemplatePageComponent } from './template-page/template-page.component';

const routes: Routes = [
  { path: '', component: TemplateComponent },
  { path: 'create', component: CreateTemplateComponent },
  { path: 'info', component: TemplatePageComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TemplateRoutingModule { }
