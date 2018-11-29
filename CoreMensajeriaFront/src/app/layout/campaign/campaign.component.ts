import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { routerTransition } from '../../router.animations';
import { HttpClient } from '@angular/common/http';
import { CampaignService } from './campaign.service';
import { ToastrService } from 'ngx-toastr';
import { Campaign } from '../../../model/campaign-model';

@Component({
  selector: 'app-campaign',
  templateUrl: './campaign.component.html',
  styleUrls: ['./campaign.component.scss'],
  animations: [routerTransition()]
})
export class CampaignComponent implements OnInit {

  campaigns: any = [];

  constructor(public router: Router, private http: HttpClient, 
    public campaignService: CampaignService, private toastr: ToastrService) { 
      campaignService.getCampaigns().subscribe(data => {
        this.campaigns = data;
      });
    }
private counter: number = 0;
private campaignList = Array<Campaign>();
private vacio: boolean;
private lastCampaignId: number;
ngOnInit() {

this.campaignService.getCampaigns().subscribe((data) => {
this.vacio = true;
this.campaignList = data;
if (this.campaignList.length > 0) {
this.vacio = false;
}
}, (err) => {
console.log(err);
})
}

activateCampaign(_idCampaign: number){
  this.toastr.info("Para confirmar realice doble click de nuevo", "Activar la campaña id: "+ _idCampaign,
  {
    timeOut: 2800,
    progressBar: true
  });
  this.counter++;
  if(this.counter == 2 && this.lastCampaignId == _idCampaign){
    this.campaignService.activateCampaign(_idCampaign);
    this.toastr.success("Campaña activada", "Campaign id: "+ _idCampaign,
    {
      timeOut: 2800,
      progressBar: true
    });
    this.counter = 0;
    this.ngOnInit();
  }
  if(this.counter >= 2) this.counter = 0;
  this.lastCampaignId = _idCampaign;
}

deactivateCampaign(_idCampaign: number){
  this.toastr.info("Para confirmar realice doble click de nuevo", "Desactivar la campaña id: "+ _idCampaign,
  {
    timeOut: 2800,
    progressBar: true
  });
  this.counter++;
  if(this.counter == 2 && this.lastCampaignId == _idCampaign){
    this.campaignService.activateCampaign(_idCampaign);
    this.toastr.success("Campaña desactivada", "Campaign id: "+ _idCampaign,
    {
      timeOut: 2800,
      progressBar: true
    });
    this.counter = 0;
    this.ngOnInit();
  }
  if(this.counter >= 2) this.counter = 0;
  this.lastCampaignId = _idCampaign;
}

}
