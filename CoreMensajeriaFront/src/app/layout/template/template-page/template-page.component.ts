import { Component, OnInit } from '@angular/core';
import {TemplateService} from '../template.service';

@Component({
  selector: 'app-template-page',
  templateUrl: './template-page.component.html',
  styleUrls: ['./template-page.component.scss']
})
export class TemplatePageComponent implements OnInit {

    template: any = [];

  constructor(private templateService: TemplateService) {
      this.getTemplate();
  }

  ngOnInit() {
  }

  getTemplate(){
      this.templateService.getTemplate(1).subscribe(data => {
          this.template = data;
      });
  }

}
