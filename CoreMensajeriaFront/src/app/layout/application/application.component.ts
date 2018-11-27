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
  companies : any = [];
  singleApplication: any = [];
  newAppForm: FormGroup;
  formSubmitted : boolean = false;
  modalBackdrop = false;
  displayNewAppModal = false;
  displayAppInfoModal = false;

  constructor(public rest: RestService,  private toastr: ToastrService, private formBuilder: FormBuilder) {}

  ngOnInit() {
    this.getApplications();
    this.getCompanies(localStorage.getItem("userid"));
    this.newAppForm = this.formBuilder.group({
      _nameApplication: ['',  [Validators.required, 
                        Validators.pattern('[a-zA-ZñÑ0-9 ]*'),
                        Validators.maxLength(32)]],
      _descriptionApplication: ['', [Validators.required, 
                        Validators.maxLength(500)]],
      _companyId: [0, [Validators.required]],
      _userId: localStorage.getItem("userid")
    });
  }
  
  getApplications() {
    this.applications = [];
    this.rest.getData("applications").subscribe((data: {}) => {
      this.applications = data;
      this.fillApplicationsCompany();
    },
    (err) => {
      this.toastr.error("Error de Conexion.");
      console.log(err);
    });
  }

  addApplication(){
    //Convert values to integer
    this.newAppForm.value._companyId *= 1;
    this.newAppForm.value._userId *= 1;
    //Send data
    this.rest.postData("applications", this.newAppForm.value)
        .subscribe(res => {
          this.toastr.success(res._message);
          this.toggleNewAppModal();
          this.getApplications();
          this.resetForm();
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
    for (let index = 0; index < this.applications.length; index++) {
      const element = this.applications[index];
      if(element._idApplication == id){
        this.singleApplication = element;
        this.toggleAppInfoModal();
        return;
      }
    }
  }

  toggleNewAppModal(){
    this.modalBackdrop = !this.modalBackdrop; 
    this.displayNewAppModal = !this.displayNewAppModal;
  }

  toggleAppInfoModal(){
    this.modalBackdrop = !this.modalBackdrop; 
    this.displayAppInfoModal = !this.displayAppInfoModal;
  }

  fillApplicationsCompany(){
    for (let index = 0; index < this.applications.length; index++) {
      const element = this.applications[index];
      this.getCompanyName(element);
    }
  }

  getCompanyName(element){
    var company:any;
    this.rest.getData("M02_Companies/CompanyDetails?id=" + element._companyId).subscribe((data: {}) => {
      company = data;
      element._companyId = company._name;
    },
    (err) => {
      console.log(err);
    });
  }

  getCompanies(userId){
    this.rest.getData("M02_Companies/GetCompanies?id=" + userId).subscribe((data: {}) => {
      this.companies = data; 
      this.cleanCompanies();
    },
    (err) => {
      this.toastr.error("Error de Conexion.");
      console.log(err);
    });
  }

  resetForm(){
    this.newAppForm.value._nameApplication = "";
    this.newAppForm.value._descriptionApplication = "";
    this.newAppForm.value._companyId = 0;
  }

  cleanCompanies(){
    var cleanList: any = [];
    for (let index = 0; index < this.companies.length; index++) {
      if(this.companies[index]._status = 1){
        cleanList.push(this.companies[index]);
      }
    }
    this.companies = cleanList;
  }
}
