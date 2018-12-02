import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ChangePassRoutingModule } from './change-pass-routing.module';
import { ChangePassComponent } from './change-pass.component';

@NgModule({
  imports: [
    CommonModule,
    ChangePassRoutingModule
  ],
  declarations: [ChangePassComponent]
})
export class ChangePassModule { }
