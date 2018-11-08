import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-campaign',
  templateUrl: './campaign.component.html',
  styleUrls: ['./campaign.component.scss']
})
export class CampaignComponent implements OnInit {

  // PRUEBA
  channels = ['SMS','Email'];
  integrators = ['AWeber','MailChimp','Digitel','Movistar','Movilnet']
  // FIN PRUEBA
  constructor() { }

  ngOnInit() {
  }

}
