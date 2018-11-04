import { Component, OnInit } from '@angular/core';
import { routerTransition } from '../../router.animations';

@Component({
  selector: 'app-template',
  templateUrl: './template.component.html',
  styleUrls: ['./template.component.scss'],
  animations: [routerTransition()]
})
export class TemplateComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
