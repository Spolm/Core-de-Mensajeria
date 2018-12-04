import { Component, OnInit, ElementRef, ViewChild } from '@angular/core';
import { TemplateService } from '../template.service';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import { delay } from 'q';

@Component({
  selector: 'app-create-template',
  templateUrl: './create-template.component.html',
  styleUrls: ['./create-template.component.scss']
})


export class CreateTemplateComponent {

  userId: string = localStorage.getItem("userid");
  companyId: string = localStorage.getItem("companyId");
  privilegesJson: any = [];
  CTEMPLATE = false;
  RTEMPLATE = false;
  UTEMPLATE = false;
  DTEMPLATE = false;
  ATEMPLATE = false;

  parametersJson: any = [];
  channelsJson: any = [];
  applicationsJson: any = [];
  originOption = 'app';
  applicationId: number;
  formMessage = '';
  parameters: Array<string> = [];
  newParameters: Array<string> = [];
  channels_integrators: any = [];
  dateIni: string;
  dateEnd: string;
  timeIni: string;
  timeEnd: string;
  showInputCreateParameterState = false;


  constructor(private templateService: TemplateService, private toastr: ToastrService, private router: Router) {
    this.getParameters();
    this.getChannels();
    this.getApplications(Number(this.companyId));
    this.getPrivileges(this.userId, Number(this.companyId));
  }

  async getPrivileges(userId: string, companyId: number) {
    this.templateService.getPrivilegesByUserAndCompany(userId, companyId).subscribe(data => {
      this.privilegesJson = data;
    });
    await delay(500);
    this.assignPrivileges(this.privilegesJson);
  }

  assignPrivileges(privileges: Array<any>) {
    privileges.forEach((privilege) => {
      if (privilege._codePrivileges == 'CTEMPLATE') {
        this.CTEMPLATE = true;
      } else if (privilege._codePrivileges == 'RTEMPLATE') {
        this.RTEMPLATE = true;
      } else if (privilege._codePrivileges == 'UTEMPLATE') {
        this.UTEMPLATE = true;
      } else if (privilege._codePrivileges == 'DTEMPLATE') {
        this.DTEMPLATE = true;
      } else if (privilege._codePrivileges == 'ATEMPLATE') {
        this.ATEMPLATE = true;
      }
    });
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

  getApplications(company: number) {
    this.templateService.getApplicationsByCompany(company).subscribe(data => {
      this.applicationsJson = data;
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
      const planning: any = [];
      if (this.dateIni <= this.dateEnd) {
          planning.push(this.dateIni, this.dateEnd, this.timeIni, this.timeEnd);

          console.log(planning);

          if (this.originOption !== 'app') {
              this.applicationId = 0;
          }
          this.formMessage = this.formMessage.trim();
          if (this.formMessage != '') {
              if ((this.formMessage !== undefined) && (this.formMessage.length > 5)) {
                  if (this.channels_integrators[0] && (this.applicationId !== undefined) && this.dateIni !== undefined && this.dateEnd !== undefined && this.timeIni !== undefined && this.timeEnd !== undefined) {
                      this.templateService.postTemplate(this.formMessage, this.parameters, this.newParameters, 1, this.channels_integrators, this.applicationId, planning);
                  } else {
                      this.toastr.warning('Falta llenar un campo', 'Aviso',
                          {
                              timeOut: 2800,
                              progressBar: true
                          });
                  }
              } else {
                  this.toastr.warning('Tal vez quiera escribir un mensaje mas largo', 'Aviso',
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
      } else {
          this.toastr.warning('La fecha inicial no puede ser superior a la final', 'Aviso',
              {
                  timeOut: 2800,
                  progressBar: true
              });
      }
  }
}
