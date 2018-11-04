import { Component, OnInit } from '@angular/core';
import { Http } from '@angular/http';

@Component({
  selector: 'app-integrator',
  templateUrl: './integrator.component.html',
  styleUrls: ['./integrator.component.scss']
})
 
export class IntegratorComponent implements OnInit {

  integrators = [
    {
      apiIntegrator: '',
      idIntegrator: '',
      messageCost: '',
      nameIntegrator: '',
      threadCapacity: ''
    }
  ];

  constructor( private http: Http) { 

  }

  ngOnInit() {
    this.http.get( 'http://localhost:8080/CoreMensajeria_war_exploded/' + 'integrators' ).
      toPromise().then( r => r.json() ).
      then( r => this.integrators = r);
  }

}
