import { Component, OnInit } from '@angular/core';
import { Users } from './../profile/models/users';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

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
