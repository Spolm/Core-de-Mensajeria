import { Component, OnInit } from '@angular/core';
import { routerTransition } from '../../router.animations';
import { TemplateService } from './template.service';
import { delay } from 'q';

@Component({
  selector: 'app-template',
  templateUrl: './template.component.html',
  styleUrls: ['./template.component.scss'],
  animations: [routerTransition()]
})

export class TemplateComponent {

  userId: string = localStorage.getItem("userid");
  templates: any = [];
  privilegesJson: any = [];
  CTEMPLATE = false;
  RTEMPLATE = false;
  UTEMPLATE = false;
  DTEMPLATE = false;
  ATEMPLATE = false;

  constructor(private templateService: TemplateService) {
    this.getTemplates();
    this.getPrivileges(this.userId, 2);
  }

  getTemplates() {
    this.templateService.getTemplates().subscribe(data => {
      this.templates = data;
    });
  }

  approveTemplates() {
    this.getTemplates();
  }

  async getPrivileges(userId: string, companyId: number) {
    this.templateService.getPrivilegesByUserAndCompany(userId, companyId).subscribe(data => {
      this.privilegesJson = data;
    });
    await delay(1000);
    this.assignPrivileges(this.privilegesJson);
  }

  assignPrivileges(privileges: Array<any>) {
    privileges.forEach((privilege) => {
      if(privilege._codePrivileges == 'CTEMPLATE'){
        this.CTEMPLATE = true;
      }
      else if(privilege._codePrivileges == 'RTEMPLATE'){
        this.RTEMPLATE = true
      }
      else if(privilege._codePrivileges == 'UTEMPLATE'){
        this.UTEMPLATE = true
      }
      else if(privilege._codePrivileges == 'DTEMPLATE'){
        this.DTEMPLATE = true
      }
      else if(privilege._codePrivileges == 'ATEMPLATE'){
        this.ATEMPLATE = true
      }
    })
  }

  templateDetails(id: number) {
    this.templateService.templateDetails(id);
  }

}
