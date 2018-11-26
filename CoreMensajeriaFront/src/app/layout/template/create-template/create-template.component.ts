import { Component, OnInit, ElementRef, ViewChild } from '@angular/core';
import { TemplateService } from '../template.service';
import { Inject }  from '@angular/core';
import { DOCUMENT } from '@angular/common'; 

@Component({
  selector: 'app-create-template',
  templateUrl: './create-template.component.html',
  styleUrls: ['./create-template.component.scss']
})
export class CreateTemplateComponent {

  parameters: any = [];
  formMessage: string;
  parametersOrder: any = [];

  constructor(private templateService: TemplateService, @Inject(DOCUMENT) document) {
    this.getParameters();
  }

  getParameters(){
    this.templateService.getParameters().subscribe(data => {
      this.parameters = data;
    });
  }

  addParameter(message: string, parameter: string){
    let myFormMessage = document.getElementById('formMessage');
    let pointer = (myFormMessage as HTMLTextAreaElement).selectionStart;
    let startMessage = message.slice(0, pointer);
    let endMessage = message.slice(pointer, message.length);
    this.parametersOrder.push(parameter);
    this.formMessage = startMessage + ' [.$' + parameter + '$.] ' + endMessage;
  }

  deleteParameter(message: string, parameter: string){
    let pointer = message.search(parameter) - 4;
    let startMessage = message.slice(0, pointer);
    let endMessage = message.slice(pointer + parameter.length + 8, message.length);
    this.formMessage = startMessage + endMessage;
    this.parametersOrder.splice( this.parametersOrder.indexOf(parameter), 1);
  }
}
