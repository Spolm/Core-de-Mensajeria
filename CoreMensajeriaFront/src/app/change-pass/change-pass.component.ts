import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import { RestService } from '../shared/services/rest.service'

@Component({
  selector: 'app-change-pass',
  templateUrl: './change-pass.component.html',
  styleUrls: ['./change-pass.component.scss']
})
export class ChangePassComponent implements OnInit {
  @Input() _email;
  constructor(public router: Router, public rest: RestService) {

   }

  ngOnInit() {
  }

  handleForgot(){
    this.rest.getData("request_password?email="+ this._email).subscribe((data:{})=>{
    }, 
    (err)=>{})
  }

}
