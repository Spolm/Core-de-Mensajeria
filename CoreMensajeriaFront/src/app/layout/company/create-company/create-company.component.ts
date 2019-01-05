import { Component, OnInit } from '@angular/core';
import { Company } from '../../../../model/company-model';
import { CompanyService } from '../company.service';


@Component({
  selector: 'app-create-company',
  templateUrl: './create-company.component.html',
  styleUrls: ['./create-company.component.scss']
})
export class CreateCompanyComponent implements OnInit {

  opcionSeleccionado1: string = "";
  verSeleccion1: string = "";
  opcionSeleccionado2: string = "";
  verSeleccion2: string = "";
  opcionSeleccionado3: string = "";
  verSeleccion3: string = "";
  
  constructor(public companyService: CompanyService) { }

  newCompany: Company = new Company();
  ngOnInit() {
  }

  capturar1() {
    // Pasamos el valor seleccionado a la variable verSeleccion
    this.verSeleccion1 = this.opcionSeleccionado1;
    console.log( "Valor Capturado", this.verSeleccion1 );
}
capturar2() {
  // Pasamos el valor seleccionado a la variable verSeleccion
  this.verSeleccion2 = this.opcionSeleccionado2;
  console.log( "Valor Capturado", this.verSeleccion2 );
}
capturar3() {
  // Pasamos el valor seleccionado a la variable verSeleccion
  this.verSeleccion3 = this.opcionSeleccionado3;
  console.log( "Valor Capturado", this.verSeleccion3 );
}


  addCompany(opcionSeleccionado1 , opcionSeleccionado2 , opcionSeleccionado3 ) {
    this.newCompany._name = opcionSeleccionado1;
    this.newCompany._desc = opcionSeleccionado2; 
    console.log("LLegue aca con los valores de: " + this.newCompany._name + " " + this.newCompany._desc)
    this.companyService.addCompany(this.newCompany).toPromise().then(res => {
      //manejo de la respuesta del servicio
    });
  }
}
