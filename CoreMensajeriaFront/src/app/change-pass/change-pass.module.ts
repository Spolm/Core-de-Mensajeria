import { NgModule } from '@angular/core';
import { CommonModule, FormStyle } from '@angular/common';

import { ChangePassRoutingModule } from './change-pass-routing.module';
import { ChangePassComponent } from './change-pass.component';
import { FormsModule } from '@angular/forms';

@NgModule({
  imports: [
    CommonModule,
    ChangePassRoutingModule,
    FormsModule
  ],
  declarations: [ChangePassComponent]
})
export class ChangePassModule { }
