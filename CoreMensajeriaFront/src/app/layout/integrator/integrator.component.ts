import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-integrator',
  templateUrl: './integrator.component.html',
  styleUrls: ['./integrator.component.scss']
})
export class IntegratorComponent implements OnInit {

  // PRUEBA
  integrators = ['AWeber','MailChimp','Digitel','Movistar','Movilnet']
  // FIN PRUEBA
  
  constructor() { }

  ngOnInit() {
  }

}
