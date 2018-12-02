import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RecoverPassRoutingModule } from './recover-pass-routing.module';
import { NgbAlertModule } from '@ng-bootstrap/ng-bootstrap';

@NgModule({
  imports: [ CommonModule, RecoverPassRoutingModule, NgbAlertModule ],
  
  declarations: [ RecoverPassModule ]
})
export class RecoverPassModule { }
