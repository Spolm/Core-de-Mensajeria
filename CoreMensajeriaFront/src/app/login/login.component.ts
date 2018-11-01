import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { routerTransition } from '../router.animations';
import { HttpClient } from '@angular/common/http';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss'],
    animations: [routerTransition()]
})
export class LoginComponent implements OnInit {
    constructor(public router: Router,private http: HttpClient) {}

    ngOnInit() {}

    onLoggedin() {
        localStorage.setItem('isLoggedin', 'true');
        this.http.get('https://jsonplaceholder.typicode.com/todos/1').subscribe(data => {
      console.log(data);
    });
    }
}
