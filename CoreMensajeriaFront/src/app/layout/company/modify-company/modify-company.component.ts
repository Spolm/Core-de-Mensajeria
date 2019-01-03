import { ToastrService } from 'ngx-toastr';
import { CompanyService } from './../company.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { Company } from '../../../../model/company-model';


@Component({
  selector: 'app-modify-company',
  templateUrl: './modify-company.component.html',
  styleUrls: ['./modify-company.component.scss']
})
export class ModifyCompanyComponent implements OnInit {

  opcionSeleccionado: string = "";
  verSeleccion: string = "";
  datos;
  
  constructor(public router: Router, private http: HttpClient, 
    public rest: CompanyService, private toastr: ToastrService) {

    //this.datos = ['Compañias' , 'Campañas', 'Canales'];

  }

   private companyList = Array<Company>();
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

  capturar() {
    // Pasamos el valor seleccionado a la variable verSeleccion
    this.verSeleccion = this.opcionSeleccionado;
    console.log( "Valor Capturado", this.verSeleccion );
}


}
