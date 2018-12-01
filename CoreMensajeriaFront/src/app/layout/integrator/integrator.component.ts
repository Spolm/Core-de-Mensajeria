import { Component, OnInit } from '@angular/core';

import { RestService } from '../../shared/services/rest.service';
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
  viewIdChannel: number = 0;
  constructor(
    private integratorDataService: IntegratorDataService,
    private channelDataService: ChannelDataService,
    private toastr: ToastrService,
    public rest: RestService,
  ) { }

  ngOnInit() {

    this.getAllIntegrators();

    this.getAllChannels();
  
  }

  getIntegratorsPerChannel( index: number ){
    this.integratorDataService
    .getIntegratorsPerChannel( index )
    .subscribe(
      ( integrators ) => {
        this.integrators = integrators;
        this.viewIdChannel = index;
      },( err => {
        console.log(err);
        this.toastr.clear();
        this.toastr.error( "Error en la Conexión" );
      })
    )
  }

  getAllChannels(){
    this.channelDataService
    .getAllChannels()
    .subscribe(
      ( channels ) => {
        this.channels = channels;
      }, err => {
        console.log( err );
      }
    );
  }

  getAllIntegrators() {
    this.integratorDataService
    .getAllIntegrators()
    .subscribe(
      ( integrators ) => {
        this.integrators = integrators;
        this.viewIdChannel = 0;
      }, err => {
        console.log(err);
        this.toastr.clear();
        this.toastr.error( "Error en la Conexión" );
      }
    )
  }

  enabledIntegrator( integrator: Integrator ){
    this.integratorDataService.enabledIntegrator( integrator )
    .subscribe(
      _ => {
        this.updateIntegratorList();
        this.toastr.clear();
        this.toastr.success( "Habilitado con Éxito" );
      }, err => {
        console.log( err );
        this.toastr.clear();
        this.toastr.error( "Error Habilitando" );
      }
    );
  }

  disabledIntegrator( integrator: Integrator ){
    this.integratorDataService.disabledIntegrator( integrator )
    .subscribe(
      _ => {
        this.updateIntegratorList();
        this.toastr.clear();
        this.toastr.success( "Inhabilitado con Éxito" );
      }, err => {
        console.log( err );
        this.toastr.clear();
        this.toastr.error( "Error habilitando" );
      }
    );
  }

  updateIntegratorList(){
    if(this.viewIdChannel > 0)
      this.getIntegratorsPerChannel( this.viewIdChannel );
    else
      this.getAllIntegrators();
  }
  
}
