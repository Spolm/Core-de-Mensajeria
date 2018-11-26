import { Component, OnInit } from '@angular/core';

import { IntegratorDataService } from './integrator-data.service';
import { ChannelDataService } from '../channel/channel-data.service';
import { ToastrService } from 'ngx-toastr';
import { Integrator } from './integrator';
import { Channel } from '../channel/channel';


@Component({
  selector: 'app-integrator',
  templateUrl: './integrator.component.html',
  styleUrls: ['./integrator.component.scss'],
  providers: [IntegratorDataService,ChannelDataService]
})
 
export class IntegratorComponent implements OnInit {
  
  integrators: Integrator[] = [];
  channels: Channel[] = [];

  constructor(
    private integratorDataService: IntegratorDataService,
    private channelDataService: ChannelDataService,
    private toastr: ToastrService
  ) { }

  ngOnInit() {

    this.getAllIntegrators();

    this.channelDataService
    .getAllChannels()
    .subscribe(
      (channels) => {
        this.channels = channels;
      },(err => {
        console.log(err);
      })
    );
  
  }

  getIntegratorsPerChannel(index: string){
    this.integratorDataService
    .getIntegratorsPerChannel(index)
    .subscribe(
      (integrators) => {
        this.integrators = integrators;
        this.toastr.success("Lista Recibida");
      },(err => {
        this.toastr.error("Error en la Conexión");
      })
    )
  }

  getAllIntegrators() {
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
