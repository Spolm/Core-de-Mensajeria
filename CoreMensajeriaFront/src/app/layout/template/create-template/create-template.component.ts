import { Component, OnInit, ElementRef, ViewChild } from '@angular/core';
import { TemplateService } from '../template.service';
import { ToastrService } from 'ngx-toastr';

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
  showInputCreateParameterState: boolean = false;

  constructor(private templateService: TemplateService) {
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
    if (!this.parameters.find(x => x == parameterName)) {
      let myFormMessage = document.getElementById('formMessage');
      let pointer = (myFormMessage as HTMLTextAreaElement).selectionStart;
      let startMessage = message.slice(0, pointer);
      let endMessage = message.slice(pointer, message.length);
      this.parameters.push(parameterName);
      this.formMessage = startMessage + ' [.$' + parameterName + '$.] ' + endMessage;
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
        let integrator = channel.integrators.find(x => x.idIntegrator == integratorId);
        this.channels_integrators.push(
          { channel, integrator }
        );
      }
    }
  }

  showInputCreateParameter() {
    this.showInputCreateParameterState = true;
    console.log(this.showInputCreateParameterState)
  }

  deleteParameter(message: string, parameterName: string) {
    let pointer = message.search(parameterName) - 4;
    let startMessage = message.slice(0, pointer);
    let endMessage = message.slice(pointer + parameterName.length + 8, message.length);
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
    //this.templateService.postTemplate(this.formMessage, this.channels_integrators)
  }
}
