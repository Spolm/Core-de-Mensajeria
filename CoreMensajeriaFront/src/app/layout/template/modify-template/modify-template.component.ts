import { Component } from '@angular/core';
import { TemplateService } from '../template.service';
import { ToastrService } from 'ngx-toastr';
import { ActivatedRoute } from '@angular/router';
import { delay } from 'q';

@Component({
  selector: 'app-modify-template',
  templateUrl: './modify-template.component.html',
  styleUrls: ['./modify-template.component.scss']
})
export class ModifyTemplateComponent {

  templateId: any;
  sub: any;
  templateJson: any = [];
  parametersJson: any = [];
  channelsJson: any = [];
  formMessage = '';
  parameters: Array<string> = [];
  newParameters: Array<string> = [];
  channels_integrators: any = [];
  showInputCreateParameterState = false;

  constructor(private templateService: TemplateService, private toastr: ToastrService, private route: ActivatedRoute) {
    this.sub = this.route.params.subscribe(params => {
      this.templateId = +params['id'];
    });
    this.getParameters();
    this.getChannels();
    this.getTemplate();
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

  async getTemplate() {
    this.templateService.getTemplate(this.templateId).subscribe(data => {
      this.templateJson = data;
    });
    await delay(1000);
    this.formMessage = this.templateJson.message.message;
    this.assignParameter(this.parameters , this.templateJson.message.parameterArrayList);
    this.assignChannelsIntegrators(this.channels_integrators, this.templateJson.channels);
  }

  assignParameter(place: Array<any>, data: Array<any>) {
    data.forEach( (value) => {
      place.push(value.name);
    });
  }

  assignChannelsIntegrators(place: Array<any>, data: Array<any>) {
    data.forEach( (channel) => {
      channel.integrators.forEach( (integrator) => {
        place.push(
          { channel, integrator}
          );
      });
    });
  }

  addParameter(message: string, parameterName: string) {
          const myFormMessage = document.getElementById('formMessage');
          const pointer = (myFormMessage as HTMLTextAreaElement).selectionStart;
          const startMessage = message.slice(0, pointer);
          const endMessage = message.slice(pointer, message.length);
          this.parameters.push(parameterName);
          this.formMessage = startMessage + ' [.$' + parameterName + '$.] ' + endMessage;

  }

  addNewParameter(message: string, parameterName: string) {
    parameterName = parameterName.trim();
    if ((parameterName.valueOf() !== '')) {
        parameterName = parameterName.toLowerCase();
        parameterName = parameterName.charAt(0).toUpperCase() + parameterName.slice(1, parameterName.length);
        if (!this.parameters.find(x => x === parameterName)) {
            this.addParameter(message, parameterName);
            if (!this.newParameters.find(x => x == parameterName)) {
                this.newParameters.push(parameterName);
            } else {
                this.toastr.warning('El parametro ya esta registrado', 'Aviso',
                    {
                        timeOut: 2800,
                        progressBar: true
                    });
                }
            }
        } else {
            this.toastr.warning('No a escrito ningun parametro', 'Aviso',
            {
                timeOut: 2800,
                progressBar: true
            });
        }
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

  updateTemplate() {
      this.formMessage = this.formMessage.trim();
      if (this.formMessage != '') {
          if ((this.formMessage !== undefined) && (this.formMessage.length > 5)) {
              if (this.channels_integrators[0]) {
                  this.templateService.updateTemplate(this.templateId, this.formMessage, this.parameters, this.newParameters, 1, this.channels_integrators);
              } else {
                  this.toastr.error('Falta llenar un campo', 'Error',
                      {
                          timeOut: 2800,
                          progressBar: true
                      });
              }
          } else {
              this.toastr.error('Tal vez quiera escribir un mensaje mas largo', 'Error',
                  {
                      timeOut: 2800,
                      progressBar: true
                  });
          }
      } else {
          this.toastr.warning('No puede crear un template sin mensaje!', 'Aviso',
              {
                  timeOut: 2800,
                  progressBar: true
              });
      }
  }
}
