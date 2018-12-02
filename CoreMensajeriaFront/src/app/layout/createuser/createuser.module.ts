import { NgModule } from '@angular/core';
import { NgbModule, NgbAlertModule}  from '@ng-bootstrap/ng-bootstrap';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { CreateuserRoutingModule } from './createuser-routing.module';
import { CreateuserComponent } from './createuser.component';
import { PageHeaderModule } from './../../shared';
import { HttpModule } from '@angular/http';
import { FormsModule } from '@angular/forms';


@NgModule({
  imports: [CommonModule,PageHeaderModule,HttpModule, HttpClientModule, FormsModule, NgbAlertModule, NgbModule,CreateuserRoutingModule
  ],
  declarations: [CreateuserComponent]
})
export class CreateuserModule { }
