import { Component, OnInit } from '@angular/core';
import { Company } from '../../../../model/company-model';
import { CompanyService } from '../company.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-create-company',
  templateUrl: './create-company.component.html',
  styleUrls: ['./create-company.component.scss']
})
export class CreateCompanyComponent implements OnInit {

  opcionSeleccionado: string = "0"; 
  opcionSeleccionado2: string = "0";

  constructor(public companyService: CompanyService , private toastr: ToastrService) { }

  newCompany: Company = new Company();
  ngOnInit() { 
  }

  capturar(){
    if ( (this.newCompany._name != null ) && ( this.newCompany._desc != null )){

  } else this.toastr.error("Algun Campo esta Vacio.");
  }

  addCompany() {
    if ( (this.newCompany._name != null ) && ( this.newCompany._desc != null )) {

    this.companyService.addCompany(this.newCompany).toPromise().then(res => {
      //manejo de la respuesta del servicio
    });

  }
  else {this.toastr.error("Algun Campo esta Vacio.");
      this.newCompany._name = null;
      this.newCompany._desc = null;
    }
  }

}
