import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-modify-company',
  templateUrl: './modify-company.component.html',
  styleUrls: ['./modify-company.component.scss']
})
export class ModifyCompanyComponent implements OnInit {

  opcionSeleccionado: string = "";
  verSeleccion: string = "";
  datos;
  
  constructor() {

    this.datos = ['Compañias' , 'Campañas', 'Canales'];

  }


  ngOnInit() {

  }

  capturar() {
    // Pasamos el valor seleccionado a la variable verSeleccion
    this.verSeleccion = this.opcionSeleccionado;
    console.log( "Valor Capturado", this.verSeleccion );
}


}
