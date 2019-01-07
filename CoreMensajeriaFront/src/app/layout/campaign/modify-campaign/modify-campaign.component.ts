import { ToastrService } from 'ngx-toastr';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Company } from '../../../../model/company-model';
import { Router } from '@angular/router';
import { Campaign } from '../../../../model/campaign-model';
import { CampaignService } from '../campaign.service';
import { CompanyService } from '../../company/company.service';


@Component({
  selector: 'app-modify-campaign',
  templateUrl: './modify-campaign.component.html',
  styleUrls: ['./modify-campaign.component.scss']
})
export class ModifyCampaignComponent implements OnInit {

  opcamp: Campaign = new Campaign();
  verSeleccion: string = "";
  datos;
  newCampaign: Campaign = new Campaign();
  campaigns: any = [];

  constructor(public companyService: CompanyService, public router: Router, private http: HttpClient, 
    public campaignService: CampaignService, private toastr: ToastrService) { 
      campaignService.getCampaigns().subscribe(data => {
        this.campaigns = data;
      });
    }


  private companyList = Array<Company>();
     private vacio: boolean;
     private campaignList = Array<Campaign>();
  ngOnInit() {

    this.companyService.getCompanies().subscribe((data) => {
      this.vacio = true;
      this.companyList = data;
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


  editCompany() {
    var sDate =  new Date(this.opcamp._startCampaign)
    console.log(sDate.toISOString);
    this.opcamp._startCampaign = this.opcamp._startCampaign+"T02:06:58.147"
    this.opcamp._endCampaign = this.opcamp._endCampaign+"T02:06:58.147"
    this.campaignService.editCampaign(this.opcamp).toPromise().then(res => {
      //manejo de la respuesta del servicio
    });
}

}
