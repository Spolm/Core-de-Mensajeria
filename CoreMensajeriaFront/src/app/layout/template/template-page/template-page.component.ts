import { Component, OnInit } from '@angular/core';
import { TemplateService } from '../template.service';
import { ActivatedRoute } from '@angular/router';
import { delay } from 'q';

@Component({
  selector: 'app-template-page',
  templateUrl: './template-page.component.html',
  styleUrls: ['./template-page.component.scss']
})
export class TemplatePageComponent implements OnInit {

  userId: string = localStorage.getItem("userid");
  companyId: string = localStorage.getItem("companyId");
  application: any =[];
  privilegesJson: any = [];
  CTEMPLATE = false;
  RTEMPLATE = false;
  UTEMPLATE = false;
  DTEMPLATE = false;
  ATEMPLATE = false;
  status:string;
  id: number;
  message:any=[]
  user:any=[]
  private sub: any;
  template: any = [];
  campaign:any=[];
  planning:any=[];

  constructor(private templateService: TemplateService, private route: ActivatedRoute) {
    this.sub = this.route.params.subscribe(params => {
      this.id = +params['id'];
    });
    this.getTemplate();
    this.getPrivileges(this.userId, Number(this.companyId));
    console.log(this.status);
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

  ngOnInit() {
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }

  getTemplate() {
    this.templateService.getTemplate(this.id).subscribe(data => {
      this.template = data;
      this.status=this.template.status.statusName;
      this.application=this.template.application;
      this.user=this.template.user;
      this.message=this.template.message;
      this.campaign=this.template.campaign;
      this.planning=this.template.planning;
    });
  }

}
