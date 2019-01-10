import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserAdministrationRoutingModule } from './user-administration-routing.module';
import { UserAdministrationComponent } from './user-administration.component';
import { FormsModule } from '@angular/forms';
import { CreateUserComponent } from './create-user/create-user.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    UserAdministrationRoutingModule
  ],
  declarations: [
    UserAdministrationComponent,
    CreateUserComponent
  ]
})
export class UserAdministrationModule { }
