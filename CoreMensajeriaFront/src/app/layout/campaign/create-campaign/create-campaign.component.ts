import { Component, OnInit } from '@angular/core';
import { CompanyService } from '../../company/company.service'
import { Company } from '../../../../model/company-model';
@Component({
  selector: 'app-create-campaign',
  templateUrl: './create-campaign.component.html',
  styleUrls: ['./create-campaign.component.scss']
})
export class CreateCampaignComponent implements OnInit {

  companies: any [];
  constructor(public companyService: CompanyService) {
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
  }

}
