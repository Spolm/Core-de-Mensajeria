import { NgModule } from '@angular/core';
import { NgbModule, NgbAlertModule}  from '@ng-bootstrap/ng-bootstrap';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { ProfileRoutingModule } from './profile-routing.module';
import { ProfileComponent } from './profile.component';
import { EditProfile } from './edit_profile.service';
import { PageHeaderModule } from './../../shared';
import { HttpModule } from '@angular/http';
import { FormsModule } from '@angular/forms'; 

@NgModule({
  imports: [CommonModule,ProfileRoutingModule,PageHeaderModule,HttpModule, HttpClientModule, FormsModule, NgbAlertModule, NgbModule],
  declarations: [ProfileComponent],
  providers: [EditProfile],
})
export class ProfileModule { }
