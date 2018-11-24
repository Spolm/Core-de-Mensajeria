import { Component, OnInit } from '@angular/core';
import { environment } from '../../../environments/environment';
@Component({
  selector: 'app-channel',
  templateUrl: './channel.component.html',
  styleUrls: ['./channel.component.scss']
})
export class ChannelComponent implements OnInit {

  // PRUEBA
  channels = ['SMS','Email'];
  integrators = ['AWeber','MailChimp','Digitel','Movistar','Movilnet']
  // FIN PRUEBA
  constructor() { }

  ngOnInit() {
  }

}
