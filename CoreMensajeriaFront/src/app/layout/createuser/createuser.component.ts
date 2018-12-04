import { Component, OnInit, Input } from '@angular/core';
import { routerTransition } from '../../router.animations';
import { Router } from '@angular/router';
import { Http } from '@angular/http';
import { ToastrService } from 'ngx-toastr';
import { RestService } from '../../shared/services/rest.service';

@Component({
  selector: 'app-createuser',
  templateUrl: './createuser.component.html',
  styleUrls: ['./createuser.component.scss']
})
export class CreateuserComponent implements OnInit {

  @Input() userData = { _emailUser: '', _usernameUser: '', _birthdateUser: '', _countryUser: '',
  _cityUser: '', _addressUser: '', _phoneUser:'', _genderUser:'', _typeUser: 1, _passwordUser: '', _companyUser:0};

  companies : any = [];
  user : any;
  users:any =[];

  constructor(public router: Router, private http: Http, public rest: RestService, private toastr: ToastrService){
    
  }

  ngOnInit() {
    this.initCompany();
    this.initUser();
    this.initUsers();
  }

  initCompany(){
    this.rest.getData("profile/listcompanies").subscribe((data: {}) => {
      this.companies = data;
    },
    (err) => {
      this.toastr.error("Error de Conexion.");
      console.log(err);
    });
  }

  initUsers(){
    this.rest.getData("profile/listusers").subscribe((data: {}) => {
      this.users = data;
    },
    (err) => {
      this.toastr.error("Error de Conexion.");
      console.log(err);
    });
  }

  initUser(){
    this.rest.getData("profile/user/"+localStorage.userid).subscribe((data: {}) => {
      this.user = data;
    },
    (err) => {
      this.toastr.error("Error de Conexion.");
      console.log(err);
    });
  }

  add() {
    this.toastr.info("Espere un momento",'Intentando acceder',{
      progressBar: true
    });
    this.rest.postData('profile/add',this.userData).subscribe((result) => {
      this.toastr.success('Creado con éxito');
      console.log(result);
      this.router.navigate(['/createuser']);
    }, (err) => {
      // console.log(err);
      if (err.status == 500) {
        this.toastr.error(err.error.message);
        console.log(err);
      } //Aqui poner mensaje de la excepcion
      else this.toastr.error(err.error._error);
    });
  }

  handleAdd() {
    this.add();
    console.log(this.companies);
  }

  editUser(id){
    this.router.navigate(['/profile', {id: id }]);
  }

  checkRole(type){
    if ( ( !( type == 2 || type == 1 ) && this.user._typeUser == 2) || (this.user._typeUser == 1) ){
      return true;
    }
    return false;
  }

  stringType(type){

    if(type == 1){
      return 'Superusuario'
    }
    else if(type == 2){
      return 'Administrador'
    }
    else if(type == 3){
      return 'Creador'
    }
    else if(type == 4){
      return 'Aprobador'
    }
    else{
      return 'Consultor'
    }
  }
  
}
