import { Component, OnInit } from '@angular/core';
import { UserAdministrationService } from './user-administration.service';
import { delay } from 'q';

@Component({
  selector: 'app-user-administration',
  templateUrl: './user-administration.component.html',
  styleUrls: ['./user-administration.component.scss']
})
export class UserAdministrationComponent {

  userId: string = localStorage.getItem("userid");
  usersJson: any = [];
  privilegesJson: any = [];
  companiesJson: any = [];
  CUSER = false;
  RUSER = false;
  UUSER = false;
  DUSER = false;

  constructor( private userAdministrationService: UserAdministrationService ) {
    this.getCompanies(this.userId);
  }

  changeCompany(companyId: string) {
    localStorage.setItem('companyId', companyId);
    this.getUsers(localStorage.getItem('companyId'));    
    this.getPrivileges(this.userId, Number(companyId));
  }

  getUsers(companyId: string){
    this.userAdministrationService.getUsers(companyId).subscribe(data => {
      this.usersJson = data;
    });
  }

  async getPrivileges(userId: string, companyId: number) {
    this.userAdministrationService.getPrivilegesByUserAndCompany(userId, companyId).subscribe(data => {
      this.privilegesJson = data;
      console.log(this.privilegesJson)
    });
    await delay(1000);
    this.assignPrivileges(this.privilegesJson);
  }

  assignPrivileges(privileges: Array<any>) {
    privileges.forEach((privilege) => {
      if (privilege._codePrivileges == 'CUSER') {
        this.CUSER = true;
      }
      else if (privilege._codePrivileges == 'RUSER') {
        this.RUSER = true
      }
      else if (privilege._codePrivileges == 'UUSER') {
        this.UUSER = true
      }
      else if (privilege._codePrivileges == 'DUSER') {
        this.DUSER = true
      }
    })
  }

  getCompanies(userId: string) {
    this.userAdministrationService.getCompanies(userId).subscribe(data => {
      this.companiesJson = data;
    });
  }

}
