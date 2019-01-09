import { Component, OnInit } from '@angular/core';
import { UserAdministrationService } from './user-administration.service';

@Component({
  selector: 'app-user-administration',
  templateUrl: './user-administration.component.html',
  styleUrls: ['./user-administration.component.scss']
})
export class UserAdministrationComponent {

  userId: string = localStorage.getItem("userid");
  users: any = [
    {
      "_idUser": 1,
      "_ciUser": 24685783,
      "_nameUser": "Boris",
      "_lastnameUser": "Torrado",
      "_rolUser": "Aprobador",
      "_emailUser": "boristor22@gmail.com"
    }
  ];
  privilegesJson: any = [];
  companiesJson: any = [
    {
      "_idCompany": 1,
      "_name": "Company 1",
      "_status": true
    },
    {
      "_idCompany": 2,
      "_name": "Company 2",
      "_status": true
    },
    {
      "_idCompany": 3,
      "_name": "Company 3",
      "_status": true
    }
  ];

  constructor(userAdministrationService: UserAdministrationService) {
    console.log(this.companiesJson);
  }

  changeCompany(companyId: string) {
    console.log(companyId);
    localStorage.setItem('companyId', companyId);

  }

}
