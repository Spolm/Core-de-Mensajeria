import { Component, OnInit } from '@angular/core';
import { ApiService } from './api.service';
import { ToastrService } from 'ngx-toastr';
import { NgbProgressbar } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-integrator',
  templateUrl: './integrator.component.html',
  styleUrls: ['./integrator.component.scss']
})
 
export class IntegratorComponent implements OnInit {
  
  integrators: any = [];

  constructor(public rest: ApiService, private toastr: ToastrService) {

    this.GetAllIntegrators();

   }

  ngOnInit() {
    
  }

  GetAllIntegrators() {
    this.integrators = [];
    this.rest.GetAllIntegrators().
    subscribe((data: {}) => {
      this.integrators = data;
      this.toastr.remove(this.toastr.currentlyActive);
      this.toastr.success("Lista Recibida");
    },(err) => {
      this.toastr.error("Error en la Conexión");
    })
  }

  GetSMSIntegrators(){
    this.integrators = [];
    this.rest.GetSMSIntegrators().
    subscribe((data: {}) => {
      this.integrators = data;
      this.toastr.remove(this.toastr.currentlyActive);
      this.toastr.success("Lista Recibida");
    },(err) => {
      this.toastr.error("Error en la Conexión");
    })
  }

  GetMailIntegrators(){
    this.integrators = [];
    this.rest.GetMailIntegrators().
    subscribe((data: {}) => {
      this.integrators = data;
      this.toastr.remove(this.toastr.currentlyActive);
      this.toastr.success("Lista Recibida");
    },(err) => {
      this.toastr.error("Error en la Conexión");
    })
  }

}
