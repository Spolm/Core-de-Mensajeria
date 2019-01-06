import { ToastrService } from 'ngx-toastr';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Company } from '../../../../model/company-model';
import { Router } from '@angular/router';
import { CompanyService } from '../../company/company.service';

@Component({
  selector: 'app-modify-campaign',
  templateUrl: './modify-campaign.component.html',
  styleUrls: ['./modify-campaign.component.scss']
})
export class ModifyCampaignComponent implements OnInit {

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
