import { Component, OnInit } from '@angular/core';
import { ApiService } from './api.service';

import { IntegratorDataService } from './integrator-data.service'
import { ToastrService } from 'ngx-toastr';
import { Integrator } from './integrator'


@Component({
  selector: 'app-integrator',
  templateUrl: './integrator.component.html',
  styleUrls: ['./integrator.component.scss'],
  providers: [IntegratorDataService]
})
 
export class IntegratorComponent implements OnInit {
  
  integrators: Integrator[] = [];

  constructor(
    private integratorDataService: IntegratorDataService,
    private toastr: ToastrService
  ) { }

  ngOnInit() {
    this.integratorDataService
    .getAllIntegrators()
    .subscribe(
      (integrators) => {
        this.integrators = integrators;
        this.toastr.success("Lista Recibida");
      },(err => {
        this.toastr.error("Error en la Conexión");
      })
    )
  }

  
  /*

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
  */
}
