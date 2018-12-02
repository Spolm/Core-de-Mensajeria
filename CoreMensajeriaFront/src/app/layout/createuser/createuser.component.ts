import { Component, OnInit } from '@angular/core';
import { Users } from './../profile/models/users';

@Component({
  selector: 'app-createuser',
  templateUrl: './createuser.component.html',
  styleUrls: ['./createuser.component.scss']
})
export class CreateuserComponent implements OnInit {

  Users : Array<Users>;

  constructor() { }

  ngOnInit() {
  }

}
