import { Component, OnInit, ElementRef, ViewChild } from '@angular/core';
import { TemplateService } from '../template.service';
import { ToastrService } from 'ngx-toastr';
import {Router} from '@angular/router';

@Component({
  selector: 'app-create-template',
  templateUrl: './create-template.component.html',
  styleUrls: ['./create-template.component.scss']
})


export class CreateTemplateComponent {

  parametersJson: any = [];
  channelsJson: any = [];
  formMessage: string;
  parameters: Array<string> = [];
  newParameters: Array<string> = [];
  channels_integrators: any = [];
  showInputCreateParameterState = false;

  constructor(private templateService: TemplateService, private toastr: ToastrService, private router: Router) {
    this.getParameters();
    this.getChannels();
  }

  getParameters() {
    this.templateService.getParameters(1).subscribe(data => {
      this.parametersJson = data;
    });
  }

  getChannels() {
    this.templateService.getChannels().subscribe(data => {
      this.channelsJson = data;
    });
  }

  addParameter(message: string, parameterName: string) {
      parameterName = parameterName.trim();
      if ((parameterName.valueOf() !== '')) {
          parameterName = parameterName.toLowerCase();
          parameterName = parameterName.charAt(0).toUpperCase() + parameterName.slice(1, parameterName.length);
          if (!this.parameters.find(x => x === parameterName)) {
              const myFormMessage = document.getElementById('formMessage');
              const pointer = (myFormMessage as HTMLTextAreaElement).selectionStart;
              const startMessage = message.slice(0, pointer);
              const endMessage = message.slice(pointer, message.length);
              this.parameters.push(parameterName);
              this.formMessage = startMessage + ' [.$' + parameterName + '$.] ' + endMessage;
          } else {
              this.toastr.warning('El parametro ya esta registrado', 'Aviso',
                  {
                      timeOut: 2800,
                      progressBar: true
                  });
          }
      } else {
          this.toastr.warning('No a escrito ningun parametro', 'Aviso',
              {
                  timeOut: 2800,
                  progressBar: true
              });
      }
  }

  addNewParameter(message: string, parameterName: string) {
    console.log(parameterName);
    this.addParameter(message, parameterName);
    if (!this.newParameters.find(x => x == parameterName)) {
      this.newParameters.push(parameterName);
    }
    this.showInputCreateParameterState = false;
  }

  addIntegrator(channel: any, integratorId: number) {
    if (!this.channels_integrators.find(x => x.channel.idChannel == channel.idChannel)) {
      if (!this.channels_integrators.find(x => x.integrator.idIntegrator == integratorId)) {
        const integrator = channel.integrators.find(x => x.idIntegrator == integratorId);
        this.channels_integrators.push(
          { channel, integrator }
        );
      }
    }
  }

  showInputCreateParameter() {
    this.showInputCreateParameterState = true;
    console.log(this.showInputCreateParameterState);
  }

  deleteParameter(message: string, parameterName: string) {
    const pointer = message.search(parameterName) - 4;
    const startMessage = message.slice(0, pointer);
    const endMessage = message.slice(pointer + parameterName.length + 8, message.length);
    this.formMessage = startMessage + endMessage;
    this.parameters.splice(this.parameters.indexOf(parameterName), 1);
    if (this.newParameters.find(x => x == parameterName)) {
      this.newParameters.splice(this.newParameters.indexOf(parameterName), 1);
    }
  }

  deleteChannel_Integrator(channel_integrator) {
    this.channels_integrators.splice(this.channels_integrators.indexOf(channel_integrator), 1);
  }

  postTemplate() {

    console.log(this.formMessage);
    console.log(this.parameters);
    console.log(this.newParameters);
    console.log(this.channels_integrators);
    if  ((this.formMessage !== undefined) && (this.formMessage.length > 5)) {
        if ( this.channels_integrators[0]) {
            this.templateService.postTemplate(this.formMessage, this.parameters, this.newParameters, 1, this.channels_integrators);
        } else{
            this.toastr.warning('Falta llenar un campo', 'Aviso',
                {
                    timeOut: 2800,
                    progressBar: true
                });
        }
    } else{
        this.toastr.error('Tal vez quiera escribir un mensaje mas largo', 'Error',
            {
                timeOut: 2800,
                progressBar: true
            });
    }
  }
}
