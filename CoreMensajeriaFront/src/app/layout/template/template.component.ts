import { Component, OnInit } from '@angular/core';
import { routerTransition } from '../../router.animations';
import { TemplateService } from './template.service';

@Component({
  selector: 'app-template',
  templateUrl: './template.component.html',
  styleUrls: ['./template.component.scss'],
  animations: [routerTransition()]
})

export class TemplateComponent{

  templates: any = [];

  constructor(private templateService: TemplateService) {
    this.getTemplates();
  }

  getTemplates(){
    this.templateService.getTemplates().subscribe(data => {
      this.templates = data;
    });
  }

  approveTemplates(){
    this.getTemplates();
  }
}
