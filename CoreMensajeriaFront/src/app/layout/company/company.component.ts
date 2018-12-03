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
