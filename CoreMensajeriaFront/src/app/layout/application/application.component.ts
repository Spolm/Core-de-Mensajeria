import { Component, OnInit, Input } from '@angular/core';
import { routerTransition } from '../../router.animations';
import { ToastrService } from 'ngx-toastr';
import { RestService } from '../../shared/services/rest.service';

@Component({
  selector: 'app-application',
  templateUrl: './application.component.html',
  styleUrls: ['./application.component.scss'],
  animations: [routerTransition()]
})
export class ApplicationComponent implements OnInit {

  applications: any = [];
  @Input() newApp = { _name : '', _description : '' };
  displayModal = false;

  constructor(public rest: RestService,  private toastr: ToastrService) { 
  }

  ngOnInit() {
    this.getApplications();
  }
  
  getApplications() {
    this.applications = [];
    this.rest.getData("applications").subscribe((data: {}) => {
      console.log(data);
      this.applications = data;
    });
  }

  deleteApplication(id){
    console.log("app:" + id);
    
    this.applications = [];
    this.rest.deleteData("applications", id)
    .subscribe(res => {
      this.toastr.success("Good");
      this.getApplications();
    }, (err) => {
      this.toastr.error("Error de conexion.");
      console.log(err);
    }
    );
  }

  addApplication(){
    console.log(this.newApp);
  }

  openModal(){
    this.displayModal = true; 
  }

  closeModal(){
    this.displayModal = false; 
  }

}
