import { Component, OnInit } from '@angular/core';
import { Company } from '../../../../model/company-model';
import { CompanyService } from '../company.service';


@Component({
  selector: 'app-create-company',
  templateUrl: './create-company.component.html',
  styleUrls: ['./create-company.component.scss']
})
export class CreateCompanyComponent implements OnInit {


  constructor(public companyService: CompanyService) { }

  newCompany: Company = new Company();
  ngOnInit() {
  }



  addCompany() {
    this.companyService.addCompany(this.newCompany).toPromise().then(res => {
      //manejo de la respuesta del servicio
    });
  }
}
