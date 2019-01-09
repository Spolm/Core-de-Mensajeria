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
  companiesJson: any = [];
  CTEMPLATE = false;
  RTEMPLATE = false;
  UTEMPLATE = false;
  DTEMPLATE = false;
  ATEMPLATE = false;

  constructor(private templateService: TemplateService) {
    this.getCompanies(this.userId);
  }

  regetTemplates(){
    this.getTemplates(this.userId, localStorage.getItem('companyId'));
  }

  getTemplates(userId: string, companyId: string) {
    this.templateService.getTemplatesByCompany(userId, companyId).subscribe(data => {
      this.templates = data;
    });
    console.log(this.templates);
  }

  approveTemplates() {
    
    this.getTemplates(this.userId, localStorage.getItem('companyId'));
  }

  getCompanies(userId: string) {
    this.templateService.getCompanies(userId).subscribe(data => {
      this.companiesJson = data;
    });
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
      if (privilege._codePrivileges == 'CTEMPLATE') {
        this.CTEMPLATE = true;
      }
      else if (privilege._codePrivileges == 'RTEMPLATE') {
        this.RTEMPLATE = true
      }
      else if (privilege._codePrivileges == 'UTEMPLATE') {
        this.UTEMPLATE = true
      }
      else if (privilege._codePrivileges == 'DTEMPLATE') {
        this.DTEMPLATE = true
      }
      else if (privilege._codePrivileges == 'ATEMPLATE') {
        this.ATEMPLATE = true
      }
    })
  }

  changeCompany(companyId: string){
    
    localStorage.setItem('companyId', companyId);
    this.getTemplates(this.userId, companyId);
    this.getPrivileges(this.userId, Number(companyId));
  
  }

  templateDetails(id: number) {
    this.templateService.templateDetails(id);
  }

}
