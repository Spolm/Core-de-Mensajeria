import { Component, NgModule, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CompanyComponent } from './company.component';
import { CompanyRoutingModule } from './company-routing.module';
import { PageHeaderModule } from './../../shared';
import { Company } from '../../../model/company-model';

@NgModule({
  imports: [
    CommonModule,
    CompanyRoutingModule,
    PageHeaderModule
  ],
  declarations: [CompanyComponent]
})
export class CompanyModule implements OnInit {

  private companyList = Array<Company>();
  //private toast: Toast;
  private response: any;
  private empty: boolean;
  selectedCompany: Company;

  constructor() { }
 
  ngOnInit() {
  }

  onSelect(company: Company): void {
    this.selectedCompany = company;
  }
}
