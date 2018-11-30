import { Component, OnInit, ElementRef, ViewChild } from '@angular/core';
import { TemplateService } from '../template.service';
import { Inject } from '@angular/core';
import { DOCUMENT } from '@angular/common';
import { Integrator } from '../../integrator/integrator';

@Component({
  selector: 'app-create-template',
  templateUrl: './create-template.component.html',
  styleUrls: ['./create-template.component.scss']
})


export class CreateTemplateComponent {

  parameters: any = [];
  formMessage: string;
  channels: any = [];
  parametersOrder: any = [];
  channels_integrators: any = [];

  constructor(private templateService: TemplateService, @Inject(DOCUMENT) document) {
    this.getParameters();
    this.getChannels();
  }

  getParameters() {
    this.templateService.getParameters(1).subscribe(data => {
      this.parameters = data;
    });
  }

  getChannels() {
    this.templateService.getChannels().subscribe(data => {
      this.channels = data;
    });
  }

  addParameter(message: string, parameter: string) {
    if (!this.parametersOrder.find(x => x == parameter)) {
      let myFormMessage = document.getElementById('formMessage');
      let pointer = (myFormMessage as HTMLTextAreaElement).selectionStart;
      let startMessage = message.slice(0, pointer);
      let endMessage = message.slice(pointer, message.length);
      this.parametersOrder.push(parameter);
      this.formMessage = startMessage + ' [.$' + parameter + '$.] ' + endMessage;
    }
  }

  addIntegrator(channel: any, integratorId: number) {
    if (!this.channels_integrators.find(x => x.integrator.idIntegrator == integratorId)) {
      let integrator = channel.integrators.find(x => x.idIntegrator == integratorId);
      this.channels_integrators.push(
        { channel, integrator }
      );
    }
  }

  deleteParameter(message: string, parameter: string) {
    let pointer = message.search(parameter) - 4;
    let startMessage = message.slice(0, pointer);
    let endMessage = message.slice(pointer + parameter.length + 8, message.length);
    this.formMessage = startMessage + endMessage;
    this.parametersOrder.splice(this.parametersOrder.indexOf(parameter), 1);
  }

  deleteChannel_Integrator(channel_integrator) {
    this.channels_integrators.splice(this.channels_integrators.indexOf(channel_integrator), 1);
  }

  postTemplate() {
    this.templateService.postTemplate(this.formMessage, this.channels_integrators)
  }
}
