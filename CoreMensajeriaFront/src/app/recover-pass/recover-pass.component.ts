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
      this.toastr.info("Espere un momento",'Procesando solicitud',{
        progressBar: true
      });
      this.rest.getData("request_password?email="+ this._email).subscribe((data:{})=>{
        this.toastr.success('Revisa tu bandeja de entrada donde se envió el código de verificación');
        this.router.navigate(['/change-pass'])
      }, 
      (err)=>{
        if (err.status == 0) this.toastr.error('Problema de conexión');
        else this.toastr.error(err.error._error);
      }) 
    
      }
    else
      this.toastr.error('Debes ingresar un Email');
    
  }

}
