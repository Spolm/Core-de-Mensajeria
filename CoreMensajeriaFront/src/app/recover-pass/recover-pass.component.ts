import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import { RestService } from '../shared/services/rest.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-recover-pass',
  templateUrl: './recover-pass.component.html',
  styleUrls: ['./recover-pass.component.scss']
})
export class RecoverPassComponent implements OnInit {
  @Input() _email = '';
  constructor(public router: Router, public rest: RestService, private toastr: ToastrService) {

   }

  ngOnInit() {
  }

  handleForgot(){
    if (this._email.length > 0){
      this.rest.getData("request_password?email="+ this._email).subscribe((data:{})=>{
      }, 
      (err)=>{}) 
      this.router.navigate(['/change-pass'])}
    else
      this.toastr.error('Debes ingresar un Email');
    
  }

}
