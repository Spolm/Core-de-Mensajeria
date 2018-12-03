import { Component, OnInit } from '@angular/core';
import { TemplateService } from '../template.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-template-page',
  templateUrl: './template-page.component.html',
  styleUrls: ['./template-page.component.scss']
})
export class TemplatePageComponent implements OnInit {

  id: number;
  private sub: any;
  template: any = [];

  constructor(private templateService: TemplateService, private route: ActivatedRoute) {
    this.sub = this.route.params.subscribe(params => {
      this.id = +params['id'];
    });
    this.getTemplate();
  }

  ngOnInit() {
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }

  getTemplate() {
    this.templateService.getTemplate(this.id).subscribe(data => {
      this.template = data;
    });
  }

}
