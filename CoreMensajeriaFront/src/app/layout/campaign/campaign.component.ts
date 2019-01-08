import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { routerTransition } from '../../router.animations';
import { HttpClient } from '@angular/common/http';
import { CampaignService } from './campaign.service';
import { ToastrService } from 'ngx-toastr';
import { Campaign } from '../../../model/campaign-model';
import { CompanyService } from '../company/company.service'
import { Company } from '../../../model/company-model';

@Component({
  selector: 'app-campaign',
  templateUrl: './campaign.component.html',
  styleUrls: ['./campaign.component.scss'],
  animations: [routerTransition()]
})
export class CampaignComponent implements OnInit {

  campaigns: any = [];

  companyList: any;
  companies: any [];

  constructor(public companyService: CompanyService, public router: Router, private http: HttpClient, 
    public campaignService: CampaignService, private toastr: ToastrService) { 
      campaignService.getCampaigns().subscribe(data => {
        this.campaigns = data;
      });
    }
private counter: number = 0;
private campaignList = Array<Campaign>();
private vacio: boolean;
private lastCampaignId: number;
campaign: Campaign = new Campaign();
campaignStatus: any;
ngOnInit() {

  this.companyService.getCompanies().subscribe((data) => {
    this.vacio = true;
    this.companyList = data;
    console.log(this.companyList);
    localStorage.setItem('companyid', data._idCompany);
    if (this.companyList.length > 0) {
    this.vacio = false;
    }
    }, (err) => {
    console.log(err);
    })


this.campaignService.getCampaigns().subscribe((data) => {
this.vacio = true;
this.campaignList = data;
console.log(this.campaignList);
if (this.campaignList.length > 0) {
this.vacio = false;
}
}, (err) => {
console.log(err);
})
}

activateCampaign(_idCampaign: number , _statusCampaign : boolean , campaignt : Campaign){
  this.toastr.info("Para confirmar realice doble click de nuevo", "Activar la compañia id: "+ _idCampaign,
  {
    timeOut: 2800,
    progressBar: true
  });
  this.counter++;
  var stat = {_idCampaign:campaignt._idCampaign, _status:campaignt._statusCampaign};
  if(this.counter == 2 && this.lastCampaignId == _idCampaign){
    this.campaignService.activateCampaign(stat).toPromise().then(res => {
    });
    console.log(campaignt);
  
    this.toastr.success("Campaign activada", "Campaign id: "+ _idCampaign,
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

desactivateCampaign(_idCampaign: number , _statusCampaign : boolean , campaignta : Campaign){
  this.toastr.info("Para confirmar realice doble click de nuevo", "Desactivar la compañia id: "+ _idCampaign,
  {
    timeOut: 2800,
    progressBar: true
  });
  this.counter++;
  if(this.counter == 2 && this.lastCampaignId == _idCampaign){
    console.log(campaignta);
    var stat = {_idCampaign:campaignta._idCampaign, _status:campaignta._statusCampaign};
    this.campaignService.activateCampaign(stat).toPromise().then(res => {
      //manejo de la respuesta del servicio
    });
    this.toastr.success("campaign desactivada", "Campaign id: "+ _idCampaign,
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
