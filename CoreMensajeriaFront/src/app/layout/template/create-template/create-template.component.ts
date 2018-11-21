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

  constructor(private templateService: TemplateService, @Inject(DOCUMENT) document) {
    this.getParameters();
  }

  getParameters(){
    this.templateService.getParameters().subscribe(data => {
      this.parameters = data;
    });
  }

  addParameter(message: string, parameter: string){
    this.formMessage = message + '[.$' + parameter + '$.]';
  }
}
