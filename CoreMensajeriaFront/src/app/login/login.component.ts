import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import { routerTransition } from '../router.animations';
import { HttpClient } from '@angular/common/http';
import { LoginService } from './login.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
  animations: [routerTransition()]
})
export class LoginComponent implements OnInit {
  users: any = [];
  response: any
  @Input() userData = { _username: '', _password: '' };


  constructor(public router: Router, private http: HttpClient, public rest: LoginService, private toastr: ToastrService) {
  }

  ngOnInit() { }

  login() {
    this.toastr.info("Espere un momento",'Intentando acceder',{
      progressBar: true,
    });
    this.rest.logUser(this.userData).subscribe((result) => {
      this.toastr.success('Has iniciado sesión');
      localStorage.setItem('isLoggedin', 'true');
      localStorage.setItem('userConnected', this.userData._username);
      this.router.navigate(['/blank-page']);
    }, (err) => {
      console.log(err);
      if (err.status == 0) this.toastr.error('Problema de conexión');
      else this.toastr.error(err.error._error);
    });
  }

  handleLogin() {
    if (this.userData._username.length > 0 && this.userData._password.length > 0)
      this.login();
    else
      this.toastr.error('Debes ingresar las credenciales');
  }
}
