import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RecoverPassRoutingModule } from './recover-pass-routing.module';
import { NgbAlertModule } from '@ng-bootstrap/ng-bootstrap';
import { RecoverPassComponent } from './recover-pass.component';

@NgModule({
  imports: [ CommonModule, RecoverPassRoutingModule, NgbAlertModule ],
  declarations: [ RecoverPassComponent ]
})
export class RecoverPassModule { }
