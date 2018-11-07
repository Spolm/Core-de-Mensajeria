import { Component, OnInit } from '@angular/core';
import { routerTransition } from '../../router.animations';
import { TemplateService } from './template.service';

@Component({
  selector: 'app-template',
  templateUrl: './template.component.html',
  styleUrls: ['./template.component.scss'],
  animations: [routerTransition()]
})

export class TemplateComponent implements OnInit {

  templates: any = [];
  status = false;

  constructor(private templateService: TemplateService) {
    templateService.getTemplates().subscribe(data => {
      this.templates = data;
    });
  }

  ngOnInit() {
  }

  approveTemplate(templateId: number){
    console.log(templateId);
  }

}
