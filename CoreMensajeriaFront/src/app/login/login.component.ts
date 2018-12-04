import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import { routerTransition } from '../router.animations';
import { HttpClient } from '@angular/common/http';
import { ToastrService } from 'ngx-toastr';
import { RestService } from '../shared/services/rest.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
  animations: [routerTransition()]
})
export class LoginComponent implements OnInit {
  users: any = [];
  response: any;
  permitions: any = [];
  @Input() userData = { _username: '', _password: '' };


  constructor(public router: Router, private http: HttpClient, private toastr: ToastrService,public rest: RestService) {
  }

  ngOnInit() { }

  login() {
    this.toastr.info("Espere un momento",'Intentando acceder',{
      progressBar: true
    });
    this.rest.postData('login',this.userData).subscribe((result) => {
      this.toastr.success('Has iniciado sesión');
      localStorage.setItem('isLoggedin', 'true');
      localStorage.setItem('username', result._usernameUser);
      localStorage.setItem('userid', result._idUser);
      this.permitions = [];
      this.rest.getData("users/"+result._idUser+"/privileges").subscribe((data: {}) => {
        this.permitions = data;
        localStorage.setItem('privileges', JSON.stringify(data));
        this.router.navigate(['/blank-page']);
      },
      (err) => {
        this.toastr.error("Error de Conexion.");
        console.log(err);
      });
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
