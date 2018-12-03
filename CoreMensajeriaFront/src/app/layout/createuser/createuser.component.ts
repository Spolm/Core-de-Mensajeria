import { Component, OnInit, Input } from '@angular/core';
import { routerTransition } from '../../router.animations';
import { Router } from '@angular/router';
import { Users } from './models/users';
import { Company } from './models/companies';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Http } from '@angular/http';
import { CreateUser } from './create_user.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-createuser',
  templateUrl: './createuser.component.html',
  styleUrls: ['./createuser.component.scss']
})
export class CreateuserComponent implements OnInit {

  @Input() userData = { _emailUser: '', _usernameUser: '', _birthdateUser: '', _countryUser: '',
  _cityUser: '', _addressUser: '', _phoneUser:'', _genderUser:'', _typeUser: 1, _passwordUser: ''};

  companies : Array<Company>;

  constructor(public router: Router, private http: Http, public rest: CreateUser, private toastr: ToastrService){
    this.http.get('http://localhost:8080/CoreMensajeria_war_exploded/profile/listcompanies').subscribe(resp=>this.companies = resp.json());
  }

  ngOnInit() {
  }

  add() {
    this.toastr.info("Espere un momento",'Intentando acceder',{
      progressBar: true
    });
    this.rest.createUser(this.userData).subscribe((result) => {
      this.toastr.success('Creado con éxito');
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
    console.log(this.companies);
    this.add();
  }

}
