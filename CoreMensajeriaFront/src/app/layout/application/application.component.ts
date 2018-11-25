import { Component, OnInit, Input } from '@angular/core';
import { routerTransition } from '../../router.animations';
import { ToastrService } from 'ngx-toastr';
import { RestService } from '../../shared/services/rest.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-application',
  templateUrl: './application.component.html',
  styleUrls: ['./application.component.scss'],
  animations: [routerTransition()]
})
export class ApplicationComponent implements OnInit {

  applications: any = [];
  singleApplication: any =[];
  companies : any = [];
  newAppForm: FormGroup;
  formSubmitted : boolean = false;
  modalBackdrop = false;
  displayNewAppModal = false;
  displayAppInfoModal = false;

  constructor(public rest: RestService,  private toastr: ToastrService, private formBuilder: FormBuilder) {}

  ngOnInit() {
    this.getApplications();
    this.newAppForm = this.formBuilder.group({
      _nameApplication: ['',  [Validators.required, 
                        Validators.pattern('[a-zA-ZñÑ0-9 ]*'),
                        Validators.maxLength(32)]],
      _descriptionApplication: ['', [Validators.required, 
                        Validators.maxLength(500)]],
      _companyId: [0, [Validators.required]],
      _userId: "1"
  });
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
    this.rest.deleteData("applications/" + id)
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
    this.rest.postData("applications", this.newAppForm.value)
        .subscribe(res => {
          this.toastr.success(res._message);
          this.toggleNewAppModal();
          this.getApplications();
        }, (err) => {
          this.toastr.error(err.error._message);
          console.log(err);
        });
  }

  checkApplication(){
    this.formSubmitted = true;
    if (this.newAppForm.valid) {
      this.addApplication();
    }
    else{
      this.toastr.error("Formulario invalido.");
    }
  }

  activeApplication(id){
    this.rest.putData("applications/active/" + id,"")
    .subscribe(res => {
      this.toastr.success(res._message);
      this.getApplications();
    }, (err) => {
      this.toastr.error(err.error._message);
      console.log(err);
    });
  }

  pauseApplication(id){
    this.rest.putData("applications/inactive/" + id,"")
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
