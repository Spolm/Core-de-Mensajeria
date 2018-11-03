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
    users:any = [];
    response:any
    @Input() userData = { _username:'', _password: ''};

 
    constructor(public router: Router,private http: HttpClient,public rest:LoginService, private toastr: ToastrService) {
    }

    ngOnInit() {}

    getUsers() {
        this.users = [];
        this.rest.getUsers().subscribe((data: {}) => {
          console.log(data);
          this.users = data;
        });
      }

    logIn() {
        this.rest.logUser(this.userData).subscribe((result) => {
          console.log(result)
          this.router.navigate(['/blank-page']);
        }, (err) => {
          console.log(err);
        });
      }
    onLoggedin() {
      this.toastr.success('hola mi corazon de melocoton','holis');
        localStorage.setItem('isLoggedin', 'true');
        console.log(this.userData);
        this.logIn();
        //this.logIn();
    }
}
