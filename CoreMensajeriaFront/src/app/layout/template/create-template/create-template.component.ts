import { Component, OnInit, ElementRef, ViewChild } from '@angular/core';
import { TemplateService } from '../template.service';
import { Inject } from '@angular/core';
import { DOCUMENT } from '@angular/common';

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
  channel_integrator: any = [];

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
    let myFormMessage = document.getElementById('formMessage');
    let pointer = (myFormMessage as HTMLTextAreaElement).selectionStart;
    let startMessage = message.slice(0, pointer);
    let endMessage = message.slice(pointer, message.length);
    this.parametersOrder.push(parameter);
    this.formMessage = startMessage + ' [.$' + parameter + '$.] ' + endMessage;
  }

  addIntegrator(channel: number, integrator: number) {
    console.log('channel: ' + channel);
    console.log( integrator);
    let integratorNumber = Number(integrator);
    this.channel_integrator.push(
      { channel, integratorNumber }
    );
    console.log(this.channel_integrator);
  }

  deleteParameter(message: string, parameter: string) {
    let pointer = message.search(parameter) - 4;
    let startMessage = message.slice(0, pointer);
    let endMessage = message.slice(pointer + parameter.length + 8, message.length);
    this.formMessage = startMessage + endMessage;
    this.parametersOrder.splice(this.parametersOrder.indexOf(parameter), 1);
  }

  postTemplate() {
      this.templateService.postTemplate(this.formMessage,this.channel_integrator)
  }
}
