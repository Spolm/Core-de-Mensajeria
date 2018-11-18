import { Component, OnInit } from '@angular/core';
import { routerTransition } from '../../router.animations';
import { TemplateService } from './template.service';
import { ToastrService } from 'ngx-toastr';
import { timeout } from 'q';

@Component({
  selector: 'app-template',
  templateUrl: './template.component.html',
  styleUrls: ['./template.component.scss'],
  animations: [routerTransition()]
})

export class TemplateComponent implements OnInit {

  templates: any = [];
  status = false;
  counter: number = 0;
  lastTemplateId: number;

  constructor(private templateService: TemplateService, private toastr: ToastrService) {
    templateService.getTemplates().subscribe(data => {
      this.templates = data;
    });
  }

  ngOnInit() {
    this.templateService.getTemplates().subscribe(data => {
      this.templates = data;
    });
    console.log("Cargue de nuevo");
  }

  approveTemplate(templateId: number){
    this.toastr.info("Para confirmar realice doble click de nuevo", "Aprobar la plantilla id: "+templateId,
    {
      timeOut: 2800,
      progressBar: true
    });
    this.counter++;
    if(this.counter == 2 && this.lastTemplateId == templateId){
      this.templateService.approveTemplate(templateId);
      this.toastr.success("Aprobada", "Plantilla id: "+templateId,
      {
        timeOut: 2800,
        progressBar: true
      });
      this.counter = 0;
      this.ngOnInit();
    }
    if(this.counter >= 2) this.counter = 0;
    this.lastTemplateId = templateId;
  }

}
