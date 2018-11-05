import { Component, OnInit } from '@angular/core';
import { routerTransition } from '../../router.animations';

@Component({
  selector: 'app-application',
  templateUrl: './application.component.html',
  styleUrls: ['./application.component.scss'],
  animations: [routerTransition()]
})
export class ApplicationComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
