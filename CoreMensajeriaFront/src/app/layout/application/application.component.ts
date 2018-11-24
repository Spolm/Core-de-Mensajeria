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

  @Input() newApp = { _nameApplication : '', _descriptionApplication : '', _userId : 1, _companyId: 0 };
  applications: any = [];
  singleApplication: any =[];
  companies : any = [];
  modalBackdrop = false;
  displayNewAppModal = false;
  displayAppInfoModal = false;

  constructor(public rest: RestService,  private toastr: ToastrService) { 
  }

  ngOnInit() {
    this.getApplications();
  }
  
  getApplications() {
    this.applications = [];
    this.rest.getData("applications").subscribe((data: {}) => {
      this.applications = data;
    },
    (err) => {
      this.toastr.error("Error de Conexion.");
      console.log(err);
    });
  }

  deleteApplication(id){
    this.rest.deleteData("applications", id)
    .subscribe(res => {
      this.toastr.success(res._message);
      this.getApplications();
    }, (err) => {
      this.toastr.error(err.error._message);
      console.log(err);
    }
    );
  }

  addApplication(){
    this.newApp._companyId = 2;
    this.rest.postData("applications", this.newApp)
    .subscribe(res => {
      this.toastr.success(res._message);
      this.toggleNewAppModal();
      this.getApplications();
    }, (err) => {
      this.toastr.error(err.error._message);
      console.log(err);
    });
  }

  activeApplication(id){
    this.rest.putData("applications/active","", id)
    .subscribe(res => {
      this.toastr.success(res._message);
      this.getApplications();
    }, (err) => {
      this.toastr.error(err.error._message);
      console.log(err);
    });
  }

  pauseApplication(id){
    this.rest.putData("applications/inactive","", id)
    .subscribe(res => {
      this.toastr.success(res._message);
      this.getApplications();
    }, (err) => {
      this.toastr.error(err.error._message);
      console.log(err);
    });
  }

  viewApplication(id){
    this.rest.getData("applications/id/" + id)
    .subscribe((data: {}) => {
      this.singleApplication = data;
      this.toggleAppInfoModal();
      console.log(data);
      
    }, (err) => {
      this.toastr.error(err.error._message);
      console.log(err);
    });
  }

  toggleNewAppModal(){
    this.modalBackdrop = !this.modalBackdrop; 
    this.displayNewAppModal = !this.displayNewAppModal;
  }

  toggleAppInfoModal(){
    this.modalBackdrop = !this.modalBackdrop; 
    this.displayAppInfoModal = !this.displayAppInfoModal;
  }

}
