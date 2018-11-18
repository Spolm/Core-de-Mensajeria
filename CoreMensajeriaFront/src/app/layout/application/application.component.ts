import { Component, OnInit } from '@angular/core';
import { routerTransition } from '../../router.animations';
import { ApplicationService } from './application.service';
import { ToastrService } from 'ngx-toastr';
//import { ApiServiceProvider } from '../../providers/api-service/api-service';

@Component({
  selector: 'app-application',
  templateUrl: './application.component.html',
  styleUrls: ['./application.component.scss'],
  animations: [routerTransition()]
})
export class ApplicationComponent implements OnInit {
  applications: any;

  constructor(public rest: ApplicationService, private toastr: ToastrService) {
    
   }

  ngOnInit() {
    this.rest.getApps().subscribe((result) => {
      console.log('Aplicaciones cargadas');
      this.applications = result;
      console.log(this.applications);
      
    }, (err) => {
      console.log(err);
      if (err.status == 0) this.toastr.error('Problema de conexión');
      else this.toastr.error(err.error._error);
    });
  }

}
