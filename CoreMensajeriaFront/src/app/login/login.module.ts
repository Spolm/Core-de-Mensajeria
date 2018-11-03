import { NgModule } from '@angular/core';
import { NgbModule, NgbAlertModule}  from '@ng-bootstrap/ng-bootstrap';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { LoginRoutingModule } from './login-routing.module';
import { LoginComponent } from './login.component';
import { FormsModule } from '@angular/forms'; 

@NgModule({
    imports: [CommonModule, LoginRoutingModule,HttpClientModule,FormsModule, NgbAlertModule],
    declarations: [LoginComponent,]
})
export class LoginModule {}
