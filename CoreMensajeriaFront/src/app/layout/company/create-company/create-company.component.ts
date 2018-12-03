import { Component, OnInit } from '@angular/core';
import { Company } from '../../../../model/company-model';
import { CompanyService } from '../company.service';


@Component({
  selector: 'app-create-company',
  templateUrl: './create-company.component.html',
  styleUrls: ['./create-company.component.scss']
})
export class CreateCompanyComponent implements OnInit {
  

  constructor(public rest: CompanyService) { }

  private company: Company;
  ngOnInit() {
    this.addCompany(this.company);
  }



  async addCompany(company:Company){
    try{
      await this.rest.addCompany(company);
    }catch(error){

    }
  }
}
