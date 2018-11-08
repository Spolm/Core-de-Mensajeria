import { Component, OnInit } from '@angular/core';
import { ApiService } from './api.service';

import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-integrator',
  templateUrl: './integrator.component.html',
  styleUrls: ['./integrator.component.scss']
})
 
export class IntegratorComponent implements OnInit {

  integrators: any = [];

  constructor(public rest: ApiService, private toastr: ToastrService) {

    this.getAllIntegrators();

   }

  ngOnInit() {
    
  }

  getAllIntegrators() {
    this.integrators = [];
    this.rest.getAllIntegrators().
    subscribe((data: {}) => {
      this.integrators = data;
      this.toastr.remove(this.toastr.currentlyActive);
      this.toastr.success("Lista Recibida");
      console.log(data);
    },(err) => {
      this.toastr.error("Error en la Conexión");
    })
  }

  getSMSIntegrators(){
    this.integrators = [];
    this.rest.getSMSIntegrators().
    subscribe((data: {}) => {
      this.integrators = data;
      this.toastr.remove(this.toastr.currentlyActive);
      this.toastr.success("Lista Recibida");
    },(err) => {
      this.toastr.error("Error en la Conexión");
    })
  }

  getMailIntegrators(){
    this.integrators = [];
    this.rest.getMailIntegrators().
    subscribe((data: {}) => {
      this.integrators = data;
      this.toastr.remove(this.toastr.currentlyActive);
      this.toastr.success("Lista Recibida");
    },(err) => {
      this.toastr.error("Error en la Conexión");
    })
  }
}
