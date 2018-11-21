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
  styleUrls: ['./campaign.component.scss']
})
export class CampaignComponent implements OnInit {

  constructor(public router: Router, private http: HttpClient, 
    public rest: CampaignService, private toastr: ToastrService) { }

private campaignList = Array<Campaign>();
private vacio: boolean;

ngOnInit() {

this.rest.getCampaigns().subscribe((data) => {
this.vacio = true;
this.campaignList = data;
if (this.campaignList.length > 0) {
this.vacio = false;
}
}, (err) => {
console.log(err);
})
}

/*getCampaigns() {
this.toastr.info("Espere un momento",'Intentando acceder',{
progressBar: true
});
this.rest.getCampaigns().subscribe((result) => {
}, (err) => {
console.log(err);
if (err.status == 0) this.toastr.error('Problema de conexión');
else this.toastr.error(err.error._error);
});
}*/

/*handleLogin() {
if (this.userData._username.length > 0 && this.userData._password.length > 0)
this.login();
else
this.toastr.error('Debes ingresar las credenciales');
}*/

}
