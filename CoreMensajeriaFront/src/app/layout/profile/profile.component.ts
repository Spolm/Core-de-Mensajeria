import { Component, OnInit } from '@angular/core';
import { routerTransition } from '../../router.animations';
import { Users } from './models/users';
import { Http } from '@angular/http';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss'],
  animations: [routerTransition()]
})
export class ProfileComponent implements OnInit {


  ngOnInit() {
  }

  Users : Array<Users>;

  constructor(private http: Http){
    this.http.get('http://localhost:8080/CoreMensajeria_war_exploded/profile/user/Ronnie').subscribe(resp=>this.Users = resp.json());
  }

}
