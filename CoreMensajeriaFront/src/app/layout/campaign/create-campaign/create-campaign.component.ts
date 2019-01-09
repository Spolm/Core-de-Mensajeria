import { Component, OnInit } from '@angular/core';
import { CompanyService } from '../../company/company.service'
import { Company } from '../../../../model/company-model';
import { Campaign } from '../../../../model/campaign-model';
import { CampaignService } from '../campaign.service';
import { ToastrService } from 'ngx-toastr';


@Component({
  selector: 'app-create-campaign',
  templateUrl: './create-campaign.component.html',
  styleUrls: ['./create-campaign.component.scss']
})
export class CreateCampaignComponent implements OnInit {

  newCampaign: Campaign = new Campaign();
  companies: any[];
  constructor(public companyService: CompanyService, public campaignService: CampaignService 
    , private toastr: ToastrService) {
    companyService.getCompanies().subscribe(data => {
      this.companies = data;
    });
  }
  private counter: number = 0;
  private companyList = Array<Company>();
  private vacio: boolean;
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
  }

  /**
   * Metodo para agregar la campaña
   */
  addCampaign(){

    if( (this.newCampaign._nameCampaign != null ) && ( this.newCampaign._descCampaign != null  ) &&
        (this.newCampaign._startCampaign != null ) && (this.newCampaign._endCampaign != null ) &&
        (this.newCampaign._idCompany != null) ){
          var sDate =  new Date(this.newCampaign._startCampaign+"T02:06:58.147")
          var fDate =  new Date(this.newCampaign._endCampaign+"T02:06:58.147")
          this.newCampaign._startCampaign = sDate.toISOString()
          this.newCampaign._endCampaign = fDate.toISOString()

          if(this.newCampaign._startCampaign < this.newCampaign._endCampaign){

    this.campaignService.addCampaign(this.newCampaign).toPromise().then(res =>{
      console.log(this.newCampaign);
      this.newCampaign = new Campaign();
    });

  }

  else{
    this.toastr.error("Fecha inicio mayor que Fecha Fin");
  }
  
  }
  else { 
    this.toastr.error("Algun Campo esta Vacio.");
    this.newCampaign._nameCampaign = null;
    this.newCampaign._descCampaign = null;
    this.newCampaign._startCampaign = null;
    this.newCampaign._endCampaign = null;
    this.newCampaign._idCompany = null;
  }

  }
}

