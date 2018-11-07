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

  constructor(private templateService: TemplateService, private toastr: ToastrService) {
    templateService.getTemplates().subscribe(data => {
      this.templates = data;
    });
  }

  ngOnInit() {
  }

  approveTemplate(templateId: number){
    this.toastr.info("Para confirmar realice click de nuevo", "Aprobar la plantilla id: "+templateId,
    {
      timeOut: 5000,
      progressBar: true,
      positionClass: 'toast-top-left'
    });
    //HTTP POST
  }

}
