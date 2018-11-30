import { Component, OnInit } from '@angular/core';
import { routerTransition } from '../../router.animations';
import { TemplateService } from './template.service';
import { Router } from "@angular/router";

@Component({
  selector: 'app-template',
  templateUrl: './template.component.html',
  styleUrls: ['./template.component.scss'],
  animations: [routerTransition()]
})

export class TemplateComponent{

  templates: any = [];

  constructor(private templateService: TemplateService , private router: Router) {
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

    templateDetails(id: number) {
        this.router.navigate(['template', id]);
    }

}
