import { Component, OnInit, Input } from '@angular/core';
import { routerTransition } from '../../router.animations';
import { Router } from '@angular/router';
import { Users } from './models/users';
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

  constructor(public router: Router, private http: Http, public rest: CreateUser, private toastr: ToastrService){
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
    console.log(this.userData);
    this.add();
  }

}
