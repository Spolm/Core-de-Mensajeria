import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { routerTransition } from '../../router.animations';
import { HttpClient } from '@angular/common/http';
import { CompanyService } from './company.service';
import { ToastrService } from 'ngx-toastr';
import { Company } from '../../../model/company-model';


@Component({
  selector: 'app-company',
  templateUrl: './company.component.html',
  styleUrls: ['./company.component.scss'],
  animations: [routerTransition()]
})
export class CompanyComponent implements OnInit {

  constructor(public router: Router, private http: HttpClient, 
              public rest: CompanyService, private toastr: ToastrService) { }

  private companyList = Array<Company>();
  private company: Company;
  private vacio: boolean;
  private counter: number = 0;
  private lastCompanyId: number;

  ngOnInit() {

    this.rest.getCompanies().subscribe((data) => {
      this.vacio = true;
      this.companyList = data;
      if (this.companyList.length > 0) {
        this.vacio = false;
      }
    }, (err) => {
   console.log(err);
    })

    
  }

  
  activateCompany(_idCompany: number , _statusCompany : boolean , Company : Company){
    this.toastr.info("Para confirmar realice doble click de nuevo", "Activar la compañia id: "+ _idCompany,
    {
      timeOut: 2800,
      progressBar: true
    });
    this.counter++;
    if(this.counter == 2 && this.lastCompanyId == _idCompany){
      this.rest.activateCompany(_idCompany , _statusCompany , Company);
      console.log("ACT " +Company);
      this.toastr.success("Compañia activada", "Company id: "+ _idCompany,
      {
        timeOut: 2800,
        progressBar: true
      });
      this.counter = 0;
      this.ngOnInit();
    }
    if(this.counter >= 2) this.counter = 0;
    this.lastCompanyId = _idCompany;
  }
  
  deactivateCompany(_idCompany: number , _statusCompany : boolean , Company : Company){
    this.toastr.info("Para confirmar realice doble click de nuevo", "Desactivar la compañia id: "+ _idCompany,
    {
      timeOut: 2800,
      progressBar: true
    });
    this.counter++;
    if(this.counter == 2 && this.lastCompanyId == _idCompany){
      console.log("Des " +Company);
      this.rest.activateCompany(_idCompany , _statusCompany , Company);
      this.toastr.success("Compañia desactivada", "Company id: "+ _idCompany,
      {
        timeOut: 2800,
        progressBar: true
      });
      this.counter = 0;
      this.ngOnInit();
    }
    if(this.counter >= 2) this.counter = 0;
    this.lastCompanyId = _idCompany;
  }


  /*getCompanies() {
    this.toastr.info("Espere un momento",'Intentando acceder',{
      progressBar: true
    });
    this.rest.getCompanies().subscribe((result) => {
       }, (err) => {
      console.log(err);
      if (err.status == 0) this.toastr.error('Problema de conexión');
      else this.toastr.error(err.error._error);
    });
  }*/

  /*handleLogin() {
    if (this.userData._username.length > 0 && this.userData._password.length > 0)
      this.login();
    else
      this.toastr.error('Debes ingresar las credenciales');
  }*/

}
